package com.example.cepapi.repository;

import android.util.Log;

import com.example.cepapi.model.CEP;
import com.example.cepapi.util.RetrofitConfig;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CepRepository {

    CEP cepResponse;

    public CEP buscarCEP(String cep) {

        Call<CEP> call = new RetrofitConfig().getCEPService().getCEP(cep);




        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                   cepResponse = response.body();
            }
            @Override
            public void onFailure(Call<CEP> call, Throwable t) {
                Log.e("CEPService   ", "Erro ao buscar o cep:" + t.getMessage());
            }
        });

        return cepResponse;
    }
}
