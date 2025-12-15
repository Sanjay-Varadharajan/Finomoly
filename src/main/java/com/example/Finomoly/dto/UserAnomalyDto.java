package com.example.Finomoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnomalyDto {


    private double totalIncome;

    private double totalExpense;

    private double totalSavings;

    private double highestExpense;

    private String mostSpentCategory;

    private List<AnomalyDto> anomalies;
}
