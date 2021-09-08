package com.faez.demo.utils.jobs;

import org.springframework.scheduling.annotation.Scheduled;

public class WeeklyReportJob {

    @Scheduled(cron = "@weekly")
    private void weeklyReport() {
        // send admin/user weekly reports
    }
}
