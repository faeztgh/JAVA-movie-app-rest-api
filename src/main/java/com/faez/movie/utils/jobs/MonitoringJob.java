package com.faez.movie.utils.jobs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
public class MonitoringJob {

    @Scheduled(cron = "@reboot")
    private void reportOnReboot() {
        // notify admin that system is rebooted
        log.info("System rebooted");
    }
}
