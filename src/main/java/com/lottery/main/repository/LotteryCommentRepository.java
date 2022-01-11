package com.lottery.main.repository;

import com.lottery.main.domain.model.LotteryComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LotteryCommentRepository extends CrudRepository<LotteryComment, Integer> {


}
