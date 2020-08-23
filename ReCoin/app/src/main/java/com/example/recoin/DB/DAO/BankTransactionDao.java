package com.example.recoin.DB.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.recoin.Model.BankAccount;
import com.example.recoin.Model.BankTransaction;

import java.util.List;

@Dao
public interface BankTransactionDao {

    @Query("Select * from BankTransactions ORDER BY BankAccountbankAccountID, transactionID")
    List<BankTransaction> getAll();

    @Query("Select * from BankTransactions where TransactionID = :transactionID ")
    BankTransaction getByID(String transactionID);

    @Query("Select * from BankTransactions where BankAccountbankAccountID = :accountID ")
    BankTransaction getByAccount(int accountID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<BankTransaction> bankTransactions);

    @Insert
    void insert(BankTransaction bankTransaction);

    @Update
    void update(BankTransaction bankTransaction);

    @Delete
    void delete(BankTransaction bankTransaction);
}
