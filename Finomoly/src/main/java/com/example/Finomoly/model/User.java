package com.example.Finomoly.model;


import com.example.Finomoly.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    private String userName;

    private String userMail;

    private String userPassword;

    @Enumerated(EnumType.STRING)
    private Role userRole;

    @OneToMany(mappedBy = "user")
    private List<Expenses> expenses;

    @OneToMany(mappedBy = "user")
    private List<Income> incomes;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime userCreatedOn;


    public User(String userName, String userMail, String userPassword, Role userRole) {
        this.userName = userName;
        this.userMail = userMail;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }
}
