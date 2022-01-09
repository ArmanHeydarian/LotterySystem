package com.lottery.main.api;

//----------
import com.lottery.main.domain.dto.LotteryDto;
import com.lottery.main.domain.dto.UserBallotDto;
import com.lottery.main.domain.model.Lottery;
import com.lottery.main.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/lottery")
public class LotteryController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LotteryService lotteryService;
    //--------------------------------------------------------------------
    @PostMapping(value = "/add")
    public @ResponseBody  ResponseEntity<String> addNewLottery(@RequestBody LotteryDto newLottery) {
        try {
            return lotteryService.addLottery(newLottery);

        } catch (Exception e) {

            return ResponseEntity.badRequest().body("Something went wrong :" + e.getMessage());
        }
    }
    //-----------------------------------------------------------------------------------
    @GetMapping(value = "/getall")
    public ResponseEntity<List<Lottery>> getAllLottery() {
        try {
            List list  = lotteryService.getAllLotteries();
            return new ResponseEntity<List<Lottery>>(list, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    //--------------------------------------------------------------------------------------
    @DeleteMapping(value = "/deleteLottery")
    public ResponseEntity<?> deleteLottery(@RequestParam int LotteryId  ) {
        try {
            return lotteryService.deleteLottery(LotteryId);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong :" + e.getMessage());

        }
    }
    //--------------------------------------------------------------------------------------
    @PutMapping(value = "/editLottery")
    public ResponseEntity<?>  editLottery(@RequestParam int lotteryId) {
        try {
            return lotteryService.editLottery(lotteryId);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong :" + e.getMessage());

        }
    }



}

