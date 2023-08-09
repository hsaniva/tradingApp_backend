package com.maverick.tradingApp.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Service
@Profile("Karthik")
public class Scheduler {

    @Scheduled(fixedRate = 5000)
    public void task1(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Comleted task1 : "+dtf.format(now));
    }

}
