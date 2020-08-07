package com.example.searchcep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Dev> devsList = new ArrayList<>();
    private RecyclerView mDevRecyclerView;
    private DevAdapter aDevAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aDevAdapter = new DevAdapter(this, devsList);
        mDevRecyclerView = findViewById(R.id.devRecyclerView);
        mDevRecyclerView.setAdapter(aDevAdapter);
        mDevRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sendRequest();
    }

    public void sendRequest() {

        Call<List<Dev>> call = new RetrofitConfig().getDevService().getAllDevs();

        call.enqueue(new Callback<List<Dev>>() {
            @Override
            public void onResponse(Call<List<Dev>> call, Response<List<Dev>> response) {

                devsList = response.body();
                aDevAdapter.devList = devsList;
                mDevRecyclerView.getAdapter().notifyItemInserted(devsList.size());
                mDevRecyclerView.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Dev>> call, Throwable t) {
                // caixa de mensagem
                Toast.makeText(MainActivity.this, "Request failure", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View view) {


//        String sNome = mFiltroNome.getText().toString();
//
//        if (!sNome.equals("")) {
//
//
//
//            Call<List<Dev>> call = new RetrofitConfig().getDevService().getDevsByLike(sNome);
//            call.enqueue(new Callback<List<Dev>>() {
//
//
//                @Override
//                public void onResponse(Call<List<Dev>> call, Response<List<Dev>> response) {
//                    devsList = response.body();
////                    int code = response.code();
//
////                    if (code == 503) {
////                        Toast.makeText(MainActivity.this, "O servidor não respondeu!", Toast.LENGTH_LONG).show();
////                    } else {
////                        mNome.setText(devsList.get(1).getName());
////                    }
//                }
//
//                @Override
//                public void onFailure(Call<List<Dev>> call, Throwable t) {
//                    Log.e("List All", t.getMessage());
//                }
//            });
//        }
    }


}