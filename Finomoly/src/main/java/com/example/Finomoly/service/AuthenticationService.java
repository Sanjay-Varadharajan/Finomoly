package com.example.Finomoly.service;

import com.example.Finomoly.dto.LoginDto;
import com.example.Finomoly.dto.UserDto;
import com.example.Finomoly.enums.Role;
import com.example.Finomoly.model.User;
import com.example.Finomoly.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<String> signup(UserDto userDto) {
        Optional<User> user = userRepo.findByUserMail(userDto.getUserMail());

        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("User Already Exists");
        }

            User usable = new User();
            usable.setUserName(userDto.getUserName());
            usable.setUserMail(userDto.getUserMail());
            usable.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
            usable.setUserRole(Role.ROLE_USER);

        userRepo.save(usable);

        return ResponseEntity.status(HttpStatus.CREATED).body("Signup Sucessfull");
    }

    public ResponseEntity<String> login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            loginDto.getUserMail().trim(),
                            loginDto.getUserPassword().trim()
                    ));

            //for storing session
            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = userRepo.findByUserMail(loginDto.getUserMail())
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

            return ResponseEntity.ok("Login successful! " +
                    "Email: " + user.getUserMail() +
                    " | Role: " + user.getUserRole());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }
    }

}