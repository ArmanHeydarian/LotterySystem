package com.lottery.main.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lottery.main.domain.dto.UserBallotDto;
import com.lottery.main.domain.model.Lottery;
import com.lottery.main.domain.model.UserBallot;
import com.lottery.main.domain.model.User;
import com.lottery.main.domain.model.WiningBallot;
import com.lottery.main.repository.LotteryRepository;
import com.lottery.main.repository.UserBallotRepository;
import com.lottery.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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
    //--------------------------------------------------------------------
    public List<UserBallot> getUserBallot(String userName)
    {
        //Find User by ID and Add it to UserBallot Model
        User user = userRepository.findByUsername(userName);
        if (user != null)
            return userBallotRepository.findByUserId(user.getId());
        else
            return null;
    }
    //--------------------------------------------------------------------
    public ResponseEntity<String> addUserBallot(UserBallotDto userBallotDto, String userName) throws Exception {

        UserBallot userBallot = jacksonObjectMapper.convertValue(userBallotDto, new TypeReference<UserBallot>() {});
        userBallot.setCreateDate(new Date());

        //Find Lottery by ID and Add it to UserBallot Model
        Optional<Lottery> lottery = lotteryRepository.findById(userBallotDto.getLotteryId());
        if (lottery != null)
            userBallot.setLottery(lottery.get());
        else
            return ResponseEntity.badRequest().body("Lottery Id was not found");

        //Find User by ID and Add it to UserBallot Model
        User user = userRepository.findByUsername(userName);
        if (user != null)
            userBallot.setUser(user);
        else
            return ResponseEntity.badRequest().body("User Id was not found");

        // Check Condition of Ballot
        int[] ArrNumbers = convertToSortedArray(userBallotDto.getNumberList());
        Boolean Duplication = checkDuplicateInNumberList(ArrNumbers);
        Boolean isLengthCorrect = checkLengthOfUserNumberList(ArrNumbers, userBallot.getLottery().getBallotLength());
        Boolean isRangeCorrect = checkRangeOfUserNumberList(ArrNumbers , lottery.get().getMinNumber(), lottery.get().getMaxNumber());
        if (!isLengthCorrect)
            return ResponseEntity.ok("Ballot Length is not correct ");
        if (Duplication)
            return ResponseEntity.ok("Ballot contains some duplicated numbers ");
        if (!isRangeCorrect)
            return ResponseEntity.ok("Ballot number Range exceed");

        userBallot.setNumberList(String.join(",", Arrays.toString(ArrNumbers)));
        userBallotRepository.save(userBallot);
        return ResponseEntity.ok("Ballot Saved Successfully");
    }

    private int[] convertToSortedArray(String input) throws Exception {
        //Sorting Number List Ascending
        int[] strNumberSplit = Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt).toArray();
        Arrays.sort(strNumberSplit);
        return strNumberSplit;
        //Checking Duplicate Numbers

    }

    public Boolean checkDuplicateInNumberList(int[] input) throws Exception {
        int prev = -1;
        // Check duplicates for every array element
        for (int e : input) {
            // if two consecutive elements are found to be equal, so a duplicate is found
            if (e == prev) {
                return true;
            }
            // set the current element as previous
            prev = e;
        }
        // no duplicate is found
        return false;
    }

    private Boolean checkLengthOfUserNumberList(int[] input, int ExpectedLength) throws Exception {
        if (input.length > ExpectedLength || input.length < ExpectedLength)
            return false;
        else
            return true;
    }

    private Boolean checkRangeOfUserNumberList(int[] input, int min ,int max) throws Exception {
        for (int e : input)
        {
            if (e > max || e < min)
                return false;
        }
        return true;
    }




}
