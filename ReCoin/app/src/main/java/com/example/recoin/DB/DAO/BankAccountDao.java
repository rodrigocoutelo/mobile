package com.example.recoin.DB.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.recoin.Model.BankAccount;
import com.example.recoin.Model.User;

import java.util.List;

@Dao
public interface BankAccountDao {

    @Query("Select * from BankAccount")
    List<BankAccount> getAll();

    @Query("Select * from BankAccount where bankAccountID = :accountID ")
    BankAccount getByID(String... accountID);

    @Query("Select * from BankAccount where useruserID = :userID")
    BankAccount getByUser(int userID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<BankAccount> accounts);

    @Insert
    void insert(BankAccount account);

    @Update
    void update(BankAccount account);

    @Delete
    void delete(BankAccount account);

}
