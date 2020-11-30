package com.serverhealthcheck.health.scheduled;

import com.serverhealthcheck.health.bot.service.BotService;
import com.serverhealthcheck.health.health.HealthChecker;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@EnableScheduling
@AllArgsConstructor
public class ScheduledTasks {

    private final BotService botService;
    private final HealthChecker healthChecker;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void reportCurrentTime() {
        if (!healthChecker.checkServer()) {
            botService.sendMessageToAllUser("Warning! The server has been stopped!");
        }
    }
}
