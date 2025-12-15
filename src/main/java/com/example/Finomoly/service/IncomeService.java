package com.example.Finomoly.service;


import com.example.Finomoly.dto.ExpenseDto;
import com.example.Finomoly.dto.IncomeDto;
import com.example.Finomoly.model.Income;
import com.example.Finomoly.model.User;
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
public class IncomeService {

    @Autowired
    IncomeRepo incomeRepo;

    @Autowired
    UserRepo userRepo;


    public ResponseEntity<String> addincome(IncomeDto incomeDto, Principal principal) {
        Optional<User> exists=userRepo.findByUserMail(principal.getName());

        if(exists.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Exists");
        }


        Income income=new Income();
        User user=exists.get();

        income.setIncomeAmount(incomeDto.getIncomeAmount());
        income.setUser(user);



        incomeRepo.save(income);

        return ResponseEntity.status(HttpStatus.OK).body("Income Added");
    }


    public ResponseEntity<List<IncomeDto>> viewincome(Principal principal) {

        Optional<User> userexists=userRepo.findByUserMail(principal.getName());

        if(userexists.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<IncomeDto> incomeDto=incomeRepo.findByUser_UserMail(principal.getName())
                .stream()
                .map(IncomeDto::new)
                .toList();

        return ResponseEntity.ok(incomeDto);

    }

    public ResponseEntity<List<IncomeDto>> sortedincomebydesc(Principal principal) {
        Optional<User> userOptional=userRepo.findByUserMail(principal.getName());

        if(userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<IncomeDto> incomeSorted=incomeRepo.findByUser_UserMailOrderByIncomeDateDesc(principal.getName())
                .stream().map(IncomeDto::new).toList();

        return ResponseEntity.ok(incomeSorted);

    }

    public ResponseEntity<List<IncomeDto>> sortedincomebyasc(Principal principal) {
        Optional<User> userOptional=userRepo.findByUserMail(principal.getName());

        if(userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<IncomeDto> incomeSorted=incomeRepo.findByUser_UserMailOrderByIncomeDateAsc(principal.getName())
                .stream().map(IncomeDto::new).toList();

        return ResponseEntity.ok(incomeSorted);
    }

    public ResponseEntity<Double> totalincome(Principal principal) {
        Optional<User> optionalUser=userRepo.findByUserMail(principal.getName());

        if(optionalUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        double total=incomeRepo.getTotalIncome(principal.getName());

        return ResponseEntity.ok(total);
    }

    public ResponseEntity<String> deleteexpense(int incomeId, Principal principal) {
        Optional<User> userOptional=userRepo.findByUserMail(principal.getName());
        if(userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not FOund");
        }
        incomeRepo.deleteById(incomeId);
        return ResponseEntity.status(HttpStatus.OK).body("Income Removed");
    }
}
