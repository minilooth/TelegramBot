package by.minilooth.telegrambot.bot.api;

public interface BotStateInjector<E extends BotState<?>> {

    void inject();
    
}
