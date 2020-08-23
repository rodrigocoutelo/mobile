package com.example.recoin.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.recoin.DB.DAO.AvatarDao;
import com.example.recoin.DB.DAO.BankAccountDao;
import com.example.recoin.DB.DAO.BankTransactionDao;
import com.example.recoin.DB.DAO.UserDao;
import com.example.recoin.Model.Avatar;
import com.example.recoin.Model.BankAccount;
import com.example.recoin.Model.BankTransaction;
import com.example.recoin.Model.User;

@Database(entities = {User.class, BankAccount.class, BankTransaction.class, Avatar.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class, TimeStampConverter.class})
public abstract class AppDB extends RoomDatabase {

    abstract public UserDao userDao();
    abstract public BankAccountDao bankAccountDao();
    abstract public BankTransactionDao bankTransactionDao();
    abstract public AvatarDao avatarDao();

    private static AppDB instance;

    public static AppDB getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    AppDB.class,
                    "RECoin")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
