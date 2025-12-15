package com.example.Finomoly.service;

import com.example.Finomoly.model.User;
import com.example.Finomoly.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userRepo.findByUserMail(email)
                .orElseThrow(()->new RuntimeException("User Not Found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserMail())
                .password(user.getUserPassword())
                .roles(user.getUserRole().name().replace("ROLE_",""))
                .build();
    }
}
