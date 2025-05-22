package com.example.chess_demo_spring_boot.controller;

//import com.example.chess_demo_spring_boot.client_server.JClient;
import com.example.chess_demo_spring_boot.domain.*;
import com.example.chess_demo_spring_boot.dto.GameProgressDto;
import com.example.chess_demo_spring_boot.service.ChessManService;
import com.example.chess_demo_spring_boot.service.GameProgressService;
import com.example.chess_demo_spring_boot.service.HistoryService;
import com.example.chess_demo_spring_boot.service.OpponentService;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
public class GameProgressController {
    private final GameProgressService progressService;
    private final HistoryService historyService;
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JClient client = new JClient();
    private final OpponentService opponentService;
    private final ChessManService chessManService;
//    private History history;
    private Opponent opponent;
    private ChessMan chessMan;
    private final List<GameProgressDto> gpd = new ArrayList<>();
    private ChessParty chessParty;
    /**
     * Отрисовка страницы с ходом выбранной шахматной партии
     * @param id код записи из таблицы История
     * @param model модель страницы
     * @return страница с данными
     */
    @GetMapping(value="/progress/{historyId:\\d+}/{chessmanId:\\d+}")
    public String getProgress(@PathVariable("historyId") Long id, @PathVariable ("chessmanId") Long chessmanId,
                              Model model) {
//        Long idHistory = Long.valueOf(id);
        // получаем оппонента по коду партии

        Optional<History> optionalHistory = historyService.getById(id);
        if(optionalHistory.isPresent()) {
            History history = optionalHistory.get();
            chessParty = history.getChessParty();
            opponent = opponentService.getByChessParty(chessParty);

            chessMan = chessManService.getBy_Id(chessmanId).get();
            if(client.getNicName() == null) {

                client.setNicName(chessMan.getNic());
                client.start();
                if(client.isConnected()) {
                    return partyProgress(model);
                } else {
                    model.addAttribute("errorMessage", "Соединение с сервером отсутствует.");
                }
            }


        }
        return "progress";
    }

    private String partyProgress(Model model) {
            List<GameProgress> progressList = progressService.getAllByChessParty(chessParty);
            // проверяем наличие ходов в шахматной партии
            if(!progressList.isEmpty()) {
                progressList.forEach(item -> {
                    GameProgressDto dto = GameProgressDto.builder()
                            .chessManName(item.getChessMan().getNic())
                            .moving(item.getMoving())
                            .build();
                    gpd.add(dto);
                });
            }
            GameProgressDto dto = GameProgressDto.builder()
                    .chessManName("")
                    .moving("")
                    .build();
            setModelAttribute(model);
//            model.addAttribute("errorMessage", "Соединение с сервером установлено");
            model.addAttribute("dto", dto);
        return "progress";
    }

    /**
     * Отсылает сообщение на сервер и в базу данных
     * @param dto аттрибут модели страницы - объект класса GameProgressDto (имя пользователя и шаг, им сделанный)
     * @param model модель страницы
     * @return страницу с изменениями
     */
    @PostMapping(value="/progress/send")
    public String sendMessage(@ModelAttribute("dto") GameProgressDto dto, Model model) {
        // передаём сообщение на сервер
        client.sendMessage(dto.getMoving());
        dto.setChessManName(chessMan.getNic());
        System.out.println("dto=" + dto.toString());
        gpd.add(dto);
        setModelAttribute(model);
//        model.addAttribute("partyDate", chessParty.getPartydate());
//        model.addAttribute("progressList", gpd);
//        model.addAttribute("connect", true);
//        model.addAttribute("errorMessage", "Соединение с сервером установлено");
        return "progress";
    }

    /**
     * Задаёт аттрибуты модели страницы
     * @param model модель страниц
     */
    private void setModelAttribute(Model model) {
        model.addAttribute("partyDate", chessParty.getPartydate());
        model.addAttribute("progressList", gpd);
    }

    private void receivedMessage(String message) {
        GameProgressDto dto = GameProgressDto.builder()
                .chessManName(opponent.getChessMan().getNic())
                .moving(message)
                .build();
        gpd.add(dto);
        try {
            final URL url = new URL("http://localhost:8080/progress/update");
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Класс, отвечающий за обмен сообщениями с другим оппонентом по игре
     */
    private class JClient {
        public int getSERVER_PORT() {
            return SERVER_PORT;
        }

        public void setSERVER_PORT(int SERVER_PORT) {
            this.SERVER_PORT = SERVER_PORT;
        }

        public String getLOCAL_HOST() {
            return LOCAL_HOST;
        }

        public void setLOCAL_HOST(String LOCAL_HOST) {
            this.LOCAL_HOST = LOCAL_HOST;
        }

        public String getNicName() {
            return nicName;
        }

        public void setNicName(String nicName) {
            this.nicName = nicName;
        }

        public boolean isConnected() {
            return connected;
        }

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

        /**
         * Запускает процесс обмена сообщениями
         */
        public void start() {
            clientSomething = new ClientSomething(SERVER_PORT, LOCAL_HOST, nicName);
            try {
                clientSomething.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * Читает параметры соединения с сервером из файла свойств
         */
        private void readConnectProperties() {
            Properties props = new Properties();
            File fil = new File("src/main/resources/client_conn.properties");
            // читаем файл свойств и устанавливаем свойства соединения с сервером
            try (FileInputStream in = new FileInputStream(fil)) {
                props.load(in);
                in.close();// закрываем поток ввода
                LOCAL_HOST = props.getProperty("host", "localhost");
                SERVER_PORT = Integer.parseInt(props.getProperty("port", "0"));
                System.out.println("Свойства считаны!\n\r");
            } catch (IOException ex) {
                LOCAL_HOST = "localhost";
                SERVER_PORT = 0;
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
            private Socket socket;// канал связи с сервером
            private BufferedReader dis;// читает сообщения с сервера
            private BufferedWriter dos;// пишет сообщения на сервер
            private BufferedReader keyBoard;
            private final String nicName;// имя клиента
            private String dTime;// строковое представление времени передачи
            private Logger logger = Logger.getLogger(JClient.class.getName());
            /**
             * Устанавливает свойства соединения и имя клиента в сети
             * @param SERVER_PORT порт связи с сервером
             * @param LOCAL_HOST адрес сервера в сети
             * @param nicName имя клиента для регистрации в сети
             */
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
                        logger.log(Level.SEVERE, null, ex);
                        // сокет должен быть закрыт при любой ошибке
                        downService();
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
                    logger.log(Level.SEVERE, null, ex);
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
                    logger.log(Level.SEVERE, null, ex);
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
                                receivedMessage(word);
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
                            logger.log(Level.SEVERE, null, ex);
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
                        this.downService();

                    } else {
                        dos.write(getFormatMessage(userWord));// отправляем на сервер
                        dos.flush();// чистим
                    }
                } catch (IOException ex) {
                    logger.log(Level.SEVERE, null, ex);
                    this.downService();
                }
            }
        }

    }
}
