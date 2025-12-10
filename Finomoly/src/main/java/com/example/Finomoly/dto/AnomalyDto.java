package com.example.Finomoly.dto;

import com.example.Finomoly.model.Expenses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnomalyDto {

    private int expenseId;

    private String expenseCategory;

    private double expenseAmount;

    private LocalDateTime expenseDate;

    private String anomalyReason;

    public AnomalyDto(Expenses e, String reason){
        this.expenseId = e.getExpenseId();
        this.expenseCategory = e.getExpenseCategory();
        this.expenseAmount = e.getExpensesAmount();
        this.expenseDate = e.getExpenseDate();
        this.anomalyReason = reason;
    }

}
