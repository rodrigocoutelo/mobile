package com.example.recoin.DB.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.recoin.Model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("Select * from Users")
    List<User> getAll();

    @Query("Select * from Users where userID = :userID")
    User getByID(String userID);

    @Query("Select * from Users where name LIKE '%' || :userName || '%'")
    List<User> getByName(String userName) ;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> users);

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);


}
