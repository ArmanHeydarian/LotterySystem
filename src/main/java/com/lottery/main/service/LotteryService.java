package com.lottery.main.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lottery.main.domain.model.*;
import com.lottery.main.domain.repository.*;
import com.lottery.main.domain.dto.LotteryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LotteryService {

    @Autowired
    private LotteryCommentRepository lotteryCommentRepository;
    @Autowired
    private UserBallotRepository userBallotRepository0;
    @Autowired
    private LotteryRepository lotteryRepository;
    @Autowired
    private WiningBallotRepository winingBallotRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper jacksonObjectMapper;

    //------------------------------------------------------------------------------------
    public ResponseEntity<String> addLottery(LotteryDto lotteryDto) throws Exception
    {
        Lottery lottery = jacksonObjectMapper.convertValue(lotteryDto, new TypeReference<Lottery>(){});
        lottery.setCreateDate(new Date());
        lotteryRepository.save(lottery);
        return ResponseEntity.ok("Lottery Saved Successfully");
    }

    //------------------------------------------------------------------------------------
    public ResponseEntity<String> editLottery (int lotteryId) throws Exception
    {
        Optional<Lottery> lottery = lotteryRepository.findById(lotteryId);
        if (lottery != null)
        {
            lottery.get().setBlocked(true);
            lotteryRepository.save(lottery.get());
            return ResponseEntity.ok("lottery Has Been Updated Successfully");
        }
        else
            return ResponseEntity.badRequest().body("lottery Id Not Found");
    }
    //------------------------------------------------------------------------------------
    /*public ResponseEntity<String> addLotteryComment(LotteryCommentDto lotteryCommentDto, String userName) throws Exception {

        //Map Eatery DTO to LotteryComment Model 
        LotteryComment lotteryComment = jacksonObjectMapper.convertValue(lotteryCommentDto, new TypeReference<LotteryComment>() {});
        Date date = new Date();
        lotteryComment.setCreateDate(date);

        //Find Lottery by Id and Add it to Comment Model
        Optional<Lottery> lottery = lotteryRepository.findById(lotteryCommentDto.getLotteryId());
        if (lottery != null)
            lotteryComment.setLottery(lottery.get());
        else
            return ResponseEntity.badRequest().body("Lottery Id was not found");

        //Find User by Id and Add it to Comment Model
        UserModel user = userRepository.findByUsername(userName);
        if (user != null)
            lotteryComment.setUser(user);
        else
            return ResponseEntity.badRequest().body("User Id was not found");

        // Add requested lotteryComment to poductCommentRepository
        poductCommentRepository.save(lotteryComment);

        return ResponseEntity.ok("Comment Saved Successfully");
    }*/

    //------------------------------------------------------------------------------------
    public List<Lottery> getAllLotteries() throws Exception {
        return lotteryRepository.findAll();
    }
    //------------------------------------------------------------------------------------

    /*@PersistenceContext
    EntityManager entityManager;
    public List<Lottery> findLotterysByParams(String title, Long price , Short rate) throws Exception {

        //Search multi properties on Lottery using CriteriaQuery
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Lottery> cq = cb.createQuery(Lottery.class);
        Root<Lottery> root = cq.from(Lottery.class);
        Fetch<Lottery, Category> lotterysCategory = root.fetch("category", JoinType.LEFT);
        Join<Lottery, Category> lotterys = (Join<Lottery, Category>) lotterysCategory;
        // Design Predicts for query
        List<Predicate> predicates = new ArrayList<>();
        if (title !=null && !title.equals("")) {
            predicates.add(cb.like(root.get("title"), "%" + title + "%"));
        }
        if (price!= null) {
            predicates.add(cb.equal(root.get("price"), price));
        }
        if (rate!= null) {
            predicates.add(cb.equal(root.get("avgRate"), rate));
        }

        cq.where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(cq).getResultList();
    }*/
    //------------------------------------------------------------------------------------

    public ResponseEntity<String> deleteLottery(int lotteryId) throws Exception {
        lotteryRepository.deleteById(lotteryId);
        return ResponseEntity.ok("Lottery Has Been Deleted Successfully");

    }

}
