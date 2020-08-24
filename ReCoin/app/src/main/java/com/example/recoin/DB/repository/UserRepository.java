package com.example.recoin.DB.repository;

import android.content.Context;
import android.util.Log;

import com.example.recoin.DB.AppDB;
import com.example.recoin.Model.User;
import com.example.recoin.Retrofit.RetrofitConfig;
import com.example.recoin.Retrofit.Service.RequestResult;
import com.example.recoin.Retrofit.Service.UserService;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private UserService userService; //retrofit
    private AppDB db; //DAO->room
    private RetrofitConfig retro = new RetrofitConfig();
    //pega as credenciais do user to shared preferences
    private static final String USER_TYPE = "admin";
    private static final String USER_LOGIN = "rcoutelo";
    private static final String USER_PASSWORD = "senhasecreta";


    public UserRepository (Context context) {
        this.db = AppDB.getInstance(context);
        this.userService = new RetrofitConfig().getService(UserService.class);
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


    public void syncUsersFromApi(final RequestResult requestResult) {
        Call<List<User>> call = userService.getAllUsers(USER_TYPE, USER_LOGIN, USER_PASSWORD);
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


    public void createUser(MultipartBody.Part file, Map<String, RequestBody> params, double amount, RequestResult requestResult) {
            try {
                Call<User> call = userService.add(USER_TYPE, USER_LOGIN, USER_PASSWORD,amount,file, params);
                Log.e("CALL_REQUEST", call.request().body().toString());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()) {
                            User user = response.body();
                            Log.e("CALL_RESPONSE", response.body().toString());
                            UserRepository.this.insertUser(user);
                            requestResult.returnSuccess(user);
                        } else {
                            Log.d("REPO_CALL_ADD_USER", response.message());
                            requestResult.returnError("Erro na criação do Usuário! Error message: \n" + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("REPO_CALL_ADD_USER", t.getMessage());
                        requestResult.returnError("Erro na criação do Usuário! Error message: \n" + t.getMessage());
                    }
                });
            } catch (Exception e) {
                Log.e("REP_SALVANDO_TUDO", e.getMessage());
            }


    }

        public void updateUser(User user, final RequestResult requestResult){
            Call<User> call = userService.update(USER_TYPE, USER_LOGIN, USER_PASSWORD, user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    UserRepository.this.updateUser(user);
                    requestResult.returnSuccess(user);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    requestResult.returnError("Erro na autalização do Usuário! Error message: \n" + t.getMessage());
                }
            });
    }



}
