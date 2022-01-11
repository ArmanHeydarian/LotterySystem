package com.lottery.main.service;

import com.lottery.main.domain.model.Lottery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Component
public class LotteryExecuterService {

    @Autowired
    LotteryService lotteryService;
    @Autowired
    WinningBallotService winningBallotService;


    @PostConstruct
    public void init() throws Exception
    {
        List<Lottery> lotteryList = lotteryService.getAllLotteries();

        Long delayTime;
        ScheduledExecutorService scheduler= Executors.newScheduledThreadPool(10);
        final Long initialDelay = LocalDateTime.now().until(LocalDate.now().plusDays(1).atTime(0, 0), ChronoUnit.MINUTES);

        // If the initialDelay is greater than 1440 (1 day) then set it for today
        if (initialDelay > TimeUnit.DAYS.toMinutes(1)) {
            delayTime = LocalDateTime.now().until(LocalDate.now().atTime(0, 0), ChronoUnit.MINUTES);
        } else {
            delayTime = initialDelay;
        }
        // Scheduling the tasks
        for (Lottery lottery : lotteryList)
            scheduler.scheduleAtFixedRate(new LotteryExecuterTask(lottery, winningBallotService),1,10,	TimeUnit.SECONDS);
            //scheduler.scheduleAtFixedRate(new LotteryExecuterTask(),delayTime,TimeUnit.DAYS.toMinutes(1),	TimeUnit.MINUTES);
    }
}
