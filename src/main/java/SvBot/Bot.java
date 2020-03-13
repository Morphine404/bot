package SvBot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    /*List<SvBot.Discript> arrayOfDiscript;
    List<SvBot.User> arrayOfUser;
    Bot Bot;*/

    public Bot (DefaultBotOptions options) {
        super(options);
        //this.arrayOfDiscript = new ArrayList<SvBot.Discript>();
        //this.arrayOfUser = new ArrayList<SvBot.User>();
    }
    /*private String check (String name, String last_name, int user_id, String username) {
        MongoClientURI connectionString = new MongoClientURI( "mongodb://host:port" );
        MongoClient mongoClient = new MongoClient(connectionString);
        MongoDatabase database = mongoClient.getDatabase( "db_name" );
        MongoCollection<Document> collection = database.getCollection( "users" );
        long found = collection.count(Document.parse( "{id : " + Integer.toString(user_id) + "}" ));
        if (found == 0 ) {
            Document doc = new Document( "first_name" , name) .append( "last_name" , last_name)
                    .append( "id" , user_id) .append( "username" , username);
            collection.insertOne(doc);
            mongoClient.close();
            System.out.println( "SvBot.User not exists in database. Written." );
            return "no_exists" ;
        } else {
            System.out.println( "SvBot.User exists in database." );
            mongoClient.close();
            return "exists" ;
        }
    }*/
    public static void main(String[] args) {
        System.out.println("Started");

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try{
            DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);
            options.setProxyHost("127.0.0.1");
            options.setProxyPort(9150);
            options.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);

            telegramBotsApi.registerBot(new Bot (options));
        } catch(TelegramApiRequestException e) {
            e.printStackTrace();
        };
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

    public void onUpdateReceived(Update update) {

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
        if (message.equals("/start")) {
            sendMsg(message, "Привет-привет!");
        }

        if (message != null && message.hasText()){
            switch (message.getText()) {
                case "/Registration":
                    sendMsg(message, "Что будем настраивать?");
                    break;
                case "/Help":
                    sendMsg(message, "Чем могу помочь?");
                    break;
                default:
            }
        }
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
    }

    public String getBotToken() {
        //return System.getenv("B_TOKEN");
        return "940574943:AAGEjP1mK6ij9Q5SJq5x2DFpsuTcY29AHpU";
    }
}
