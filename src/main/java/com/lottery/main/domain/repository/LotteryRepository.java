package com.lottery.main.domain.repository;

import java.util.List;
import com.lottery.main.domain.model.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface LotteryRepository extends JpaRepository<Lottery, Integer> {


}
