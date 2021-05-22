package by.minilooth.telegrambot.bot.message.client;

import by.minilooth.telegrambot.bot.api.BotContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import by.minilooth.telegrambot.bot.api.enums.UpdateType;
import by.minilooth.telegrambot.bot.keyboard.client.ClientReplyKeyboardMarkupSource;
import by.minilooth.telegrambot.bot.message.MessageService;
import by.minilooth.telegrambot.bot.api.MessageSender;
import by.minilooth.telegrambot.exception.ClientNotFoundException;
import by.minilooth.telegrambot.model.Client;
import by.minilooth.telegrambot.util.TelegramUtils;

@Service
public class ClientMessageService extends MessageService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ClientMessageService.class);
    
    @Autowired private MessageSender messageSender;
    @Autowired private ClientMessageSource clientMessageSource;
    @Autowired private ClientReplyKeyboardMarkupSource clientReplyKeyboardMarkupSource;

    public void sendStartMessage(BotContext<Client> botContext) throws ClientNotFoundException {
        Client client = botContext.getContextEntity();

        if (client == null) throw new ClientNotFoundException();

        if (TelegramUtils.getUpdateType(botContext.getUpdate()).equals(UpdateType.CALLBACK_QUERY) &&
            !botContext.getUpdate().getMessage().getReplyMarkup().equals(new InlineKeyboardMarkup())) {

        }
        else {
            try {
                Message message = messageSender.sendMessage(client.getTelegramId(), clientMessageSource.getMessage("message"), null); 

                updateLastBotMessageId(client.getUser(), message);
            }
            catch (TelegramApiException ex) {
                LOGGER.error("Unable to send start message to user: {}, reason: {}", client.getTelegramId(), ex.getLocalizedMessage());
            }
        }
    }
}
