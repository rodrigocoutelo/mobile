package com.example.cepapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cepapi.model.CEP;
import com.example.cepapi.repository.CepRepository;
import com.example.cepapi.util.MaskEdit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CepRepository repo = new CepRepository();
    EditText editTextCEP;
    Button buttonConsultar;
    TextView logradouro, complemento, bairro, localidade, uf;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.editTextCEP = this.findViewById(R.id.editTextCEP);
        this.logradouro = this.findViewById(R.id.logradouro);
        this.complemento = this.findViewById(R.id.complemento);
        this.bairro = this.findViewById(R.id.bairro);
        this.localidade = this.findViewById(R.id.localidade);
        this.uf = this.findViewById(R.id.uf);
        this.buttonConsultar = this.findViewById(R.id.button);
        buttonConsultar.setOnClickListener(this);


        editTextCEP.addTextChangedListener(MaskEdit.mask(editTextCEP, MaskEdit.FORMAT_CEP));
        editTextCEP.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                editTextCEP.setText("");
                return false;
            }
        });



    }

    @Override
    public void onClick(View view) {
        String cep = MaskEdit.unmask(editTextCEP.getText().toString());

        CEP response = repo.buscarCEP(cep);

        if (response != null) {

            logradouro.setText(response.getLogradouro());
            complemento.setText(response.getComplemento());
            bairro.setText(response.getBairro());
            localidade.setText(response.getLocalidade());
            uf.setText(response.getUf());
        } else {
            editTextCEP.setText("");
            Toast.makeText(this, "CEP não encontrado", Toast.LENGTH_LONG).show();

        }

    }
}