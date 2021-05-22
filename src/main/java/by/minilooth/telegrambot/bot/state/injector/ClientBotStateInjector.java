package by.minilooth.telegrambot.bot.state.injector;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import by.minilooth.telegrambot.bot.api.BotStateInjector;
import by.minilooth.telegrambot.bot.state.ClientBotState;

@Component
public class ClientBotStateInjector implements BotStateInjector<ClientBotState> {
    
    @PostConstruct
    @Override
    public void inject() {

    }

}
