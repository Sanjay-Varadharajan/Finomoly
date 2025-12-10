package com.example.Finomoly.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Expenses {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expenseId;

    private String expenseCategory;

    private double expensesAmount;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime expenseDate;

    private boolean isAnomaly;

    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    public Expenses(boolean isAnomaly, double expensesAmount, String expenseCategory) {
        this.isAnomaly = isAnomaly;
        this.expensesAmount = expensesAmount;
        this.expenseCategory = expenseCategory;
    }
}
