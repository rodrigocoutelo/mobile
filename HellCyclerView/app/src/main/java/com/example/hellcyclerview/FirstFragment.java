package com.example.hellcyclerview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hellcyclerview.adapter.PaxAdapter;
import com.example.hellcyclerview.model.Pax;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    ArrayList<Pax> ListaPax = new ArrayList<>();

    private Context context;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_first, container, false);

        Pax pax1 = new Pax();
        pax1.setAvatar("http://4.bp.blogspot.com/_30PRmkOl4ro/SktbEYXJCXI/AAAAAAAASBg/8hZRAtMU6Sc/s400/Brad-Pitt2.jpg");
        pax1.setName("Bradson Silva");
        pax1.setSchool("Santa Maria");
        ListaPax.add(pax1);

        Pax pax2 = new Pax();
        pax2.setAvatar("http://4.bp.blogspot.com/_30PRmkOl4ro/SktbEYXJCXI/AAAAAAAASBg/8hZRAtMU6Sc/s400/Brad-Pitt2.jpg");
        pax2.setName("Bradson Silva");
        pax2.setSchool("Santa Maria");
        ListaPax.add(pax2);
        Pax pax3 = new Pax();
        pax3.setAvatar("http://4.bp.blogspot.com/_30PRmkOl4ro/SktbEYXJCXI/AAAAAAAASBg/8hZRAtMU6Sc/s400/Brad-Pitt2.jpg");
        pax3.setName("Bradson Silva");
        pax3.setSchool("Santa Maria");
        ListaPax.add(pax3);

        Pax pax4 = new Pax();
        pax4.setAvatar("http://4.bp.blogspot.com/_30PRmkOl4ro/SktbEYXJCXI/AAAAAAAASBg/8hZRAtMU6Sc/s400/Brad-Pitt2.jpg");
        pax4.setName("Bradson Silva");
        pax4.setSchool("Santa Maria");
        ListaPax.add(pax4);

        Pax pax5 = new Pax();
        pax5.setAvatar("http://4.bp.blogspot.com/_30PRmkOl4ro/SktbEYXJCXI/AAAAAAAASBg/8hZRAtMU6Sc/s400/Brad-Pitt2.jpg");
        pax5.setName("Bradson Silva");
        pax5.setSchool("Santa Maria");
        ListaPax.add(pax5);

        Pax pax6 = new Pax();
        pax6.setAvatar("http://4.bp.blogspot.com/_30PRmkOl4ro/SktbEYXJCXI/AAAAAAAASBg/8hZRAtMU6Sc/s400/Brad-Pitt2.jpg");
        pax6.setName("Bradson Silva");
        pax6.setSchool("Santa Maria");
        ListaPax.add(pax6);

        Pax pax7 = new Pax();
        pax7.setAvatar("http://4.bp.blogspot.com/_30PRmkOl4ro/SktbEYXJCXI/AAAAAAAASBg/8hZRAtMU6Sc/s400/Brad-Pitt2.jpg");
        pax7.setName("Bradson Silva");
        pax7.setSchool("Santa Maria");
        ListaPax.add(pax7);

        Pax pax8 = new Pax();
        pax8.setAvatar("http://4.bp.blogspot.com/_30PRmkOl4ro/SktbEYXJCXI/AAAAAAAASBg/8hZRAtMU6Sc/s400/Brad-Pitt2.jpg");
        pax8.setName("Bradson Cavalcanti");
        pax8.setSchool("Liceu de Artes e Ofíco");
        ListaPax.add(pax8);

        Pax pax9 = new Pax();
        pax9.setAvatar("http://4.bp.blogspot.com/_30PRmkOl4ro/SktbEYXJCXI/AAAAAAAASBg/8hZRAtMU6Sc/s400/Brad-Pitt2.jpg");
        pax9.setName("Bradson Oliveira");
        pax9.setSchool("Instituto Capibaribe");
        ListaPax.add(pax9);
        ListaPax.add(pax8);
        ListaPax.add(pax7);
        ListaPax.add(pax6);
        ListaPax.add(pax5);
        ListaPax.add(pax4);

        RecyclerView recyclerView = view.findViewById(R.id.PaxListView);
        PaxAdapter mPaxAdapter = new PaxAdapter(context, ListaPax);
        recyclerView.setAdapter(mPaxAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}