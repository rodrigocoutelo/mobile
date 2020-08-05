package com.example.hellcyclerview.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hellcyclerview.R;
import com.example.hellcyclerview.model.Pax;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PaxAdapter extends RecyclerView.Adapter<PaxAdapter.PaxViewHolder> {

    private final ArrayList<Pax> mPaxList;
    private final LayoutInflater mInflater;
    public Context ctx;
    public Bundle mDataBundle;



    public PaxAdapter (Context context, ArrayList<Pax> paxList) {
        ctx = context;
        mInflater = LayoutInflater.from(context);
        this.mPaxList = paxList;
    }

    @NonNull
    @Override
    public PaxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mItemView = mInflater.inflate(
                R.layout.paxlista, parent, false);

        return new PaxViewHolder(mItemView, this);
    }


    @Override
    public void onBindViewHolder(@NonNull PaxViewHolder holder, int position) {
        Pax itemPax = (mPaxList.get(position));
        Picasso.get().load(itemPax.getAvatar()).resize(0, 60).into(holder.iAvatarPax);
        holder.tNamePax.setText(itemPax.getName());
        holder.tSchoolPax.setText(itemPax.getSchool());
        Log.d("Pax Avatar",itemPax.getAvatar());


        holder.cItemPax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();

            }
        });
  }

    @Override
    public int getItemCount() {
        return mPaxList.size();
    }

   class PaxViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ImageView iAvatarPax;
        public final TextView tNamePax;
        public final TextView tSchoolPax;
        public final LinearLayout cItemPax;

       final PaxAdapter mPaxAdapter;

        public PaxViewHolder(View itemView, PaxAdapter adapter) {
            super(itemView);

            this.mPaxAdapter = adapter;
            this.iAvatarPax = itemView.findViewById(R.id.avatar);
            this.tNamePax = itemView.findViewById(R.id.paxname);
            this.tSchoolPax = itemView.findViewById(R.id.paxschool);
            this.cItemPax = itemView.findViewById(R.id.paxItem);

            iAvatarPax.setOnClickListener(this);
            tNamePax.setOnClickListener(this);
            tSchoolPax.setOnClickListener(this);
            cItemPax.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                //Toast.makeText(ctx, "Item foi clicado", Toast.LENGTH_LONG).show();
              Navigation.findNavController(v).navigate(R.id.action_FirstFragment_to_SecondFragment);
        }
    }
}
