package by.minilooth.telegrambot.bot.service;

import by.minilooth.telegrambot.bot.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramFilesService {

    @Autowired private TelegramBot bot;

    public org.telegram.telegrambots.meta.api.objects.File getTelegramFile(String fileId) throws TelegramApiException {
        return bot.execute(GetFile.builder().fileId(fileId).build());
    }

    public java.io.File downloadTelegramFile(String fileId) throws TelegramApiException {
        return bot.downloadFile(getTelegramFile(fileId));
    }

}
