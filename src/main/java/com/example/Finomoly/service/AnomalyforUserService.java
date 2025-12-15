package com.example.Finomoly.service;

import com.example.Finomoly.dto.AnomalyDto;
import com.example.Finomoly.dto.UserAnomalyDto;
import com.example.Finomoly.model.Expenses;
import com.example.Finomoly.model.User;
import com.example.Finomoly.repo.ExpenseRepo;
import com.example.Finomoly.repo.IncomeRepo;
import com.example.Finomoly.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class AnomalyforUserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ExpenseRepo expenseRepo;

    @Autowired
    IncomeRepo incomeRepo;


    public ResponseEntity<UserAnomalyDto> useranomaly(Principal principal) {

        Optional<User> userOptional =
                userRepo.findByUserMail(principal.getName());

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        User user = userOptional.get();

        UserAnomalyDto userAnomalyDto = new UserAnomalyDto();
        double totalIncome = incomeRepo.getTotalIncome(user.getUserMail());
        double totalExpense = expenseRepo.getTotalExpense(user.getUserMail());
        double highestExpense = expenseRepo.getMaxExpense(user.getUserMail());
        String highestExpenseCategory =
                expenseRepo.findCategoryOfMaxExpense(user.getUserMail());

        double totalSavings = totalIncome - totalExpense;

        userAnomalyDto.setTotalIncome(totalIncome);
        userAnomalyDto.setTotalExpense(totalExpense);
        userAnomalyDto.setTotalSavings(totalSavings);
        userAnomalyDto.setHighestExpense(highestExpense);
        userAnomalyDto.setMostSpentCategory(
                highestExpenseCategory != null ? highestExpenseCategory : "N/A"
        );

        List<Expenses> allExpenses = expenseRepo.findAllByUser(user);

        List<AnomalyDto> anomalies = allExpenses.stream()
                .map(e -> new AnomalyDto(e, "High expense"))
                .toList();

        userAnomalyDto.setAnomalies(anomalies);

        return ResponseEntity.ok(userAnomalyDto);
    }

}
