package com.lottery.main.service;

import com.lottery.main.domain.dto.WiningBallotDto;
import com.lottery.main.domain.model.Lottery;

import java.io.IOException;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LotteryExecuterTask implements Runnable {

    private static final Logger logger = LogManager.getLogger(LotteryExecuterTask.class);
    private Lottery lottery;
    private WinningBallotService winningBallotService;
    private WiningBallotDto winingBallotDto;
    private List<Integer> randomList;

    //----------------------------------------------------------------------------------------
    public LotteryExecuterTask(Lottery lottery, WinningBallotService winningBallotService) {
        this.lottery = lottery;
        this.winningBallotService = winningBallotService;
        this.randomList = new ArrayList<>();
    }
    //----------------------------------------------------------------------------------------
    public void run() {
         try {
            GenerateRandomNumbers();
            GenerateAndPersistWiningBallot();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(lottery.getTitle() + " : " + randomList.toString());
    }
    //----------------------------------------------------------------------------------------
    public void GenerateRandomNumbers() throws Exception{
        int min = lottery.getMinNumber();
        int max = lottery.getMaxNumber();
        randomList = new ArrayList<>();
        for (int i = 0; i < lottery.getBallotLength(); i++) {
            //Generate random double value from min to max
            int rand = (int) (Math.random() * (max - min + 1) + min);

            // check is duplicated so regenerate another random number
            while (randomList.contains(rand)) {
                rand = (int) (Math.random() * (max - min + 1) + min);
            }
            randomList.add(rand);
        }
        Collections.sort(randomList);
    }
    //----------------------------------------------------------------------------------------
    private void GenerateAndPersistWiningBallot() throws Exception {
        // creates the WinningBallot to persist on DataBase
        winingBallotDto = new WiningBallotDto() {{
            setLotteryId(lottery.getId());
            setWiningNumberList(randomList.toString());
        }};
        winningBallotService.addWiningBallot(winingBallotDto);
    }

}
