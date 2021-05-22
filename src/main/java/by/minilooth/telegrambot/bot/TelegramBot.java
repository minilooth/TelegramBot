package by.minilooth.telegrambot.bot;

import by.minilooth.telegrambot.util.TelegramUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import by.minilooth.telegrambot.bot.handler.client.ClientUpdateHandler;
import by.minilooth.telegrambot.model.User;
import by.minilooth.telegrambot.model.enums.Role;
import by.minilooth.telegrambot.service.UserService;
import lombok.Getter;

@Component
@Configuration
public class TelegramBot extends TelegramLongPollingBot {
    private static final Logger LOGGER = LogManager.getLogger(TelegramBot.class);

    @Getter 
    @Value("${telegram.botUsername}")
    private String botUsername;

    @Getter
    @Value("${telegram.botToken}") 
    private String botToken;

    @Autowired private UserService userService;
    @Autowired private ClientUpdateHandler clientUpdateHandler;

    @Override
    public void onUpdateReceived(final Update update) {
        userService.getByTelegramId(TelegramUtils.getTelegramId(update))
                .ifPresentOrElse(u -> processUpdate(u, update), () -> register(update));
    }

    private void processUpdate(User user, Update update) {
        switch (user.getRole()) {
            case CLIENT:
                clientUpdateHandler.handle(user, update);
                break;
            default:
                break;
        }
    }

    private void register(Update update) {
        User user = userService.createUser(update, Role.parseRole(update.getMessage().getText()));

        LOGGER.info("New user registered: " + user.getTelegramId());

        processUpdate(user, update);
    }
}
