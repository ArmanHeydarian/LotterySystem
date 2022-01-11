package com.lottery.main.repository;

import com.lottery.main.domain.model.WiningBallot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface WiningBallotRepository extends JpaRepository<WiningBallot, Integer> {

    List<WiningBallot> findByCreateDate(Date date);

    @Query("SELECT w  FROM WiningBallot w  Left join fetch w.lottery l  ")
    List<WiningBallot> GetAllRows();

}
