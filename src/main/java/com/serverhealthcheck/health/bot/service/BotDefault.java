package com.serverhealthcheck.health.bot.service;

import com.serverhealthcheck.health.entity.UserTG;

public interface BotDefault {

    UserTG saveOrUpdate(Long chatId, String name);

    void sendMessageToAllUser(String message);
}
