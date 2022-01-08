package com.lottery.main.domain.dto;

import java.util.Date;

public class LotteryDto {

    private String title;
    private String description;
    private long ballotPrice;
    private boolean isBlocked;
    private int ballotLength;


    public int getBallotLength() {
        return ballotLength;
    }

    public void setBallotLength(int ballotLength) {
        this.ballotLength = ballotLength;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getBallotPrice() {
        return ballotPrice;
    }

    public void setBallotPrice(long ballotPrice) {
        this.ballotPrice = ballotPrice;
    }


}
