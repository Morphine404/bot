package SvBot;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class Registr extends BotCommand{
    private final SendMessage sendMessage;

    public Registr(String commandIdentifier, String description) {
        super(commandIdentifier, description);
        sendMessage = new SendMessage();
    }



    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        try {
            Gender newgender = null;
            if (arguments[4] == "female") {
                newgender = Gender.female;
            } else if (arguments[4] == "male") {
                newgender = Gender.male;
            }
            /*SvBot.User newUser = new SvBot.User(Integer.parseInt(arguments[0]), arguments[1],
                    arguments[2], Integer.parseInt(arguments[3]),
                    newgender]);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


