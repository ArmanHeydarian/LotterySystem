package com.lottery.main.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
public class Lottery {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @NotNull
    private String title;
    @NotNull
    private long BallotPrice;
    @NotNull
    private Date createDate;
    private String description;
    private int ballotLength;
    private int minNumber;
    private int maxNumber;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "lottery" , cascade = { CascadeType.ALL } )
    private List<WiningBallot> winingBallots;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "lottery" , cascade = { CascadeType.ALL } )
    private List<UserBallot> userBallots;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "lottery", cascade = { CascadeType.ALL } )
    private List<LotteryComment> lotteryComments ;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return BallotPrice;
    }

    public void setBallotPrice(long ballotPrice) {
        BallotPrice = ballotPrice;
    }


    public int getBallotLength() {
        return ballotLength;
    }

    public void setBallotLength(int ballotLength) {
        this.ballotLength = ballotLength;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public List<LotteryComment> getLotteryComments() {
        return lotteryComments;
    }

    public void setLotteryComments(List<LotteryComment> lotteryComments) {
        this.lotteryComments = lotteryComments;
    }


    public List<WiningBallot> getWiningBallots() {
        return winingBallots;
    }

    public void setWiningBallots(List<WiningBallot> winingBallots) {
        this.winingBallots = winingBallots;
    }

    public List<UserBallot> getUserBallots() {
        return userBallots;
    }

    public void setUserBallots(List<UserBallot> userBallots) {
        this.userBallots = userBallots;
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
