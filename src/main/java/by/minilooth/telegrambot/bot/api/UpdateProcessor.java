package by.minilooth.telegrambot.bot.api;

import by.minilooth.telegrambot.model.User;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateProcessor {
    
    public <E extends Exception> void processText(User user, Update update) throws E;
    public <E extends Exception> void processContact(User user, Update update) throws E;
    public <E extends Exception> void processPhoto(User user, Update update) throws E;
    public <E extends Exception> void processCallbackQuery(User user, Update update) throws E;
    public <E extends Exception> void processVoice(User user, Update update) throws E;
    public <E extends Exception> void processVideo(User user, Update update) throws E;
    public <E extends Exception> void processVideoNote(User user, Update update) throws E;
    public <E extends Exception> void processDocument(User user, Update update) throws E;
    
}
