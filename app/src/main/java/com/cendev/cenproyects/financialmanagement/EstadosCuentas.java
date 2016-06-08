package com.cendev.cenproyects.financialmanagement;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EstadosCuentas extends Fragment {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static   RecyclerView recyclerView;
    private static ArrayList<CuentaDataModel> data;
    private static ArrayList<Integer> removedItems;

    public EstadosCuentas() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_estados_cuentas, container, false);
        fillRecycler(v);
        return v;
    }


    public void fillRecycler(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<CuentaDataModel>();
        SqliteController controller = new SqliteController(this.getContext());
        Cursor cursor = controller.listarCuentas();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            data.add(new CuentaDataModel(
                    cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    cursor.getFloat(cursor.getColumnIndexOrThrow("balance")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("cuentaid"))
            ));
        }

        removedItems = new ArrayList<Integer>();

        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);

    }
}
