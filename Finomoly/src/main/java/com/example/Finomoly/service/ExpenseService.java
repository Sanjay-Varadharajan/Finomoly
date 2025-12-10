package com.example.Finomoly.service;


import com.example.Finomoly.dto.ExpenseDto;
import com.example.Finomoly.model.Expenses;
import com.example.Finomoly.model.User;
import com.example.Finomoly.repo.ExpenseRepo;
import com.example.Finomoly.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {


    @Autowired
    ExpenseRepo expenseRepo;

    @Autowired
    UserRepo userRepo;


    public ResponseEntity<String> addExpenses(ExpenseDto expenses, Principal principal) {
        Optional<User> userexists=userRepo.findByUserMail(principal.getName());

        if(userexists.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Exists");
        }
        Expenses usable=new Expenses();
        User useobj=userexists.get();

        usable.setExpensesAmount(expenses.getExpenseAmount());
        usable.setExpenseCategory(expenses.getExpenseCategory());
        usable.setUser(useobj);

        expenseRepo.save(usable);

        return ResponseEntity.status(HttpStatus.OK).body("Expense Added");
    }

    public ResponseEntity<List<ExpenseDto>> viewexpense(Principal principal) {
        Optional<User> userexists=userRepo.findByUserMail(principal.getName());

        if(userexists.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        List<ExpenseDto> expensesList=expenseRepo.findByUser_UserMail(principal.getName()).stream().map(ExpenseDto::new).toList();
        return ResponseEntity.ok(expensesList);


    }

    public ResponseEntity<List<ExpenseDto>> sortedexpense(Principal principal) {
        Optional<User> user=userRepo.findByUserMail(principal.getName());

        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<ExpenseDto> returnDto=expenseRepo.findByUser_UserMailOrderByExpenseDateDesc(principal.getName())
                .stream().map(ExpenseDto::new).toList();

        return ResponseEntity.ok(returnDto);
    }

    public ResponseEntity<List<ExpenseDto>> sortedexpensebyasc(Principal principal) {
        Optional<User> user=userRepo.findByUserMail(principal.getName());

        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<ExpenseDto> returnDto=expenseRepo.findByUser_UserMailOrderByExpenseDateAsc(principal.getName())
                .stream().map(ExpenseDto::new).toList();

        return ResponseEntity.ok(returnDto);
    }

    public ResponseEntity<Double> totalexpense(Principal principal) {
        Optional<User> user=userRepo.findByUserMail(principal.getName());

        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        double totalExp=expenseRepo.getTotalExpense(principal.getName());
        return ResponseEntity.ok(totalExp);
    }
}
