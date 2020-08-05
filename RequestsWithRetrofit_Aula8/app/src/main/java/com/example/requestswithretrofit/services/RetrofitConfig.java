package com.example.requestswithretrofit.services;

import com.example.requestswithretrofit.services.CEPService;
import com.example.requestswithretrofit.services.DevService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {


        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        this.retrofit = new Retrofit.Builder()
//                .baseUrl("https://exampletindev-backend.herokuapp.com/")
                .baseUrl("https://viacep.com.br/ws/")
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public CEPService getCEPService() {
        return this.retrofit.create(CEPService.class);
    }

    public DevService getDevServer() {
        return this.retrofit.create(DevService.class);
    }
}
