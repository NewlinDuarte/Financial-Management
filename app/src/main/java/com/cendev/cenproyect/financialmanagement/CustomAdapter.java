package com.cendev.cenproyect.financialmanagement;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Newlin on 6/8/2016.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<CuentaDataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView card_view_cuenta_nombre;
        TextView card_view_cuenta_balance;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.card_view_cuenta_nombre = (TextView) itemView.findViewById(R.id.card_view_cuenta_nombre);
            this.card_view_cuenta_balance = (TextView) itemView.findViewById(R.id.card_view_cuenta_balance);

        }
    }

    public CustomAdapter(ArrayList<CuentaDataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_card_view, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView card_view_cuenta_nombre = holder.card_view_cuenta_nombre;
        TextView card_view_cuenta_balance = holder.card_view_cuenta_balance;

        card_view_cuenta_nombre.setText(dataSet.get(listPosition).getNombre());
        card_view_cuenta_balance.setText(String.valueOf(dataSet.get(listPosition).getBalance()));

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}

