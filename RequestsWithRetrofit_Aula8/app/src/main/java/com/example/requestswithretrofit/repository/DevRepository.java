package com.example.requestswithretrofit.repository;

import com.example.requestswithretrofit.RequestResult;
import com.example.requestswithretrofit.model.DevMessage;
import com.example.requestswithretrofit.model.DevRequest;
import com.example.requestswithretrofit.services.RetrofitConfig;
import com.example.requestswithretrofit.model.Desenvolvedor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DevRepository {

    public void getAllDevs(final RequestResult requestResult) {

        Call<List<Desenvolvedor>> call = new RetrofitConfig().getDevServer().getAllDevs();
        call.enqueue(new Callback<List<Desenvolvedor>>() {
            @Override
            public void onResponse(Call<List<Desenvolvedor>> call, Response<List<Desenvolvedor>> response) {
                List<Desenvolvedor> devs = response.body();
                requestResult.returnSuccess(devs);
            }

            @Override
            public void onFailure(Call<List<Desenvolvedor>> call, Throwable t) {
                requestResult.returnError("Deu erro na requisição! Error message: \n" + t.getMessage());
            }
        });
    }

    public void getDevs(String name, final RequestResult requestResult) {

        Call<DevMessage> call = new RetrofitConfig().getDevServer().getDevs(name);
        call.enqueue(new Callback<DevMessage>() {
            @Override
            public void onResponse(Call<DevMessage> call, Response<DevMessage> response) {
                DevMessage message = response.body();
                requestResult.returnSuccess(message.getMenssagem());
            }

            @Override
            public void onFailure(Call<DevMessage> call, Throwable t) {
                requestResult.returnError("Deu erro na requisição! Error message: \n" + t.getMessage());
            }
        });
    }

    public void getDevsByLike(String name, final RequestResult requestResult) {

        Call<List<Desenvolvedor>> call = new RetrofitConfig().getDevServer().getDevsByLike(name);
        call.enqueue(new Callback<List<Desenvolvedor>>() {
            @Override
            public void onResponse(Call<List<Desenvolvedor>> call, Response<List<Desenvolvedor>> response) {
                List<Desenvolvedor> devs = response.body();
                requestResult.returnSuccess(devs);
            }

            @Override
            public void onFailure(Call<List<Desenvolvedor>> call, Throwable t) {
                requestResult.returnError("Deu erro na requisição! Error message: \n" + t.getMessage());
            }
        });
    }

    public void addDev(String userName ,final RequestResult requestResult) {

        Call<Desenvolvedor> call = new RetrofitConfig().getDevServer().add(new DevRequest(userName));

        call.enqueue(new Callback<Desenvolvedor>() {
            @Override
            public void onResponse(Call<Desenvolvedor> call, Response<Desenvolvedor> response) {
                Desenvolvedor dev = response.body();
                requestResult.returnSuccess(dev);
            }

            @Override
            public void onFailure(Call<Desenvolvedor> call, Throwable t) {
                requestResult.returnError("Deu erro na requisição! Error message: \n" + t.getMessage());
            }
        });

    }
}
