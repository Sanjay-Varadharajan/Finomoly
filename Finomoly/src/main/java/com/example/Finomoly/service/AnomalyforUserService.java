package com.example.Finomoly.service;

import com.example.Finomoly.dto.UserAnomalyDto;
import com.example.Finomoly.model.User;
import com.example.Finomoly.repo.ExpenseRepo;
import com.example.Finomoly.repo.IncomeRepo;
import com.example.Finomoly.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
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
        Optional<User> userOptional = userRepo.findByUserMail(principal.getName());

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        UserAnomalyDto userAnomalyDto = new UserAnomalyDto();

        double totalIncome = incomeRepo.getTotalIncome(principal.getName());
        double totalExpense = expenseRepo.getTotalExpense(principal.getName());
        double totalSavings = totalIncome - totalExpense;
        double highestExpense = expenseRepo.getMaxExpense(principal.getName());
        String highestExpenseCategory = expenseRepo.findCategoryOfMaxExpense(principal.getName());

        if (highestExpenseCategory == null) {
            highestExpenseCategory = "N/A";
        }
        userAnomalyDto.setHighestExpense(highestExpense);
        userAnomalyDto.setTotalIncome(totalIncome);
        userAnomalyDto.setTotalSavings(totalSavings);
        userAnomalyDto.setMostSpentCategory(highestExpenseCategory);
        userAnomalyDto.setTotalExpense(totalExpense);

        return ResponseEntity.ok(userAnomalyDto);

    }
}
