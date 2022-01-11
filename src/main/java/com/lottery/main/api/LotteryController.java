package com.lottery.main.api;

import com.lottery.main.domain.dto.LotteryCommentDto;
import com.lottery.main.domain.dto.LotteryDto;
import com.lottery.main.domain.model.Lottery;
import com.lottery.main.service.JwtUserDetailsService;
import com.lottery.main.service.LotteryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path="/api/lottery")
public class LotteryController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private LotteryService lotteryService;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    private static final Logger logger = LogManager.getLogger(LotteryController.class);

    //--------------------------------------------------------------------
    @PostMapping(value = "/add")
    public @ResponseBody  ResponseEntity<String> addNewLottery(@RequestBody LotteryDto newLottery) {
        try {
            return lotteryService.addLottery(newLottery);

        } catch (Exception e) {
            logger.warn("Something went wrong :" + e.getMessage());
            return ResponseEntity.badRequest().body("Something went wrong :" + e.getMessage());
        }
    }
    //-----------------------------------------------------------------------------------
    @GetMapping(value = "/getall")
    public ResponseEntity<List<Lottery>> getAllLottery() {
        try {
            List<Lottery> list  = lotteryService.getAllLotteries();
            return new ResponseEntity(list, HttpStatus.OK);
        }
        catch (Exception e) {
            logger.warn("Something went wrong :" + e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }
    //--------------------------------------------------------------------------------------
    @DeleteMapping(value = "/delete")
    public ResponseEntity<String> deleteLottery(@RequestParam int LotteryId  ) {
        try {
            return lotteryService.deleteLottery(LotteryId);
        }
        catch (Exception e) {
            logger.warn("Something went wrong :" + e.getMessage());
            return ResponseEntity.badRequest().body("Something went wrong :" + e.getMessage());
        }
    }
    //--------------------------------------------------------------------------------------
    @PutMapping(value = "/edit")
    public ResponseEntity<String>  editLottery(@RequestParam int lotteryId) {
        try {
            return lotteryService.editLottery(lotteryId);
        }
        catch (Exception e) {
            logger.warn("Something went wrong :" + e.getMessage());
            return ResponseEntity.badRequest().body("Something went wrong :" + e.getMessage());
        }
    }
    //--------------------------------------------------------------------------------------
    @PostMapping(value = "/putComment")
    public @ResponseBody ResponseEntity<String> addNewLotteryComment(@RequestBody LotteryCommentDto LotteryCommentDto) {

        // Find User Entity by name
        ResponseEntity<String> UserEntityResponse= jwtUserDetailsService.findUserNameInContext();
        if (UserEntityResponse.getStatusCode() ==  HttpStatus.OK) {
            // Add new Comment to Repository and then Database
            try {
                return lotteryService.addLotteryComment(LotteryCommentDto, UserEntityResponse.getBody());
            }
            catch (Exception e)
            {
                logger.warn("Something went wrong :" + e.getMessage());
                return ResponseEntity.badRequest().body("Something went wrong" + e.getMessage());
            }
        }
        return ResponseEntity.badRequest().body("User is not authenticated");
    }



}

