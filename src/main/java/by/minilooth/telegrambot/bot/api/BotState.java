package by.minilooth.telegrambot.bot.api;

public interface BotState<E extends Enum<E>, T> {
    
    <X extends Exception> void handleText(T botContext) throws X;
    <X extends Exception> void handleCallbackQuery(T botContext) throws X;
    <X extends Exception> void handleContact(T botContext) throws X;
    <X extends Exception> void handlePhoto(T botContext) throws X;
    <X extends Exception> void handleVoice(T botContext) throws X;
    <X extends Exception> void handleVideo(T botContext) throws X;
    <X extends Exception> void handleVideoNote(T botContext) throws X;
    <X extends Exception> void handleDocument(T botContext) throws X;

    <X extends Exception> void enter(T botState) throws X;
    E nextState();
    E rootState();

}
