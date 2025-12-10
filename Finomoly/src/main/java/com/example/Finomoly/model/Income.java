package com.example.Finomoly.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int incomeId;

    private double incomeAmount;

    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime incomeDate;


}
