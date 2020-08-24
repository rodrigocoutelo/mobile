package com.example.recoin.DB.repository;

import android.content.Context;

import com.example.recoin.DB.AppDB;
import com.example.recoin.Model.Avatar;
import com.example.recoin.Model.User;
import com.example.recoin.Retrofit.RetrofitConfig;
import com.example.recoin.Retrofit.Service.AvatarService;
import com.example.recoin.Retrofit.Service.RequestResult;
import com.example.recoin.Retrofit.Service.UserService;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AvatarRepository {

    private AvatarService avatarService;
    private AppDB db;


    //pega as credenciais do user to shared preferences
    private static final String USER_TYPE = "admin";
    private static final String USER_LOGIN = "rcoutelo";
    private static final String USER_PASSWORD = "senhasecreta";

    public AvatarRepository(Context context){
        this.db = AppDB.getInstance(context);
        this.avatarService = new RetrofitConfig().getService(AvatarService .class);
    }

    public AvatarRepository(AppDB instance){
        this.db = instance;
    }

    private void insertAvatar(List<Avatar> avatars) {
        db.avatarDao().insertAll(avatars);
    }

    private void insertAvatar(Avatar avatar){
        db.avatarDao().insert(avatar);
    }

    private void updateAvatar(Avatar avatar){
        if (!avatar.get_id().isEmpty()) {
            db.avatarDao().update(avatar);
        }
    }
    private void deleteAvatar(Avatar avatar) {
        if (!avatar.get_id().isEmpty()) {
            db.avatarDao().delete(avatar);
        }
    }

    public List<Avatar> listAllSAvatars(){
        return db.avatarDao().getAll();
    }

    public Avatar getAvatarById(String avatarID) {
        return db.avatarDao().getByID(avatarID);
    }

    public void syncAvatarsFromApi(final RequestResult requestResult) {
        Call<List<Avatar>> call = avatarService.list(USER_TYPE, USER_LOGIN, USER_PASSWORD);
        call.enqueue(new Callback<List<Avatar>>() {
            @Override
            public void onResponse(Call<List<Avatar>> call, Response<List<Avatar>> response) {
                List<Avatar> avatars = response.body();
                AvatarRepository.this.insertAvatar(avatars);
                requestResult.returnSuccess(avatars);
            }

            @Override
            public void onFailure(Call<List<Avatar>> call, Throwable t) {
                requestResult.returnError("Erro na sincronização! Error message: \n" + t.getMessage());
            }
        });

    }


    public void uploadAvatar(String userID, MultipartBody.Part image, final RequestResult requestResult) {
        Call<Avatar> call = avatarService.upload(USER_TYPE, USER_LOGIN, USER_PASSWORD, userID, image );
        call.enqueue(new Callback<Avatar>() {

            @Override
            public void onResponse(Call<Avatar> call, Response<Avatar> response) {
                if(response.isSuccessful()) {
                    Avatar avatar = response.body();
                    AvatarRepository.this.insertAvatar(avatar);
                    requestResult.returnSuccess(avatar);
                } else {
                    requestResult.returnError("Erro no upload do Avatar! Error message: \n" + response.body());
                }
            }

            @Override
            public void onFailure(Call<Avatar> call, Throwable t) {
                requestResult.returnError("Erro na criação do Avatar no backEnd! Error message: \n" + t.getMessage());
            }
        });

    }


}
