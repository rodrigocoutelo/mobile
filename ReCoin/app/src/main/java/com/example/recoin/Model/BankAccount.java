package com.example.recoin.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.recoin.DB.TimeStampConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Date;

@Entity(tableName = "BankAccount")
@JsonIgnoreProperties({"uid","__v", "createdAt","updatedAt"})
public class BankAccount implements Serializable {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name="bankAccountID")
    private String _id;
    private String bank_branch;
    private String code;
    @Embedded(prefix = "user")
    @NonNull
    private User user;
    private double account_balance;
    private int status;


    public void set_id(@NonNull String _id) {
        this._id = _id;
    }

    public void setBank_branch(String bank_branch) {
        this.bank_branch = bank_branch;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAccount_balance(double account_balance) {
        this.account_balance = account_balance;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String get_id() {
        return _id;
    }

    public String getBank_branch() {
        return bank_branch;
    }

    public String getCode() {
        return code;
    }

    public User getUser() {
        return user;
    }

    public double getAccount_balance() {
        return account_balance;
    }

    public int getStatus() {
        return status;
    }

   }
