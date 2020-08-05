package com.example.requestswithretrofit.services;

import com.example.requestswithretrofit.model.Desenvolvedor;
import com.example.requestswithretrofit.model.DevMessage;
import com.example.requestswithretrofit.model.DevRequest;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DevService {

    @GET("devs/all")
    Call<List<Desenvolvedor>> getAllDevs();

    @GET("/")
    Call<DevMessage> getDevs(@Query("name") String name);

    @GET("devs")
    Call<List<Desenvolvedor>> getDevsByLike(@Header("user") String userid);

    @Headers("Content-Type: application/json")
    @POST("devs")
    Call<Desenvolvedor> add(@Body DevRequest devRequest);
}
