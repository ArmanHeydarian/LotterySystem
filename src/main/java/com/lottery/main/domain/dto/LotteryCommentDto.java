package com.lottery.main.domain.dto;

public class LotteryCommentDto {

    private String comment;
    private int lotteryId;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(int lotteryId) {
        this.lotteryId = lotteryId;
    }


}
