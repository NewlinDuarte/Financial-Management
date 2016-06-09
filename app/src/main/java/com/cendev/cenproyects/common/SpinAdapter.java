package com.cendev.cenproyects.common;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.cendev.cenproyects.financialmanagement.IngresoFragment;

import java.util.List;

/**
 * Created by chris on 09/06/2016.
 */
public class SpinAdapter extends ArrayAdapter<IngresoFragment> {
    private Context context;
    private List<IngresoFragment> values;

    public SpinAdapter(Context context, int textViewResourceId, List<IngresoFragment> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    public int getCount(){
        return values.size();
    }

    public IngresoFragment getItem(int position){
        return values.get(position);
    }

    public long getItemId(int position){
        return values.get(position).getId();
    }

    public int getPosition(int Id) {
        int Posicion = 0;
        for(int i = 0; i < values.size(); ++i) {
            IngresoFragment groupCategorias = values.get(i);
            if(groupCategorias.getId() == Id) {
                Posicion = i;
            }
        }
        return Posicion;
    }

    @Override
    public String toString() {
        return values.toString();
    }
}
