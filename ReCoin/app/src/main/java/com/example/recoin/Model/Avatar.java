package com.example.recoin.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(tableName = "Avatar")
@JsonIgnoreProperties({"uid","__v", "createdAt","updatedAt"})
public class Avatar {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="avatarID")
    private String _id;
    private String url;
    private String contentType;
    @Embedded(prefix = "user")
    @NonNull
    private User user;

    @NonNull
    public String get_id() {
        return _id;
    }

    public void set_id(@NonNull String _id) {
        this._id = _id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @NonNull
    public User getUser() {
        return user;
    }

    public void setUser(@NonNull User user) {
        this.user = user;
    }
}
