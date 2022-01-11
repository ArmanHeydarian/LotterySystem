package com.lottery.main.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lottery.main.domain.dto.LotteryCommentDto;
import com.lottery.main.domain.model.*;
import com.lottery.main.repository.*;
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
    //----------------------------------------------- -------------------------------------
    public ResponseEntity<String> editLottery (int lotteryId) throws RuntimeException
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
    public List<Lottery> getAllLotteries() throws Exception {
        return lotteryRepository.findAll();
    }
    //------------------------------------------------------------------------------------

    public ResponseEntity<String> deleteLottery(int lotteryId) throws Exception {
        lotteryRepository.deleteById(lotteryId);
        return ResponseEntity.ok("Lottery Has Been Deleted Successfully");

    }
    //------------------------------------------------------------------------------------
    public ResponseEntity<String> addLotteryComment(LotteryCommentDto lotteryCommentDto, String userName) throws Exception {

        //Map Eatery DTO to LotteryComment Model
        LotteryComment lotteryComment = jacksonObjectMapper.convertValue(lotteryCommentDto, new TypeReference<LotteryComment>() {});
        lotteryComment.setCreateDate(new Date());

        //Find Lottery by Id and Add it to Comment Model
        Optional<Lottery> lottery = lotteryRepository.findById(lotteryCommentDto.getLotteryId());
        if (lottery != null)
            lotteryComment.setLottery(lottery.get());
        else
            return ResponseEntity.badRequest().body("Lottery Id was not found");

        //Find User by Id and Add it to Comment Model
        User user = userRepository.findByUsername(userName);
        if (user != null)
            lotteryComment.setUser(user);
        else
            return ResponseEntity.badRequest().body("User Id was not found");

        // Add requested lotteryComment to CommentRepository
        lotteryCommentRepository.save(lotteryComment);

        return ResponseEntity.ok("Comment Saved Successfully");
    }

}
