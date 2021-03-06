package com.example.recoin.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(tableName = "Users")
@JsonIgnoreProperties({"uid","__v", "createdAt","updatedAt"})
public class User {

    @PrimaryKey
    @ColumnInfo(name="userID")
    @NonNull
    private String _id;
    private String name;
    private String cpf;
    private String email;
    private String pws;
    private String phone;
    @Embedded(prefix = "avatar")
    private Avatar avatar;



    public void set_id(@NonNull String _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) { this.email = email;}

    public void setPws(String pws) {
        this.pws = pws;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }


    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {return email;}

    public String getCpf() {
        return cpf;
    }

    public String getPws() {
        return pws;
    }

    public String getPhone() {
        return phone;
    }

    public Avatar getAvatar() {
        return avatar;
    }


}
