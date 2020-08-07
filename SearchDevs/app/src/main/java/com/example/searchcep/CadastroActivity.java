package com.example.searchcep;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener  {

    EditText devName;
    Button createButton;
    TextView showDev;

    DevRepository repo = new DevRepository();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        devName = findViewById(R.id.editTextTextPersonName);
        createButton = findViewById(R.id.button);
        showDev = findViewById(R.id.showDev);

        createButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        DevRequest devRequest = new DevRequest(devName.getText().toString());
         //showDev.setText(repo.createDev(devRequest).toString());

        Call<Dev> call = new RetrofitConfig().getDevService().add(devRequest);
        call.enqueue(new Callback<Dev>() {
            Dev dev;

            @Override
            public void onResponse(Call<Dev> call, Response<Dev> response) {
                dev = response.body();
                showDev.setText((dev).toString());
            }

            @Override
            public void onFailure(Call<Dev> call, Throwable t) {
                Log.e("List All", "Erro no Post");
            }
        });


    }
}