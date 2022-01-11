package com.lottery.main.domain.dto;

public class LotteryDto {

    private String title;
    private String description;
    private long ballotPrice;
    private int ballotLength;
    private int minNumber;
    private int maxNumber;

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

    public int getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(int minNumber) {
        this.minNumber = minNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }
}
