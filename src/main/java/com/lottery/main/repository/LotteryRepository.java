package com.lottery.main.repository;

import com.lottery.main.domain.model.Lottery;
import com.lottery.main.domain.model.WiningBallot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LotteryRepository extends JpaRepository<Lottery, Integer> {

    /*@Query("SELECT w  FROM Lottery w  ")
    List<WiningBallot> GetAllRows();*/
}
