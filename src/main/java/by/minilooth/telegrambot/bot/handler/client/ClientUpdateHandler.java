package by.minilooth.telegrambot.bot.handler.client;

import by.minilooth.telegrambot.bot.api.BotContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import by.minilooth.telegrambot.bot.api.UpdateHandler;
import by.minilooth.telegrambot.bot.state.ClientBotState;
import by.minilooth.telegrambot.exception.ClientBotStateException;
import by.minilooth.telegrambot.model.Client;
import by.minilooth.telegrambot.model.User;
import by.minilooth.telegrambot.service.ClientService;
import by.minilooth.telegrambot.service.UserService;

@Component
public class ClientUpdateHandler extends UpdateHandler {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(ClientUpdateHandler.class);

    @Autowired private UserService userService;
    @Autowired private ClientService clientService;

    private void updateState(User user, ClientBotState clientBotState) {
        if (user != null && user.getClient() != null && clientBotState != null) {
            user.getClient().setClientBotState(clientBotState);
            userService.save(user);
        }
    }

    @Override
    public void processText(User user, Update update) throws ClientBotStateException {
        final String chatId = update.getMessage().getChatId().toString();
        BotContext<Client> botContext = null;
        ClientBotState botState = null;
        Client client = user.getClient();

        try {
            if (client == null) {
                client = clientService.createClient(user);

                botContext = BotContext.of(client, update);
                botState = client.getClientBotState();

                botState.enter(botContext);
                
                while(!botState.getIsInputNeeded()) {
                    if (botState.nextState() != null) {
                        botState = botState.nextState();
                        botState.enter(botContext);
                    }
                    else {
                        break;
                    }
                }
            }
            else {
                botContext = BotContext.of(client, update);
                botState = client.getClientBotState();

                LOGGER.info("[{} | {}] Text: {}", chatId, botState, update.getMessage().getText());

                botState.handleText(botContext);

                do {
                    if (botState.nextState() != null) {
                        botState = botState.nextState();
                        botState.enter(botContext);
                    }
                    else {
                        break;
                    }
                } while (!botState.getIsInputNeeded());
            }
        }
        catch (ClientBotStateException ex) {
            botState = ex.getExceptionState().rootState();
            botState.enter(botContext);
        }
        finally {
            updateState(user, botState);
        }
    }

    @Override
    public void processContact(User user, Update update) {
        // TODO Auto-generated method stub

    }

    @Override
    public void processPhoto(User user, Update update) {
        // TODO Auto-generated method stub

    }

    @Override
    public void processCallbackQuery(User user, Update update) {
        // TODO Auto-generated method stub

    }

    @Override
    public void processVoice(User user, Update update) {
        // TODO Auto-generated method stub

    }

    @Override
    public void processVideo(User user, Update update) {
        // TODO Auto-generated method stub

    }

    @Override
    public void processVideoNote(User user, Update update) {
        // TODO Auto-generated method stub

    }

    @Override
    public void processDocument(User user, Update update) {
        // TODO Auto-generated method stub

    }

    
}
