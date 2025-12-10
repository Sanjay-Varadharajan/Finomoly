package com.example.Finomoly.service;


import com.example.Finomoly.dto.AnomalyDto;
import com.example.Finomoly.dto.ExpenseDto;
import com.example.Finomoly.dto.StatsDto;
import com.example.Finomoly.dto.UserDto;
import com.example.Finomoly.enums.Role;
import com.example.Finomoly.model.Expenses;
import com.example.Finomoly.model.User;
import com.example.Finomoly.repo.ExpenseRepo;
import com.example.Finomoly.repo.IncomeRepo;
import com.example.Finomoly.repo.UserRepo;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ExpenseRepo expenseRepo;

    @Autowired
    IncomeRepo incomeRepo;


    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    public ResponseEntity<StatsDto> viewallstats() {
        StatsDto stats=new StatsDto();

        stats.setTotalExpenses(expenseRepo.sumAllExpenses());
        stats.setTotalIncome(incomeRepo.sumAllIncome());
        stats.setTotalUsers(userRepo.count());

        return ResponseEntity.ok(stats);
    }

    public ResponseEntity<List<AnomalyDto>> viewanomaly() {
        List<Expenses> expenses=expenseRepo.findAll();

        List<AnomalyDto> anomalyDtoList=new ArrayList<>();

        double avg= expenseRepo.averageExpense();

        for(Expenses ex:expenses){
            if (ex.getExpensesAmount()>avg*2) {
                ex.setAnomaly(true);

                anomalyDtoList.add(new AnomalyDto(
                        ex,
                        "Outlier detected:Significant jump from avg"
                ));
            }

            else{
                ex.setAnomaly(false);
            }
        }
        return ResponseEntity.ok(anomalyDtoList);



    }

    public ResponseEntity<String> addadmin(UserDto userDto, Principal principal) {
        Optional<User> loggin=userRepo.findByUserMail(principal.getName());

        if(loggin.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }

        User user=loggin.get();
        if(user.getUserRole()!= Role.ROLE_ADMIN){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Denied");
        }
        User admin=new User();
        admin.setUserName(userDto.getUserName().trim());
        admin.setUserMail(userDto.getUserMail().trim());
        admin.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
        admin.setUserRole(Role.ROLE_ADMIN);

        userRepo.save(admin);

        return ResponseEntity.status(HttpStatus.OK).body("Admin Added");
    }
}
