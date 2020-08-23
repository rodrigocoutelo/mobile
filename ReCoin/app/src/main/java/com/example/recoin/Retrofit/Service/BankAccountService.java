package com.example.recoin.Retrofit.Service;

import com.example.recoin.Model.Avatar;
import com.example.recoin.Model.BankAccount;
import com.example.recoin.Model.User;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface BankAccountService {

    @Headers("Content-Type: application/json")
    @GET("getAllAccounts")
    Call<List<BankAccount>> list(@Header("userType") String userType,
                                 @Header("userLogin") String userLogin,
                                 @Header("password") String password
                                         );

    @Headers("Content-Type: application/json")
    @GET("accounts")
    Call<BankAccount> get(@Header("userType") String userType,
                     @Header("userLogin") String userLogin,
                     @Header("password") String password,
                     @Header("userid") String userID,
                     @Body BankAccount account

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
