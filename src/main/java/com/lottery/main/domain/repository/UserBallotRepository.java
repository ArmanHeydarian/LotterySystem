package com.lottery.main.domain.repository;

import com.lottery.main.domain.model.UserBallot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserBallotRepository extends JpaRepository<UserBallot, Integer> {


}
