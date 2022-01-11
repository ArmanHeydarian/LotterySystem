package com.lottery.main.api;

import com.lottery.main.domain.dto.UserBallotDto;
import com.lottery.main.service.JwtUserDetailsService;
import com.lottery.main.service.ParticipateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/participate")
public class ParticipateController {

    @Autowired
    private ParticipateService participateService;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    private static final Logger logger = LogManager.getLogger(ParticipateController.class);

    //--------------------------------------------------------------------
    @PostMapping(value = "/addNewUserBallot")
    public @ResponseBody ResponseEntity<String> addNewUserBallot(@RequestBody UserBallotDto userBallotDto)  {

        // Find User Entity by name
        ResponseEntity<String> UserEntityResponse= jwtUserDetailsService.findUserNameInContext();
        if (UserEntityResponse.getStatusCode() ==  HttpStatus.OK) {
            // Add new Ballot to Repository for persisting in Database
            try {
                return participateService.addUserBallot(userBallotDto,UserEntityResponse.getBody() );
            }
            catch (Exception e)
            {
                logger.warn("Something went wrong :" + e.getMessage());
                return ResponseEntity.badRequest().body("Something went wrong: " + e.getMessage());
            }
        }
        return ResponseEntity.badRequest().body("User is not authenticated");
    }
}
