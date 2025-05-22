package com.example.chess_demo_spring_boot.client_server;

import com.example.chess_demo_spring_boot.controller.GameProgressController;
import com.example.chess_demo_spring_boot.controller.HomePageController;
import com.example.chess_demo_spring_boot.dto.GameProgressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.ui.Model;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class JClient {
    private int SERVER_PORT;// 8192 - номер канала для связи с сервером
    private String LOCAL_HOST;// IP адрес компьютера
    private String nicName;
    private boolean connected;
    private ClientSomething clientSomething;

    public JClient() {
//        try {
        // читаем свойства подключения
        readConnectProperties();


    }

    public void start() {
        clientSomething = new ClientSomething(SERVER_PORT, LOCAL_HOST, nicName);
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

    /**
     * Передаёт сообщение на сервер выбранному пользователю
     * @param message сообщение, которое передаётся
     */
    public void sendMessage(String message) {
        clientSomething.sendMessage(message);
    }

    private class ClientSomething {
        private final int SERVER_PORT;// номер канала для связи с сервером
        private final String LOCAL_HOST;// IP адрес компьютера
        private Socket socket;
        private BufferedReader dis;
        private BufferedWriter dos;
        private BufferedReader keyBoard;
        private final String nicName;// имя клиента
        private String dTime;// строковое представление времени передачи

        public ClientSomething(int SERVER_PORT, String LOCAL_HOST, String nicName) {
            this.SERVER_PORT = SERVER_PORT;
            this.LOCAL_HOST = LOCAL_HOST;


            this.nicName = nicName;
        }

        private void start() throws IOException {
            Charset charset = Charset.forName("windows-1251");
            InputStreamReader isr = new InputStreamReader(System.in, charset);
            keyBoard = new BufferedReader(isr);// объект чтения с клавиатуры
            // открываем соединение с сервером
            if (openConnectToServer()) {
                // если соединение открыто, запускаем обмен данными
                JClient.this.connected = true;
                try {
                    // создаём потоки для чтения/записи в канал связи и классы для них
                    dis = new BufferedReader(new InputStreamReader(socket.getInputStream(), charset));
                    dos = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), charset));

                    // передаём имя игрока
                    dos.write(nicName + "\n");
                    dos.flush();

                    new ReadMsg().start();// нить, читающая сообщения из сокета
                    new WriteMsg().start();// нить, пишущая сообщения в сокет
                } catch (IOException ex) {
                    // выводим отладочную информацию
                    Logger.getLogger(JClient.class.getName()).log(Level.SEVERE, null, ex);
                    // сокет должен быть закрыт при любой ошибке
                    ClientSomething.this.downService();
                }
            } else {
                JClient.this.connected = false;
            }

        }

        /**
         * Открывает соединение с сервером
         */
        private boolean openConnectToServer() {
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

            } catch (IOException ex) {
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
                } catch (IOException | NullPointerException ex) {
//                    Logger.getLogger(JClient.class.getName()).log(Level.SEVERE, null, ex);
                    ClientSomething.this.downService();
                }
            }

        }

        private class WriteMsg extends Thread {

            @Override
            public void run() {
                while(true) {
                    String userWord;
                    try {
                        System.out.println(Arrays.toString(GameProgressController.class.getMethods()));

                        // время передачи сообщения
                        Date time = new Date();
                        // формат времени
                        SimpleDateFormat dtl = new SimpleDateFormat("HH:mm:ss");// берём только время до секунд
                        dTime = dtl.format(time);// получаем время
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
                retVal.append(id).append(nicName).append("(")
                        .append(dTime).append("):\t")
                        .append(userWord).append("\n");
            } else {
                id = userWord.substring(0, pos) + ":";
                retVal.append(id).append(nicName).append("(")
                        .append(dTime).append("):\t")
                        .append(userWord.substring(pos + 1)).append("\n");
            }

            return retVal.toString();
        }

        private void sendMessage(String userWord) {
            // время передачи сообщения
            Date time = new Date();
            // формат времени
            SimpleDateFormat dtl = new SimpleDateFormat("HH:mm:ss");// берём только время до секунд
            dTime = dtl.format(time);// получаем время
            try {
                if (userWord.equalsIgnoreCase("quit")) {
                    dos.write(userWord + "\n");
                    dos.flush();
                    ClientSomething.this.downService();

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
}
