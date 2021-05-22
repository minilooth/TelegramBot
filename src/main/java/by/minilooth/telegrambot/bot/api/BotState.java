package by.minilooth.telegrambot.bot.api;

public interface BotState<T> {
    
    default void handleText(T botContext) {}
    default void handleCallbackQuery(T botContext) {}
    default void handleContact(T botContext) {}
    default void handlePhoto(T botContext) {}
    default void handleVoice(T botContext) {}
    default void handleVideo(T botContext) {}
    default void handleVideoNote(T botContext) {}
    default void handleDocument(T botContext) {}

    void enter(T botContext);
    BotState<T> nextState();
    BotState<T> rootState();

}
