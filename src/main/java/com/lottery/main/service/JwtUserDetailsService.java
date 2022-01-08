package com.lottery.main.service;

import java.util.HashSet;
import java.util.Set;

import com.lottery.main.domain.model.User;
import com.lottery.main.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

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




}