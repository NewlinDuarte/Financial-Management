package com.cendev.cenproyects.financialmanagement;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class CuentasFragment extends Fragment implements View.OnClickListener {
    FragmentActivity listener;

    public CuentasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cuentas, container, false);
        setListener(v);
        return  v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Guardar_Cuentas_Button:
                insertCuenta();
                break;
        }
    }

    private void setListener(View v){
        Button insertar = (Button)v.findViewById(R.id.Guardar_Cuentas_Button);
        insertar.setOnClickListener(this);
    }

    private void insertCuenta(){
        SqliteController controller = new SqliteController(this.getContext());
        EditText edit = (EditText) getView().findViewById(R.id.Nombre_Cuenta_Edit_Text);
        CharSequence text =  String.valueOf(edit.getText());
        controller.insertCuenta(String.valueOf(text),0);
    }

}
