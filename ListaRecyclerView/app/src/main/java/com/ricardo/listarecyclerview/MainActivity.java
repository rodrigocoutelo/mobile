package com.ricardo.listarecyclerview;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements
        ListagemAdapter.OnItemListClickListener {

    private LinkedList<ReceitaModel> listaDeReceitas = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaDeReceitas.add(
                new ReceitaModel("Pao",
                        "de Queijo Mineiro. Bacon ipsum dolor amet biltong fatback frankfurter corned beef picanha ham. Frankfurter chicken tri-tip bresaola ground round ham hock picanha pig buffalo ball tip pork belly.",
                        "https://i.pinimg.com/736x/dd/7e/86/dd7e86da795d1688fb7cbc7b85ad4449.jpg"));
        listaDeReceitas.add(
                new ReceitaModel("Sonho",
                        "Bem recheado. Bacon ipsum dolor amet biltong fatback frankfurter corned beef picanha ham. Frankfurter chicken tri-tip bresaola ground round ham hock picanha pig buffalo ball tip pork belly.",
                        "https://anamaria.uol.com.br/images/large/2019/03/18/sonho-com-creme-de-baunilha-1134863.jpg"));
        listaDeReceitas.add(
                new ReceitaModel("Pipoca",
                        "Com manteiga. Bacon ipsum dolor amet biltong fatback frankfurter corned beef picanha ham. Frankfurter chicken tri-tip bresaola ground round ham hock picanha pig buffalo ball tip pork belly.",
                        "https://d1e676vce0x2at.cloudfront.net/uploads/post/image/11411/main_shutterstock_296395484.jpg\n"));
        listaDeReceitas.add(
                new ReceitaModel("Ruffles",
                        "Sabor churrasco. Bacon ipsum dolor amet biltong fatback frankfurter corned beef picanha ham. Frankfurter chicken tri-tip bresaola ground round ham hock picanha pig buffalo ball tip pork belly.",
                        "https://docemalu.vteximg.com.br/arquivos/ids/176656-1000-1000/13619-1.jpg?v=636613055480970000"));
        listaDeReceitas.add(
                new ReceitaModel("Doritos",
                        "Sem molho. Bacon ipsum dolor amet biltong fatback frankfurter corned beef picanha ham. Frankfurter chicken tri-tip bresaola ground round ham hock picanha pig buffalo ball tip pork belly.",
                        "https://araujo.vteximg.com.br/arquivos/ids/3879972-1000-1000/07892840253349.jpg?v=636695167450830000\n"));
        listaDeReceitas.add(
                new ReceitaModel("Fini",
                        "Todas as variedades. Bacon ipsum dolor amet biltong fatback frankfurter corned beef picanha ham. Frankfurter chicken tri-tip bresaola ground round ham hock picanha pig buffalo ball tip pork belly.",
                        "https://http2.mlstatic.com/balas-de-goma-fini-banana-citricas-5-kg-D_NQ_NP_712363-MLB40140050834_122019-O.webp"));

//        pega a view que vai ser usada pelo recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
//        pega o adapter criado
        ListagemAdapter adapter = new ListagemAdapter(this, listaDeReceitas, this);
//        passa o 'ListagemAdapter' para o recycler view usar
        recyclerView.setAdapter(adapter);
//        determina que os itens serão exibidos na vertical, por padrão
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(ReceitaModel model, int position) {
        Intent intent = new Intent(this, DetalhesActivity.class);
        intent.putExtra("nome", model.getName());
        intent.putExtra("descricao", model.getDescription());
        intent.putExtra("url", model.getUrl());
        this.startActivity(intent);
    }
}