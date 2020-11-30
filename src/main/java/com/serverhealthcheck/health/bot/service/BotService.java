package com.serverhealthcheck.health.bot.service;

import com.serverhealthcheck.health.bot.Bot;
import com.serverhealthcheck.health.entity.UserTG;
import com.serverhealthcheck.health.repository.UserTGRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Slf4j
@Service
@EnableAsync(proxyTargetClass = true)
@AllArgsConstructor
public class BotService implements BotDefault {
    private final Bot bot;
    private final UserTGRepository userTGRepository;

    @Override
    public UserTG saveOrUpdate(Long chatId, String name) {
        Optional<UserTG> byName = userTGRepository.findByName(name);
        if (byName.isEmpty()) {
            UserTG build = UserTG.builder()
                    .chatId(chatId)
                    .name(name)
                    .build();
            return userTGRepository.save(build);
        } else {
            UserTG userTG = byName.get();
            userTG.setChatId(chatId);
            return userTGRepository.save(userTG);
        }
    }

    @Override
    @Async
    public void sendMessageToAllUser(String message) {
        userTGRepository.findAll().forEach(userTG -> {
            try {
                bot.execute(new SendMessage().setChatId(userTG.getChatId()).setText(message));
            } catch (TelegramApiException e) {
                log.warn("Error send message", e);
            }
        });
    }
}
