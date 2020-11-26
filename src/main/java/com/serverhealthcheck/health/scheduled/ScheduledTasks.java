package com.serverhealthcheck.health.scheduled;

import com.serverhealthcheck.health.bot.service.BotService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@EnableScheduling
@AllArgsConstructor
public class ScheduledTasks {

    private final BotService botService;

    @Scheduled(fixedRate = 500)
    public void reportCurrentTime() {
        botService.sendMessageToAllUser("Error");
    }
}
