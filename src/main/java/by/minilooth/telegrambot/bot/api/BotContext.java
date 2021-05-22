package by.minilooth.telegrambot.bot.api;

import org.telegram.telegrambots.meta.api.objects.Update;

public class BotContext<T> {
    
    private final T entity;
    private final Update update;

    private BotContext(T entity, Update update) {
        this.entity = entity;
        this.update = update;
    }

    public static <T> BotContext<T> of(T entity, Update update) {
        return new BotContext<T>(entity, update);
    }

    public T getContextEntity() {
        return entity;
    }

    public Update getUpdate() {
        return update;
    }

}
