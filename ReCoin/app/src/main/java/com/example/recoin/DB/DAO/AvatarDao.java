package com.example.recoin.DB.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.recoin.Model.Avatar;
import com.example.recoin.Model.BankAccount;

import java.util.List;

@Dao
public interface AvatarDao {

    @Query("Select * from Avatar")
    List<Avatar> getAll();

    @Query("Select * from Avatar where AvatarID = :avatarID ")
    Avatar getByID(String avatarID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Avatar> avatars);

    @Insert
    void insert(Avatar avatar);

    @Update
    void update(Avatar avatar);

    @Delete
    void delete(Avatar avatar);
}
