package com.ricardo.listarecyclerview;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetalhesActivity extends AppCompatActivity {

    private TextView nome;
    private TextView descricao;
    private ImageView url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Intent intent = getIntent();

        nome = (TextView) findViewById(R.id.titulo);
        descricao = (TextView) findViewById(R.id.mensagem);
        url = (ImageView) findViewById(R.id.imagem);

        nome.setText(intent.getStringExtra("nome"));
        descricao.setText(intent.getStringExtra("descricao"));
        Picasso.get().load(intent.getStringExtra("url")).resize(0, 300).into(url);
    }
}