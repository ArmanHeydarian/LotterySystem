package com.lottery.main.service;

import java.util.HashSet;
import java.util.Set;

import com.lottery.main.api.ParticipateController;
import com.lottery.main.domain.model.User;
import com.lottery.main.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    private static final Logger logger = LogManager.getLogger(JwtUserDetailsService.class);

    //--------------------------------------------------------------------
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User dbUser= userRepository.findByUsername(userName);
        // Check if user exists in resource and has not been blocked by Admin already
        if (dbUser!=null && !dbUser.isBlocked()) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(dbUser.getRole()));
            return new org.springframework.security.core.userdetails.User(dbUser.getUsername(), dbUser.getPassword(), grantedAuthorities);
        }
        else
            //Return nothing to Authentication Manager. The Error will be handled in UserController
            return null;


    }
    //--------------------------------------------------------------------
    public ResponseEntity<String> findUserNameInContext ()  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken))
            return ResponseEntity.ok(authentication.getName());
        else {
            logger.warn("User is not authenticated");
            return ResponseEntity.badRequest().body("User is not authenticated");
        }

    }

}