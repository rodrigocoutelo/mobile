package com.example.searchcep;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DevAdapter extends RecyclerView.Adapter<DevAdapter.DevHolder>{

    private final LayoutInflater layoutInflater;
    private Context context;
    public List<Dev> devList;

    public DevAdapter (Context pContext, List<Dev> pList) {
        this.devList = pList;
        this.context = pContext;
        this.layoutInflater = LayoutInflater.from(context);

    }


    @NonNull
    @Override
    public DevHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.itemdev, parent, false);

        return new DevHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DevHolder holder, int position) {
        Dev dev = devList.get(position);
        holder.mDevName.setText(dev.getName());
        holder.mDevDescription.setText(dev.getBio());
        Picasso.get().load(dev.getAvatar()).resize(0, 60).into(holder.mAvatar);
    }



    @Override
    public int getItemCount() {
        return devList.size();
    }


    class DevHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mAvatar;
        TextView mDevName;
        TextView mDevDescription;
        ConstraintLayout mDevItem;



        public DevHolder(@NonNull View itemView) {
            super(itemView);

            mAvatar = itemView.findViewById(R.id.avatar);
            mDevName = itemView.findViewById(R.id.devName);
            mDevDescription = itemView.findViewById(R.id.devDescription);
            mDevItem = itemView.findViewById(R.id.itemDev);
            mDevItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("DEV", devList.get(getLayoutPosition()));
            context.startActivity(intent);
        }
    }
}
