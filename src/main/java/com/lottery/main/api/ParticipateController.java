package com.lottery.main.api;


import com.lottery.main.domain.dto.UserBallotDto;
import com.lottery.main.domain.dto.WiningBallotDto;
import com.lottery.main.service.ParticipateService;
import com.lottery.main.service.WiningBallotService;
import org.springframework.beans.factory.annotation.Autowired;
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



    @PostMapping(value = "/addNewUserBallot")
    public @ResponseBody ResponseEntity<String> addNewUserBallot(@RequestBody UserBallotDto userBallotDto) {
        Authentication authentication;
        // Get User Name for finding User Entity by name
        try {
            authentication = SecurityContextHolder.getContext().getAuthentication();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User is not authenticate");
        }

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            // Add new Rate to Repository and then Database
            try {
                return participateService.addUserBallot(userBallotDto, authentication.getName());
            }
            catch (Exception e)
            {
                return ResponseEntity.badRequest().body("Something went wrong: " + e.getMessage());
            }
        }

        return ResponseEntity.badRequest().body("User is not authenticated");
    }
}
