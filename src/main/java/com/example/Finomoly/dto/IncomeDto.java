package com.example.Finomoly.dto;


import com.example.Finomoly.model.Income;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeDto {

    private int incomeId;

    private double incomeAmount;

    private LocalDateTime incomeDate;


    public IncomeDto(Income income) {
        this.incomeId=income.getIncomeId();
        this.incomeAmount=income.getIncomeAmount();
        this.incomeDate=income.getIncomeDate();
    }
}
