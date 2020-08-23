package com.example.recoin.DB.repository;

import android.content.Context;

import com.example.recoin.DB.AppDB;
import com.example.recoin.Model.Avatar;
import com.example.recoin.Model.User;
import com.example.recoin.Retrofit.Service.AvatarService;

import java.util.List;

public class AvatarRepository {

    private AvatarService avatarService;
    private AppDB db;

    public AvatarRepository(Context context){
        this.db = AppDB.getInstance(context);
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

    public Avatar getAvatarByUser(User user) {
        return db.avatarDao().getByUser(user.get_id());
    }


}
