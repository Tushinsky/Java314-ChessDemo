package com.example.chess_demo_spring_boot.client_server;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
public class JClient {
    private static int SERVER_PORT;// 8192 - номер канала для связи с сервером
    private static String LOCAL_HOST;// IP адрес компьютера

    public JClient() {
//        try {
            // читаем свойства подключения
            readConnectProperties();

//            ClientSomething clientSomething = new ClientSomething(SERVER_PORT, LOCAL_HOST);
//            clientSomething.start();
//        } catch (IOException ex) {
//            Logger.getLogger(JClient.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public void start() {
        ClientSomething clientSomething = new ClientSomething(SERVER_PORT, LOCAL_HOST);
        try {
            clientSomething.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readConnectProperties() {
        Properties props = new Properties();
        Logger logger = Logger.getLogger(JClient.class.getName());
        File fil = new File("src/main/resources/client_conn.properties");
        try {
            System.out.println(fil.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileInputStream in = new FileInputStream(fil)) {
            props.load(in);
            in.close();// закрываем поток ввода
            LOCAL_HOST = props.getProperty("host", "localhost");
            SERVER_PORT = Integer.parseInt(props.getProperty("port", "0"));
            System.out.println("Свойства считаны!\n\r");
        } catch (IOException ex) {
            LOCAL_HOST = "localhost";
            SERVER_PORT = 0;
            logger.log(Level.SEVERE, "Something is wrong", ex);
        }
    }

    private static class ClientSomething {
        private final int SERVER_PORT;// номер канала для связи с сервером
        private final String LOCAL_HOST;// IP адрес компьютера
        private Socket socket;
        private BufferedReader dis;
        private BufferedWriter dos;
        private BufferedReader keyBoard;
        private String nickName;// имя клиента
        private String dtime;// строковое представление времени передачи

        public ClientSomething(int SERVER_PORT, String LOCAL_HOST) {
            this.SERVER_PORT = SERVER_PORT;
            this.LOCAL_HOST = LOCAL_HOST;


        }

        public void start() throws IOException {
            Charset charset = Charset.forName("windows-1251");
            InputStreamReader isr = new InputStreamReader(System.in, charset);
            keyBoard = new BufferedReader(isr);// объект чтения с клавиатуры
            // открываем соединение с сервером
            if (openConnectToServer()) {
                // если соединение открыто, запускаем обмен данными
                try {
                    // создаём потоки для чтения/записи в канал связи и классы для них
                    dis = new BufferedReader(new InputStreamReader(socket.getInputStream(), charset));
                    dos = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), charset));
//                        this.pressNickname();// запрос на ввод имени
                    new ReadMsg().start();// нить, читающая сообщения из сокета
                    new WriteMsg().start();// нить, пишущая сообщения в сокет
                } catch (IOException ex) {
                    // выводим отладочную информацию
                    Logger.getLogger(JClient.class.getName()).log(Level.SEVERE, null, ex);
                    // сокет должен быть закрыт при любой ошибке
                    ClientSomething.this.downService();
                }
            }

        }

        /**
         * Открывает соединение с сервером
         *
         * @throws IOException исключительная ситуация
         */
        private boolean openConnectToServer() throws IOException {
            try {
                System.out.println("Подключение к серверу\n\t(IP адрес - {" + LOCAL_HOST + "}" +
                        " порт - {" + SERVER_PORT + "}");
                InetAddress ipAddress = InetAddress.getByName(LOCAL_HOST);
                socket = new Socket(ipAddress, SERVER_PORT);
                if (socket.isConnected()) {
                    // проверка соединения
                    System.out.println("Соединение с сервером установлено:");
                    System.out.println("\tАдрес хоста = " + socket.getInetAddress().getHostAddress() +
                            "\tРазмер буфера = " + socket.getReceiveBufferSize());
                    return true;
                }
                return false;

            } catch (UnknownHostException ex) {
                Logger.getLogger(JClient.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Ошибка подключения к серверу\n\t(IP адрес " + LOCAL_HOST + ")" +
                        " порт" + SERVER_PORT + ")");
                return false;
            }


        }

        private void downService() {
            try {
                if(!socket.isClosed()) {
                    System.out.println("Закрываем соединение с сервером...");
                    socket.close();
                    System.out.println("Соединение закрыто.");
                    dis.close();
                    dos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(JClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private class ReadMsg extends Thread {

            @Override
            public void run() {
                String word;
                try {
                    while(true) {
                        if(!socket.isClosed()) {
                            word = dis.readLine();
                            if(word.equalsIgnoreCase("quit")) {
                                ClientSomething.this.downService();
                                break;
                            }
                            System.out.println(word);
                        } else {
                            break;
                        }
                    }
                } catch (IOException ex) {
//                    Logger.getLogger(JClient.class.getName()).log(Level.SEVERE, null, ex);
                    ClientSomething.this.downService();
                } catch (NullPointerException ex) {

                }
            }

        }

        private class WriteMsg extends Thread {

            @Override
            public void run() {
                while(true) {
                    String userWord;
                    try {
                        // время передачи сообщения
                        Date time = new Date();
                        // формат времени
                        SimpleDateFormat dtl = new SimpleDateFormat("HH:mm:ss");// берём только время до секунд
                        dtime = dtl.format(time);// получаем время
                        userWord = keyBoard.readLine();// читаем с консоли
                        if(userWord.equalsIgnoreCase("quit")) {
                            dos.write(userWord + "\n");
                            dos.flush();
                            ClientSomething.this.downService();
                            break;// выход из цикла
                        } else {
                            dos.write(getFormatMessage(userWord));// отправляем на сервер
                            dos.flush();// чистим
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(JClient.class.getName()).log(Level.SEVERE, null, ex);
                        ClientSomething.this.downService();
                    }
                }

            }

            /**
             * Преобразует сообщение, извлекая код контакта, которому оно передаётся.
             * Найденный код контакта ставится первым в передаваемом сообщении
             * @param userWord сообщение для форматирования
             * @return преобразованное сообщение для передачи
             */
            private String getFormatMessage(String userWord) {
                int pos = userWord.indexOf(":");// первое вхождение символа ":"
                // если вхождение есть, получаем код, в противном случае он равен "0"
                String id;
                StringBuilder retVal = new StringBuilder();
                if(pos == -1) {
                    id = "0:";
                    retVal.append(id).append(nickName).append("(")
                            .append(dtime).append("):\t")
                            .append(userWord).append("\n");
                } else {
                    id = userWord.substring(0, pos) + ":";
                    retVal.append(id).append(nickName).append("(")
                            .append(dtime).append("):\t")
                            .append(userWord.substring(pos + 1)).append("\n");
                }

                return retVal.toString();
            }
        }

    }



}
