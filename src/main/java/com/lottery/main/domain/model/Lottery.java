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

    private String description;

    @NotNull
    private long BallotPrice;

    private boolean isBlocked;

    private int ballotLength;

    @NotNull
    private Date createDate;


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

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
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
}
