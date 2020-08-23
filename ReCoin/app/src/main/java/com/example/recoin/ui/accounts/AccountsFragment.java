package com.example.recoin.ui.accounts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.recoin.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AccountsFragment extends Fragment {

    private AccountsViewModel accountsViewModel;
    private FloatingActionButton fab;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountsViewModel = new ViewModelProvider(this).get(AccountsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_accounts, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transactionAdd = getParentFragmentManager().beginTransaction();
                transactionAdd.replace(R.id.nav_host_fragment, new CreateAccountFragment(),"create_account" );
                transactionAdd.addToBackStack(null);
                transactionAdd.commit();


            }
        });

        accountsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }

        });
        return root;
    }
}