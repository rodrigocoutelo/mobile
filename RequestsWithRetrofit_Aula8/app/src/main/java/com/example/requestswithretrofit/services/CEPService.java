package com.example.requestswithretrofit.services;


import com.example.requestswithretrofit.model.CEP;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {

    @GET("{cep}/json/")
    Call<CEP> buscarCep(@Path("cep") String cep);
}
