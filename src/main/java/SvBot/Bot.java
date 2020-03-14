package SvBot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class Bot extends TelegramLongPollingCommandBot {

    static Map<Integer,String> Anketa = new HashMap<>();
    static Map<Integer,List>  Lyalya = new HashMap<>();
    final static Logger logger = Logger.getLogger(Bot.class.getName());
    //List<SvBot.Discript> arrayOfDiscript;
    List<SvBot.User> arrayOfUser;
    Bot Bot;
    static String BotToken, BotUsername, startMsg, voteMsg, wrongFormatMsg, wrongNumberMsg, statusMsg, revoteMsg;
    static String ruRegMsg, ruVoteMsg, ruRevoteMsg, ruWrongNumberMsg, ruWrongFormatMsg, ruStartMsg, ruDoubleVoteMsg;

    public Bot (DefaultBotOptions botOptions) {
        super(botOptions);
        //this.arrayOfDiscript = new ArrayList<SvBot.Discript>();
        this.arrayOfUser = new ArrayList<SvBot.User>();
    }

    public static void main(String[] args) {
        System.out.println("Started");
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try{
            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
            botOptions.setProxyHost("127.0.0.1");
            botOptions.setProxyPort(9150);
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
            telegramBotsApi.registerBot(new Bot (botOptions));
        } catch(TelegramApiRequestException e) {
            logger.error("Error telegram bot token:  " + e);
            System.exit(0);
        };
        logger.info("BOT WORKING");
    }

    public void sendMsg(Message message, String text){
        SendMessage sendMessage= new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try{
            setButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void processNonCommandUpdate(Update update) {

        Message message = update.getMessage();
        /*if (update.hasMessage() && update.getMessage().hasText()) {

        String user_first_name = update.getMessage().getChat().getFirstName();
        String user_last_name = update.getMessage().getChat().getLastName();
        String user_username = update.getMessage().getChat().getUserName();
        long user_id = update.getMessage().getChat().getId();
        String message_text = update.getMessage().getText();
        long chat_id = update.getMessage().getChatId();

        SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(message_text);
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }*/
        if (message.getText().equals("/start")) {
            sendMsg(message, "Привет-привет! Я бот знакомств");
            logger.info("Posted /start message");
        }

        if (message != null && message.hasText()){
            switch (message.getText()) {
                case "/Registration":
                    sendMsg(message, "Хочешь знакомиться? " + "\nДавай сначала познакомимся со мной! :)" );
                    sendMsg(message, "Для начала представься");
                    logger.info("Nachalo registracii");
                    /*if(update.hasCallbackQuery()){
                        try {
                            execute(new sendMsg().setText(
                                    update.getCallbackQuery().getData())
                                    .setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }*/
                    /*if (message != null && message.hasText())
                    {

                        //sendMsg(message, "");
                    }
                    break;*/
                case "/Help":
                    sendMsg(message, "Чем могу помочь?");
                    break;
                default:
            }
        }
    }
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

        // ADDING VARS
        /*String players = properties.getProperty("allPlayers");
        int id = 1;
        while (players.contains(",")) {
            playerName.put(id, players.substring(0, players.indexOf(",")));
            players = players.substring(players.indexOf(", ") + 2);
            id++;
        }
        playerName.put(id, players);*/


        /*for (int i = 1; i < playerName.size() + 1; i++) {
            playerVotes.put(i, 0);
        }*/
    }

    public void setButtons(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton( "/Registration"));
        keyboardFirstRow.add(new KeyboardButton( "/Help"));

        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

    }

    public String getBotUsername() {
        return "Svahasvetabot";
       //return BotUsername;
    }

    public String getBotToken() {
        //return BotToken;
        return "940574943:AAGEjP1mK6ij9Q5SJq5x2DFpsuTcY29AHpU";
    }
}
