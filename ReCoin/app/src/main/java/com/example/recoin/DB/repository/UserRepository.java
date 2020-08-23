package com.example.recoin.DB.repository;

import android.content.Context;

import com.example.recoin.DB.AppDB;
import com.example.recoin.Model.User;
import com.example.recoin.Retrofit.RetrofitConfig;
import com.example.recoin.Retrofit.Service.RequestResult;
import com.example.recoin.Retrofit.Service.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private UserService userService;
    private AppDB db;

    public UserRepository (Context context) {
        this.db = AppDB.getInstance(context);
    }

    public UserRepository (AppDB instance) {
        this.db = instance;
    }

    private void insertUser(List<User> users) {
        db.userDao().insertAll(users);
    }

    private void insertUser(User user){
        db.userDao().insert(user);
    }

    private void updateUser(User user){
        if (!user.get_id().isEmpty()) {
            db.userDao().update(user);
        }
    }
    private void deleteUser(User user) {
        if (!user.get_id().isEmpty()) {
            db.userDao().delete(user);
        }
    }

    public List<User> listAllSUsers(){
       return db.userDao().getAll();
    }

    public User getUserById(String userid) {
        return db.userDao().getByID(userid);
    }

    public List<User> listUserByName(String name) {
        return db.userDao().getByName(name);
    }


    public void syncUsersFromApi(String userType, String userLogin, String userPassword, final RequestResult requestResult) {
        Call<List<User>> call = userService.getAllUsers(userType, userLogin, userPassword);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                UserRepository.this.insertUser(users);
                requestResult.returnSuccess(users);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                requestResult.returnError("Erro na sincronização! Error message: \n" + t.getMessage());
            }
        });

    }
    public void createUser(String userType, String userLogin, String userPassword, User user, final RequestResult requestResult) {
        Call<User> call = userService.add(userType, userLogin, userPassword, user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                db.userDao().insert(user);
                requestResult.returnSuccess(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                requestResult.returnError("Erro na criação do Usuário! Error message: \n" + t.getMessage());
            }
        });

    }

        public void updateUser(String userType, String userLogin, String userPassword, User user, final RequestResult requestResult){
            Call<User> call = userService.update(userType, userLogin, userPassword, user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    db.userDao().update(user);
                    requestResult.returnSuccess(user);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    requestResult.returnError("Erro na autalização do Usuário! Error message: \n" + t.getMessage());
                }
            });
    }



}
