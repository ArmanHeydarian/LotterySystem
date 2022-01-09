package com.lottery.main.domain.dto;

public class WiningBallotDto {

    private int lotteryId;
    private String winingNumberList;

    public int getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(int lotteryId) {
        this.lotteryId = lotteryId;
    }

    public String getWiningNumberList() {
        return winingNumberList;
    }

    public void setWiningNumberList(String winingNumberList) {
        this.winingNumberList = winingNumberList;
    }


}
