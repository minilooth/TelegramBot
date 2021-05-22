package by.minilooth.telegrambot.bot.api;

public interface BotStateInjector<E extends Enum<E>, T> {

    void inject();
    
}
