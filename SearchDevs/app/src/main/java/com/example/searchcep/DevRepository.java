package com.example.searchcep;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DevRepository {

    DevService devService;

    List<Dev> listAllDev;
    Dev dev;

    public List<Dev> getAll() {


        Call<List<Dev>> call = new RetrofitConfig().getDevService().getAllDevs();

        call.enqueue(new Callback<List<Dev>>() {


            @Override
            public void onResponse(Call<List<Dev>> call, Response<List<Dev>> response) {
                listAllDev = response.body();
            }

            @Override
            public void onFailure(Call<List<Dev>> call, Throwable t) {
                Log.e("List All", "Erro ao no getAll");
            }


        });

        return listAllDev;

    }

    public List<Dev> getByName(String name) {

        Call<List<Dev>> call = new RetrofitConfig().getDevService().getDevsByLike(name);
        call.enqueue(new Callback<List<Dev>>() {

            @Override
            public void onResponse(Call<List<Dev>> call, Response<List<Dev>> response) {
                listAllDev = response.body();
            }

            @Override
            public void onFailure(Call<List<Dev>> call, Throwable t) {
                Log.e("List All", t.getMessage());
            }
        });
        return listAllDev;

    }

    public Dev createDev(DevRequest devRequest) {

        Call<Dev> call = new RetrofitConfig().getDevService().add(devRequest);
        call.enqueue(new Callback<Dev>() {


            @Override
            public void onResponse(Call<Dev> call, Response<Dev> response) {
                dev = response.body();
            }

            @Override
            public void onFailure(Call<Dev> call, Throwable t) {
                Log.e("List All", "Erro no Post");
            }
        });
        return dev;
    }

}