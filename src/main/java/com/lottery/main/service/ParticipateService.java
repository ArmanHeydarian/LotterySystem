package com.lottery.main.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lottery.main.domain.dto.UserBallotDto;
import com.lottery.main.domain.model.Lottery;
import com.lottery.main.domain.model.UserBallot;
import com.lottery.main.domain.model.User;
import com.lottery.main.domain.repository.LotteryRepository;
import com.lottery.main.domain.repository.UserBallotRepository;
import com.lottery.main.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
public class ParticipateService {
    @Autowired
    private UserBallotRepository userBallotRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LotteryRepository lotteryRepository;
    @Autowired
    private ObjectMapper jacksonObjectMapper;

    public ResponseEntity<String> addUserBallot(UserBallotDto userBallotDto , String userName) throws Exception
    {
        UserBallot userBallot= jacksonObjectMapper.convertValue(userBallotDto, new TypeReference<UserBallot>(){});
        Date date =new Date();
        userBallot.setCreateDate(date);

        //Find Lottery by Id and Add it to UserBallot Model
        Optional<Lottery> lottery = lotteryRepository.findById(userBallotDto.getLotteryId());
        if (lottery != null)
            userBallot.setLottery(lottery.get());
        else
            return ResponseEntity.badRequest().body("Lottery Id was not found");

        //Find User by Id and Add it to Rate Model
        User user = userRepository.findByUsername(userName);
        if (user != null)
            userBallot.setUser(user);
        else
            return ResponseEntity.badRequest().body("User Id was not found");

        // Add requested lotteryRate to poductRateRepository
        userBallotRepository.save(userBallot);

        return ResponseEntity.ok("Rate Saved Successfully");
    }



}
