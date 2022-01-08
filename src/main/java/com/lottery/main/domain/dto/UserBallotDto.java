package com.lottery.main.domain.dto;


public class UserBallotDto {

    private int lotteryId;
    private String numberList;

    public int getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(int lotteryId) {
        this.lotteryId = lotteryId;
    }

    public String getNumberList() {
        return numberList;
    }

    public void setNumberList(String numberList) {
        this.numberList = numberList;
    }
}
