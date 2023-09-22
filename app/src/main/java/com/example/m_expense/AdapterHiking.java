package com.example.m_expense;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterHiking extends RecyclerView.Adapter<AdapterHiking.HikingViewHolder> {
    private Context context;
    private ArrayList<HikingModel> hikingList;
    public AdapterHiking(Context context, ArrayList<HikingModel> hikingList){
        this.context=context;
        this.hikingList=hikingList;
    }
    @NonNull
    @Override
    public HikingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.hiking_item,parent,false);
        HikingViewHolder vh= new HikingViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HikingViewHolder holder, int position) {
        HikingModel hikingModel= hikingList.get(position);

        String id= hikingModel.getHikingId();
        String name=hikingModel.getHikingName();
        holder.hikingName.setText(name);
    }

    @Override
    public int getItemCount() {
        return hikingList.size();
    }

    class HikingViewHolder extends RecyclerView.ViewHolder{

        TextView hikingName;
        public HikingViewHolder(@NonNull View itemView) {
            super(itemView);

            hikingName=itemView.findViewById(R.id.hikingName);
        }
    }
}
