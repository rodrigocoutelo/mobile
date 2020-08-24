package com.example.recoin.DB;

import androidx.room.TypeConverter;

import com.example.recoin.Model.Avatar;
import com.example.recoin.Model.BankAccount;
import com.example.recoin.Model.BankTransaction;
import com.example.recoin.Model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converters {

    private static ObjectMapper objectMapper;


    @TypeConverter
    public static User jsonToUser(String value) {
        objectMapper = new ObjectMapper();
        User user = new User();
        try {
             user = objectMapper.readValue(value, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @TypeConverter
    public static BankAccount jsonToBankAccount(String value) {
        objectMapper = new ObjectMapper();
        BankAccount account = new BankAccount();
        try {
            account = objectMapper.readValue(value, BankAccount.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return account;
    }

    @TypeConverter
    public static Avatar jsonToAvatar(String value) {
        objectMapper = new ObjectMapper();
        Avatar avatar = new Avatar();
        try {
            avatar = objectMapper.readValue(value, Avatar.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return avatar;
    }

    @TypeConverter
    public static BankTransaction jsonToBankTransaction(String value) {
        objectMapper = new ObjectMapper();
        BankTransaction transaction = new BankTransaction();
        try {
            transaction = objectMapper.readValue(value, BankTransaction.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transaction;
    }

    @TypeConverter
    public static String ObjectToJson(User value) {
        objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{'Error':'Convert error'}";
        }
    }

    @TypeConverter
    public static String BankAccountToJson(BankAccount value) {
        objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{'Error':'Convert error'}";
        }
    }

    @TypeConverter
    public static String AvatarToJson(Avatar value) {
        objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{'Error':'Convert error'}";
        }
    }

    @TypeConverter
    public static String BankTransactionToJson(BankTransaction value) {
        objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{'Error':'Convert error'}";
        }
    }

    @TypeConverter
    public static ArrayList<User> jsonToListUser(String value) {
        objectMapper = new ObjectMapper();
        User[] users = new User[0];
        try {
            users = objectMapper.readValue(value, User[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<User>(Arrays.asList(users));
    }

    @TypeConverter
    public static ArrayList<BankAccount> jsonToListBankAccount(String value) {
        objectMapper = new ObjectMapper();
        BankAccount[] accounts = new BankAccount[0];
        try {
            accounts = objectMapper.readValue(value, BankAccount[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<BankAccount>(Arrays.asList(accounts));
    }

    @TypeConverter
    public static ArrayList<Avatar> jsonToListAvatar(String value) {
        objectMapper = new ObjectMapper();
        Avatar[] avatars = new Avatar[0];
        try {
            avatars = objectMapper.readValue(value, Avatar[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Avatar>(Arrays.asList(avatars));
    }

    @TypeConverter
    public static ArrayList<BankTransaction> jsonToListBankTransaction(String value) {
        objectMapper = new ObjectMapper();
        BankTransaction[] transactions = new BankTransaction[0];
        try {
            transactions = objectMapper.readValue(value, BankTransaction[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<BankTransaction>(Arrays.asList(transactions));
    }

    @TypeConverter
    public static String ListObjectToJson(List<User> value) {
        objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{'Error':'Convert error'}";
        }
    }

    @TypeConverter
    public static String ListBankAccountToJson(List<BankAccount> value) {
        objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{'Error':'Convert error'}";
        }
    }

    @TypeConverter
    public static String ListAvatarToJson(List<Avatar> value) {
        objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{'Error':'Convert error'}";
        }
    }

    @TypeConverter
    public static String ListBankTransactionToJson(List<BankTransaction> value) {
        objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{'Error':'Convert error'}";
        }
    }

}
