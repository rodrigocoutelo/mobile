package com.example.recoin.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.recoin.DB.TimeStampConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@Entity(tableName = "BankTransactions")
@JsonIgnoreProperties({"uid","__v", "createdAt","updatedAt"})
public class BankTransaction {

    @Embedded(prefix = "BankAccount")
    @NonNull
    private BankAccount account;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="transactionID")
    private String _id;
    private int source_transaction;
    private double amount;


    public void setAccount(BankAccount account) {
        this.account = account;
    }

    public void set_id(@NonNull String _id) {
        this._id = _id;
    }

    public void setSource_transaction(int source_transaction) {
        this.source_transaction = source_transaction;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



    public BankAccount getAccount() {
        return account;
    }

    public String get_id() {
        return _id;
    }

    public int getSource_transaction() {
        return source_transaction;
    }

    public double getAmount() {
        return amount;
    }


}
