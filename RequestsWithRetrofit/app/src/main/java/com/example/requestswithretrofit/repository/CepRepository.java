package com.example.requestswithretrofit.repository;

import com.example.requestswithretrofit.RequestResult;
import com.example.requestswithretrofit.services.RetrofitConfig;
import com.example.requestswithretrofit.model.CEP;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CepRepository {

    public void buscarCep(String cep, final RequestResult requestResult) {

        Call<CEP> call = new RetrofitConfig().getCEPService().buscarCep(cep);

        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                CEP cep = response.body();
                requestResult.returnSuccess(cep);
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {
                requestResult.returnError("Deu erro na requisição!");
            }
        });
    }
}
