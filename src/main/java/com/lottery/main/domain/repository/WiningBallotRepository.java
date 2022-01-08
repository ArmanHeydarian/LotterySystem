package com.lottery.main.domain.repository;

import com.lottery.main.domain.model.WiningBallot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WiningBallotRepository extends JpaRepository<WiningBallot, Integer> {


}
