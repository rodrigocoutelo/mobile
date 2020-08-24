package com.example.recoin.DB.repository;

import android.content.Context;

import com.example.recoin.DB.AppDB;
import com.example.recoin.Model.BankAccount;
import com.example.recoin.Retrofit.RetrofitConfig;
import com.example.recoin.Retrofit.Service.BankAccountService;
import com.example.recoin.Retrofit.Service.RequestResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BankAccountRepository {

    private BankAccountService accountService;
    private AppDB db;
    private static final String USER_TYPE = "admin";
    private static final String USER_LOGIN = "rcoutelo";
    private static final String USER_PASSWORD = "senhasecreta";

    public BankAccountRepository(Context context) {
        this.db = AppDB.getInstance(context);
        accountService = new RetrofitConfig().getService(BankAccountService.class);
    }

    public BankAccountRepository(AppDB instance) {
        this.db = instance;
    }

    private void insertAccount(BankAccount account) {
        db.bankAccountDao().insert(account);
    }

    public void crateAccount(BankAccount account, final RequestResult requestResult) {
        Call<BankAccount> call = accountService.insert(USER_TYPE, USER_LOGIN, USER_PASSWORD, account);
        call.enqueue(new Callback<BankAccount>() {
            @Override
            public void onResponse(Call<BankAccount> call, Response<BankAccount> response) {
                if (response.isSuccessful()) {
                    BankAccount newAccount = response.body();
                    BankAccountRepository.this.insertAccount(newAccount);
                    requestResult.returnSuccess(newAccount);
                } else {
                    requestResult.returnError("Erro no criação Conta! Error message: \n" + response.body());
                }
            }

            @Override
            public void onFailure(Call<BankAccount> call, Throwable t) {
                requestResult.returnError("Erro na criação do conta no backEnd! Error message: \n" + t.getMessage());
            }

        });
    }
}