package com.example.recoin.Retrofit;


import com.example.recoin.Util.APIRest;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;



public class RetrofitConfig {

    private final Retrofit retrofit;


    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(APIRest.API_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }


}
