package com.example.recoin.Retrofit.Service;

import com.example.recoin.Model.Avatar;
import com.example.recoin.Model.User;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface UserService {

    @Headers("Content-Type: application/json")
    @GET("getAllUsers")
    Call<List<User>> getAllUsers(@Header("userType") String userType,
                                 @Header("userLogin") String userLogin,
                                 @Header("password") String password);

    @Headers("Content-Type: application/json")
    @GET("users")
    Call<User> getUser(@Header("cpf") String cpf,
                       @Header("pws") String password);

    @Headers("Content-Type: application/json")
    @POST("users")
    Call<User> add1(@Header("usertype") String userType,
                   @Header("userlogin") String userLogin,
                   @Header("userpassword") String password,
                   @Body User user);

    @Headers("Content-Type: application/json")
    @PUT("users")
    Call<User> update(@Header("usertype") String userType,
                       @Header("userlogin") String userLogin,
                       @Header("userpassword") String password,
                       @Body User user);

   // @Headers("Content-Type: application/json")
    @Multipart
    @POST("createuser")
    Call<User> add(@Header("userType") String userType,
                        @Header("userLogin") String userLogin,
                        @Header("userpassword") String password,
                        @Header("amount") double amount,
                        @Part MultipartBody.Part file,
                        @PartMap Map<String, RequestBody> params

    );
}



