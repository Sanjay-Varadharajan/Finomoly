package com.example.Finomoly.dto;


import com.example.Finomoly.model.Expenses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {

    private int expenseId;

    private String expenseCategory;

    private double expenseAmount;

    private LocalDateTime expenseDate;


    public ExpenseDto(Expenses expenses) {
        this.expenseId=expenses.getExpenseId();
        this.expenseCategory = expenses.getExpenseCategory();
        this.expenseAmount = expenses.getExpensesAmount();
        this.expenseDate = expenses.getExpenseDate();
    }
}
