package com.example.Finomoly.repo;

import com.example.Finomoly.model.Expenses;
import com.example.Finomoly.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepo extends JpaRepository<Expenses,Integer> {
    List<Expenses> findByUser_UserId(int userId);

    List<Expenses> findByUser_UserMail(String userMail);

    List<Expenses> findByUser_UserIdAndExpenseCategory(int userId,String expenseCategory);

    List<Expenses> findByUser_UserMailOrderByExpenseDateDesc(String userMail);

    List<Expenses> findByUser_UserMailOrderByExpenseDateAsc(String userMail);


    @Query("SELECT COALESCE(SUM(e.expensesAmount), 0) FROM Expenses e")
    double sumAllExpenses();

    @Query("SELECT AVG(e.expensesAmount) FROM Expenses e")
    double averageExpense();

    @Query("SELECT COALESCE(SUM(e.expensesAmount), 0) FROM Expenses e WHERE e.user.userMail = :userMail")
    double getTotalExpense(@Param("userMail") String userMail);

    @Query("SELECT COALESCE(MAX(e.expensesAmount),0) FROM Expenses e WHERE e.user.userMail = :userMail")
    double getMaxExpense(@Param("userMail")String userMail);

    @Query("SELECT e.expenseCategory FROM Expenses e WHERE e.user.userMail = :userMail AND e.expensesAmount = (SELECT MAX(e2.expensesAmount) FROM Expenses e2 WHERE e2.user.userMail = :userMail)")
    String findCategoryOfMaxExpense(@Param("userMail") String userMail);


    List<Expenses> findAllByUser(User user);

}
