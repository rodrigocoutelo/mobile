package com.example.recoin.Retrofit.Service;

import com.example.recoin.Model.Avatar;
import com.example.recoin.Model.User;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AvatarService {

    @Headers("Content-Type: application/json")
    @GET("uploads")
    Call<List<Avatar>> list(@Header("userType") String userType,
                            @Header("userLogin") String userLogin,
                            @Header("password") String password);

    @Headers("Content-Type: application/json")
    @GET("upload")
    Call<Avatar> get(@Header("userType") String userType,
                         @Header("userLogin") String userLogin,
                         @Header("password") String password,
                         @Header("userid") String userID
                        );

    @Multipart
    @POST("upload")
    Call<User> editUser(@Header("userType") String userType,
                        @Header("userLogin") String userLogin,
                        @Header("password") String password,
                        @Header("userid") String userID,
                        @Part("avatar") MultipartBody.Part avatar
    );


}
