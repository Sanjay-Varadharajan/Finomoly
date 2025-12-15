package com.example.Finomoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsDto {

    private long totalUsers;

    private double totalIncome;

    private double totalExpenses;
}
