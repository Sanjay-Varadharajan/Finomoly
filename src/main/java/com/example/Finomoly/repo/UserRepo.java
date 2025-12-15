package com.example.Finomoly.repo;

import com.example.Finomoly.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {


    Optional<User> findByUserMail(String userMail);
}
