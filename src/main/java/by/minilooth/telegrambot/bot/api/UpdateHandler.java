package by.minilooth.telegrambot.bot.api;

import by.minilooth.telegrambot.model.User;
import org.telegram.telegrambots.meta.api.objects.Update;

import by.minilooth.telegrambot.util.TelegramUtils;

public abstract class UpdateHandler implements UpdateProcessor {

    public void handle(User user, Update update) {
        switch(TelegramUtils.getUpdateType(update)) {
            case CALLBACK_QUERY:
                this.processCallbackQuery(user, update);
                break;
            case TEXT:
                this.processText(user, update);
                break;
            case CONTACT:
                this.processContact(user, update);
                break;
            case PHOTO:
                this.processPhoto(user, update);
                break;
            case VOICE:
                this.processVoice(user, update);
                break;
            case VIDEO:
                this.processVideo(user, update);
                break;
            case VIDEO_NOTE:
                this.processVideoNote(user, update);
                break;
            case DOCUMENT:
                this.processDocument(user, update);
                break;
            default:
                break;
        }
    }

}
