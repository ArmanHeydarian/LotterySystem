package com.lottery.main.service;

import com.lottery.main.LotteryApplication;
import com.lottery.main.domain.dto.WiningBallotDto;
import com.lottery.main.domain.model.Lottery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Logger;



public class LotteryExecuterTask implements Runnable {

    static private FileHandler fileHanlder;
    static private SimpleFormatter formatterTxt;
    private Lottery lottery;
    private WiningBallotService winingBallotService;
    private static final Logger logger = Logger.getLogger(LotteryExecuterTask.class.getName());

    public LotteryExecuterTask(Lottery lottery ,WiningBallotService winingBallotService) {
        this.lottery = lottery;
        this.winingBallotService = winingBallotService;
    }

        public void run ()
        {
            ConfigFileHanlder();

            int min = 1;
            int max = 50;
            List<Integer> randomList= new ArrayList<Integer>();
            //logger.info("Current time : "+new Date().getTime());
            logger.info("Random value of type double between "+min+" to "+max+ ":");

            for (int i=0; i<lottery.getBallotLength();i++)
            {
                //Generate random double value from min to max
                int rand = (int) (Math.random()*(max-min+1)+min);
                // check is duplicated so regenerate another random number
                while (randomList.contains(rand)) {
                    //logger.warn("Duplicate:" + rand);
                    rand = (int) (Math.random() * (max - min + 1) + min);
                }
                randomList.add(rand);
            }
            Collections.sort(randomList);

            WiningBallotDto winingBallotDto =new WiningBallotDto(){{setLotteryId(lottery.getId()); setWiningNumberList(randomList.toString());}};

            try {
                winingBallotService.addWiningBallot(winingBallotDto);
            } catch (Exception e) {
                e.printStackTrace();
            }

            logger.info(lottery.getTitle() + " : "+  randomList.toString());


        }

    private void ConfigFileHanlder() {
        if (logger.getHandlers().length==0)
        {
            try {
                fileHanlder = new FileHandler("logs/"+new Date().getDate()+".txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.addHandler(fileHanlder);
        }
        formatterTxt = new SimpleFormatter();
        fileHanlder.setFormatter(formatterTxt);
    }


}
