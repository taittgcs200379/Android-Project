package com.example.m_expense;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterObservation extends RecyclerView.Adapter<AdapterObservation.ObservationViewHolder> {
    private Context context;
    private ArrayList<ObservationModel> observeList;
    private DatabaseHelper databaseHelper;
    public AdapterObservation(Context context, ArrayList<ObservationModel> observeList){
        this.context=context;
        this.observeList=observeList;
        databaseHelper= new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ObservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.observation_item,parent,false);
        ObservationViewHolder vo= new ObservationViewHolder(view);
        return vo;
    }

    @Override
    public void onBindViewHolder(@NonNull ObservationViewHolder holder, int position) {
        ObservationModel observationModel= observeList.get(position);


        String id= observationModel.getId();
        String id2= observationModel.getId2();
        String name=observationModel.getObserveChoice();
        String date=observationModel.getDate();
        String time=observationModel.getTime();
        String comment=observationModel.getComment();

        holder.ObservationName.setText(name);
    }

    @Override
    public int getItemCount() {
        return observeList.size();
    }

    class ObservationViewHolder extends RecyclerView.ViewHolder{
        TextView ObservationName;
        public ObservationViewHolder(@NonNull View itemView) {

            super(itemView);
            ObservationName=itemView.findViewById(R.id.observationName);
        }
    }
}
