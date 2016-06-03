package com.example.newlin.financialmanagement;


import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class IngresoFragment extends Fragment implements View.OnClickListener {
    FragmentActivity listener;

    public IngresoFragment() {
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
        View v = inflater.inflate(R.layout.fragment_ingreso, container, false);;
        Button mostrar = (Button) v.findViewById(R.id.mostrarbutton);
        mostrar.setOnClickListener(this);
        Button insertar = (Button) v.findViewById(R.id.insButton);
        insertar.setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.insButton:
                insertIngreso();
                break;
            case R.id.mostrarbutton:
                mostrarIngreso();
                break;
        }
    }


    // TODO: arreglar findViewById()
    private void insertIngreso(){
        SqliteController controller = new SqliteController(this.getContext());
       //EditText edit = (EditText) findViewById();
       // controller.insertIngreso(Float.parseFloat(edit.getText()));
        controller.insertIngreso(700);
    }

    private void mostrarIngreso(){
        SqliteController controller = new SqliteController(this.getContext());
        long item = controller.selectIngreso(1);
        // para probar
        CharSequence text =  String.valueOf(item);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this.getContext(), text, duration);
        toast.show();

    }
}
