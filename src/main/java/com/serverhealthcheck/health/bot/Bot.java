package com.serverhealthcheck.health.bot;

import com.serverhealthcheck.health.bot.service.BotService;
import com.serverhealthcheck.health.entity.UserTG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Autowired
    private BotService botService;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {

            Message message = update.getMessage();

            UserTG userTG = botService.saveOrUpdate(message.getChatId(), message.getFrom().getUserName());

            execute(new SendMessage().setChatId(userTG.getChatId())
                    .setText("Hi!" + userTG.getName()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
