package com.lottery.main.domain.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
public class LotteryComment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @NotNull
    private String comment;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Lottery getLottery() {
        return lottery;
    }

    public void setLottery(Lottery lottery) {
        this.lottery = lottery;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @NotNull
    private Date createDate;

    @ManyToOne
   // @MapsId("lotteryId")
    @JoinColumn(name = "lottery_id")
    Lottery lottery;

    @ManyToOne
   // @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

}
