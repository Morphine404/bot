package SvBot;

import com.vdurmont.emoji.EmojiParser;
import javafx.scene.paint.Stop;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.LoggingPermission;

public class Bot extends TelegramLongPollingBot {
    private static final String BotUsername = "Svahasvetabot";
    private static final String BotToken = "940574943:AAGEjP1mK6ij9Q5SJq5x2DFpsuTcY29AHpU";
    static Map<String, Integer> registr = new HashMap<>();// user name / ID for meeting
    static Map<Integer, String> Anketa = new HashMap<>();// ID/ user prof
    static Map<Integer, List> Lyalya = new HashMap<>(); //ID / likes
    final static Logger logger = Logger.getLogger(Bot.class.getName());
    List<SvBot.User> arrayOfUser;
    Bot Bot;
    static String startMsg, voteMsg, wrongFormatMsg, wrongNumberMsg, statusMsg, revoteMsg;
    static String ruRegMsg, ruVoteMsg, ruRevoteMsg, ruWrongNumberMsg, ruWrongFormatMsg, ruStartMsg, ruDoubleVoteMsg;

    public Bot(DefaultBotOptions botOptions) {
        super(botOptions);
    }
/*
    public Bot(){
        super(BotToken, BotUsername);
    }
    public BotAbilit botAbilit() {
        return new BotAbilit(silent, db, BotUsername);
    }*/

    public static void main(String[] args) {
        System.out.println("Started");
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
            botOptions.setProxyHost("127.0.0.1");
            botOptions.setProxyPort(9150);
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
            telegramBotsApi.registerBot(new Bot(botOptions));
        } catch (TelegramApiRequestException e) {
            logger.error("Error telegram bot token:  " + e);
            System.exit(0);
        }
        logger.info("BOT WORKING");
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();

        if (message.getText().equals("/start")) {
            String smile_emoji = EmojiParser.parseToUnicode(":smiley:");
            sendMsg(message, "Привет-привет! Я бот знакомств" + smile_emoji);
            logger.info("Posted /start message");
        }

        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/Registration":

                    sendMsg(message, "Хочешь знакомиться? " +
                            "\nДавай сначала познакомимся со мной! :)" +
                            "\nДля начала представься");
                    logger.info("Nachalo registracii");

                    break;

                case "/Help":
                    sendMsg(message, "Чем могу помочь?");
                    break;

                default:
                    try {
                        User user = new User();
                        message = update.getMessage();
                        if (update.hasMessage() && update.getMessage().hasText() && !update.getMessage().isCommand()) {
                            String message_text = update.getMessage().getText();
                            long chat_id = update.getMessage().getChatId();
                            SendMessage messagename = new SendMessage()
                                    .setChatId(chat_id)
                                    .setText(message_text);
                            message_text = user.Name;
                            sendMsg(message, "Отлично, " + message_text +
                                    "! Сколько тебе лет?");
                            logger.info("Polucheno imya");

                            if (update.hasMessage() && update.getMessage().hasText() && !update.getMessage().isCommand()) {
                                String message_age = update.getMessage().getText();
                                SendMessage messageage = new SendMessage()
                                        .setChatId(chat_id)
                                        .setText(message_age);
                                message_age = String.valueOf(user.age);
                                sendMsg(message, "А ты  хорошо выглядишь для своих " + message_age +
                                        "! Из какого ты городе?");
                                logger.info("Poluchen vozrast");

                                if (update.hasMessage() && update.getMessage().hasText() && !update.getMessage().isCommand()) {
                                    String message_city = update.getMessage().getText();
                                    SendMessage messagecity = new SendMessage()
                                            .setChatId(chat_id)
                                            .setText(message_city);
                                    message_city = user.city;
                                    sendMsg(message, message_city + " - красивый город, бывал там однажды."+
                                            "\nНу и последний вопрос, кто ты по знаку зодиака?");
                                    logger.info("Poluchen gorod");

                                    if (update.hasMessage() && update.getMessage().hasText() && !update.getMessage().isCommand()) {

                                        String message_zodiak = update.getMessage().getText();
                                        SendMessage messagezodiak = new SendMessage()
                                                .setText(message_zodiak);
                                        message_zodiak = user.zodiak;
                                        sendMsg(message, "На этом регистрация окончена. Ты можешь посмотреть свою анкету или же ознакомиться с другими");

                                        logger.info("Poluchen zodiak/ Registracia okonchena");
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        sendMsg(message, "Неправильный формат сообщения, я тебя не понимаю :(");
                        logger.info("Posted WrongFormat message");
                    }


            }
        }
    }

    /*private void read(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(Message));
        int character;
        StringBuilder sb = new StringBuilder();
        while ((character = br.read()) != -1) {
            char ch = (char) character;
            if (character == ' ' || ch == ',' ) break;
            sb.append(ch);
        }
    }*/
    public static void settings() {
        Properties properties = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream("src/main/resources/configuration.properties");
            properties.load(in);
        } catch (FileNotFoundException e) {
            logger.error("FILE NOT FOUND EXCEPTION: " + e);
        } catch (IOException e) {
            logger.error("IO EXCEPTION: " + e);
        }

        //BotToken = properties.getProperty("BotToken");
        //BotUsername = properties.getProperty("BotUsername", "Unknown Bot");

        String users = properties.getProperty("allusers");
        int id = 1;
        while (users.contains(",")) {
            Anketa.put(id, users.substring(1, users.indexOf(",")));
            users = users.substring(users.indexOf(", ") + 2);
            id++;
        }
        Anketa.put(id, users);

        /*for (int i = 1; i < Anketa.size() + 1; i++) {
            registr.put(i, 0);
        }*/
    }

    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("/Registration"));
        keyboardFirstRow.add(new KeyboardButton("/Help"));

        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

    }

    public int creatorId() {
        return 0;
    }

    public String getBotUsername() {
        return "Svahasvetabot";
        //return BotUsername;
    }

    public String getBotToken() {
        return "940574943:AAGEjP1mK6ij9Q5SJq5x2DFpsuTcY29AHpU";
        //return BotToken;
    }
}
