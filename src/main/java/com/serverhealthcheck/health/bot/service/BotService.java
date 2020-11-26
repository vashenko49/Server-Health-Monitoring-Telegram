package com.serverhealthcheck.health.bot.service;

import com.serverhealthcheck.health.bot.Bot;
import com.serverhealthcheck.health.entity.UserTG;
import com.serverhealthcheck.health.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Slf4j
@Service
@EnableAsync(proxyTargetClass = true)
@AllArgsConstructor
public class BotService implements BotDefault {
    private final Bot bot;
    private final UserRepository userRepository;

    @Override
    @Async
    public void sendMessageToAllUser(String message) {

        List<UserTG> all = userRepository.findAll();
        System.out.println(all.toString());

        all.forEach(userTG -> {
            try {
                bot.execute(new SendMessage().setChatId(userTG.getChatId()).setText(message));
            } catch (TelegramApiException e) {
                log.warn("Error send message", e);
            }
        });

    }
}
