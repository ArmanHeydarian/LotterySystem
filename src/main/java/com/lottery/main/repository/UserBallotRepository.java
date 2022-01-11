package com.lottery.main.repository;

import com.lottery.main.domain.model.UserBallot;
import com.lottery.main.domain.model.WiningBallot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface UserBallotRepository extends JpaRepository<UserBallot, Integer> {

    //@Query("SELECT b  FROM UserBallot b  Left join b.lottery l ")
    List<UserBallot> findByUserId(int userId);

}
