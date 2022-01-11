package com.lottery.main.api;


import com.lottery.main.domain.dto.LotteryDto;
import com.lottery.main.domain.model.Lottery;
import com.lottery.main.domain.model.WiningBallot;
import com.lottery.main.service.LotteryService;
import com.lottery.main.service.WinningBallotService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path="/api/winning")
public class WinningController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private WinningBallotService winningBallotService;

    private static final Logger logger = LogManager.getLogger(WinningController.class);
    //-----------------------------------------------------------------------------------
    @GetMapping(value = "/getWinningBallot")
    public ResponseEntity<List<WiningBallot>> getWinningBallot(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  Date date) {
        try {
            List<WiningBallot> list  = winningBallotService.getWiningBallotByDate(date);
            return new ResponseEntity<List<WiningBallot>>(list, HttpStatus.OK);
        }
        catch (Exception e) {
            logger.warn("Something went wrong :" + e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }




}

