import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Bot extends TelegramLongPollingBot {
    public Bot(DefaultBotOptions options) {
        super(options);
    }
    public static void main(String[] args) {

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try{
            DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);
            options.setProxyHost("178.214.12.140");
            options.setProxyPort(8080);
            options.setProxyType(DefaultBotOptions.ProxyType.HTTP);

            telegramBotsApi.registerBot(new Bot (options));
        } catch(TelegramApiRequestException e) {
            e.printStackTrace();
        };
    }

    /*public void sendMsg(Message message, String text){
        SendMessage sendMessage= new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try{
            sendMessage(sendMessage);
        }

    }
    */


    public void onUpdateReceived(Update update) {
        /*Message message = update.getMessage();
        if (message != null && message.hasText()){
            switch (message.getText()) {
                case "/help":
                    sendMsg(message, "Чем могу помочь?");
                    break;
                case "/settings":
                    sendMsg(message, "Что будем настраивать?");
                    break;
                default:

            }
        }
        if (update.hasMessage() && update.getMessage().hasText()) {
                SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                        .setChatId(update.getMessage().getChatId())
                        .setText(update.getMessage().getText());
                try {
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }*/

    }

    public String getBotUsername() {
        return "Svahasvetabot";
    }

    public String getBotToken() {
        return "940574943:AAGEjP1mK6ij9Q5SJq5x2DFpsuTcY29AHpU";
    }
}

/*public class user
{
        [Key]
    public string tg_id { get; set; }//Уникальный айди пользователя
    public string name { get; set; }//Отображаемое имя
    public string age { get; set; }// Возраст
    public string country { get; set; }// Страна
    public string city { get; set; }//Город
    public string gender { get; set; }//Пол
    public string photo { get; set; }//Ссылка на фото
    public string tg_username { get; set; }//Телеграмовский ник-нейм, по которому можно будет перейти к пользователю в личную переписку
    public string tg_chat_id { get; set; }//Айди чата, куда отправлять ответ
}*/