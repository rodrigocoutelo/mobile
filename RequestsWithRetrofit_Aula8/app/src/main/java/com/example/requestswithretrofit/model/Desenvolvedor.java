package com.example.requestswithretrofit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties({"createdAt", "updatedAt", "__v"})
public class Desenvolvedor {

    ArrayList<String> likes = new ArrayList();
    ArrayList<String> dislikes = new ArrayList();
    private String _id;
    private String name;
    private String user;
    private String bio;
    private String avatar;

    public ArrayList<String> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<String> likes) {
        this.likes = likes;
    }

    public ArrayList<String> getDislikes() {
        return dislikes;
    }

    public void setDislikes(ArrayList<String> dislikes) {
        this.dislikes = dislikes;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Desenvolvedor{" +
                "likes=" + likes +
                ", dislikes=" + dislikes +
                ", _id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", bio='" + bio + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
