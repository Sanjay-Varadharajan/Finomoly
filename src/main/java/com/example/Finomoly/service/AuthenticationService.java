package com.example.Finomoly.service;

import com.example.Finomoly.dto.LoginDto;
import com.example.Finomoly.dto.UserDto;
import com.example.Finomoly.enums.Role;
import com.example.Finomoly.model.User;
import com.example.Finomoly.repo.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;


import java.util.Map;
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

    public ResponseEntity<Map<String, String>> login(LoginDto loginDto, HttpServletRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUserMail(),
                            loginDto.getUserPassword()
                    )
            );

            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authentication);
            SecurityContextHolder.setContext(securityContext);

            request.getSession(true).setAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    securityContext
            );

            String role = authentication.getAuthorities().iterator().next().getAuthority();
            return ResponseEntity.ok(Map.of("role", role));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid Credentials"));
        }
    }


}