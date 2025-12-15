package com.example.Finomoly.controller;


import com.example.Finomoly.dto.AnomalyDto;
import com.example.Finomoly.dto.ExpenseDto;
import com.example.Finomoly.dto.IncomeDto;
import com.example.Finomoly.dto.UserAnomalyDto;
import com.example.Finomoly.service.AnomalyforUserService;
import com.example.Finomoly.service.ExpenseService;
import com.example.Finomoly.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {


    

    @Autowired
    ExpenseService expenseService;

    @Autowired
    IncomeService incomeService;

    @Autowired
    AnomalyforUserService anomolyforUser;


    @PostMapping("/expenses/add")
    public ResponseEntity<String> addExpenses(@RequestBody ExpenseDto expenses, Principal principal){
        return expenseService.addExpenses(expenses,principal);
    }

    @PostMapping("/income/add")
    public ResponseEntity<String> addincome(@RequestBody IncomeDto incomeDto,Principal principal){
        return incomeService.addincome(incomeDto,principal);
    }

    @GetMapping("/expenses/view")
    public ResponseEntity<List<ExpenseDto>> viewexpense(Principal principal){
        return expenseService.viewexpense(principal);
    }

    @GetMapping("/income/view")
    public ResponseEntity<List<IncomeDto>> viewincome(Principal principal){
        return incomeService.viewincome(principal);
    }

    @GetMapping("/expenses/sortedbydesc")
    public ResponseEntity<List<ExpenseDto>> sortedexpensebydesc(Principal principal){
        return expenseService.sortedexpense(principal);
    }

    @GetMapping("/expenses/sortedbyasc")
    public ResponseEntity<List<ExpenseDto>> sortedexpensebyasc(Principal principal){
        return expenseService.sortedexpensebyasc(principal);
    }

    @GetMapping("/income/sortedbydesc")
    public ResponseEntity<List<IncomeDto>> sortedincomebydesc(Principal principal){
        return incomeService.sortedincomebydesc(principal);
    }

    @GetMapping("/income/sortedbyasc")
    public ResponseEntity<List<IncomeDto>> sortedincomebyasc(Principal principal){
        return incomeService.sortedincomebyasc(principal);
    }

    @GetMapping("/income/total")
    public ResponseEntity<Double> totalincome(Principal principal){
        return incomeService.totalincome(principal);
    }

    @GetMapping("/expense/total")
    public ResponseEntity<Double> totalexpense(Principal principal){
        return expenseService.totalexpense(principal);
    }

    @GetMapping("/anomaly")
    public ResponseEntity<UserAnomalyDto> getuseranomaly(Principal principal){
        return anomolyforUser.useranomaly(principal);
    }
    @DeleteMapping("/expense/delete")
    public ResponseEntity<String> deleteexpense(@RequestParam int expenseId,Principal principal){
        return expenseService.deleteexpense(expenseId,principal);
    }
    @DeleteMapping("/income/delete")
    public ResponseEntity<String> deleteincome(@RequestParam int incomeId,Principal principal){
        return incomeService.deleteexpense(incomeId,principal);
    }
}
