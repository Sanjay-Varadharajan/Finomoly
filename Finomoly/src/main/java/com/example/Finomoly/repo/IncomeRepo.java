package com.example.Finomoly.repo;

import com.example.Finomoly.model.Expenses;
import com.example.Finomoly.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IncomeRepo extends JpaRepository<Income,Integer> {

    List<Income> findByUser_UserId(int userId);


    List<Income> findByUser_UserMail(String userMail);

    List<Income> findByUser_UserMailOrderByIncomeDateDesc(String userMail);

    List<Income> findByUser_UserMailOrderByIncomeDateAsc(String userMail);


    @Query("SELECT COALESCE(SUM(i.incomeAmount), 0) FROM Income i")
    double sumAllIncome();


    @Query("SELECT COALESCE(SUM(i.incomeAmount), 0) FROM Income i WHERE i.user.userMail = :userMail")
    double getTotalIncome(@Param("userMail") String userMail);


}
