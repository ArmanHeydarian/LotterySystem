package com.lottery.main.api;

import com.lottery.main.api.security.JwtTokenUtil;
import com.lottery.main.domain.dto.AuthenticationRequest;
import com.lottery.main.domain.dto.AuthenticatonResponse;
import com.lottery.main.domain.dto.UserDto;
import com.lottery.main.service.JwtUserDetailsService;
import com.lottery.main.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    private static final Logger logger = LogManager.getLogger(UserController.class);

    //----------------------------------------------------------------------------------------
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?>  signUp(@RequestBody UserDto userDto) {
        try {
            return userService.addUserToDb(userDto);
        }
        catch (Exception e) {
            logger.warn("Something went wrong :" + e.getMessage());
            return ResponseEntity.badRequest().body("Something went wrong :" + e.getMessage());
        }
    }
    //----------------------------------------------------------------------------------------
    @RequestMapping(value = "/blockuser", method = RequestMethod.POST)
    public ResponseEntity<?>  blockUser(@RequestParam int userId) {
        try {
            return userService.blockUser(userId);
        }
        catch (Exception e) {
            logger.warn("Something went wrong :" + e.getMessage());
            return ResponseEntity.badRequest().body("Something went wrong :" + e.getMessage());

        }
    }
    //----------------------------------------------------------------------------------------
    @RequestMapping(value = "/unblockuser", method = RequestMethod.POST)
    public ResponseEntity<?>  unBlockUser(@RequestParam int userId) {
        try {
            return userService.unBlockUser(userId);
        }
        catch (Exception e) {
            logger.warn("Something went wrong :" + e.getMessage());
            return ResponseEntity.badRequest().body("Something went wrong :" + e.getMessage());
        }
    }
    //----------------------------------------------------------------------------------------

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> signIn(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        // First step checks user credential
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Incorrect Username and Password");
        }
        // If User exists then Generate a new JWT token and send it to user
        try {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthenticatonResponse(token));
        } catch (Exception e) {
            logger.warn("Something went wrong :" + e.getMessage());
            return ResponseEntity.badRequest().body("Something went wrong in token generating part");
        }

    }

}
