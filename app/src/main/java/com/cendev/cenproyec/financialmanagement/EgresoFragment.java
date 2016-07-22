package com.cendev.cenproyec.financialmanagement;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cendev.cenproyec.BLL.CuentasClass;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EgresoFragment extends Fragment implements View.OnClickListener  {
    FragmentActivity listener;


    public EgresoFragment() {
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
        View v = inflater.inflate(R.layout.fragment_egreso, container, false);
        CuentasClass Cuentaclass = new CuentasClass();
        Spinner Spin = (Spinner) v.findViewById(R.id.CuentaEgresoSpinner);
        SqliteController sqlite = new SqliteController(getContext());
        List<String> labels = sqlite.getCuentasLabels();
        ArrayAdapter spinnerCuentasAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,labels );
        Spin.setAdapter(spinnerCuentasAdapter);

        Button insertar = (Button) v.findViewById(R.id.EgresoGuardarButton);
        insertar.setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.EgresoGuardarButton:
                insertEgreso();
                break;
           /* case R.id.MostrarButton:
                mostrarIngreso();
                break; */
        }
    }

    private void insertEgreso() {
        SqliteController controller = new SqliteController(this.getContext());
        EditText edit = (EditText) getView().findViewById(R.id.EgresoTextBox);
        CharSequence text = String.valueOf(edit.getText());
        float egreso = Float.parseFloat(String.valueOf(text));
        int cuentaid = 0;
        List<Integer> ids = controller.getCuentasId();
        Spinner spinner = (Spinner) getView().findViewById(R.id.CuentaEgresoSpinner);
        cuentaid =  ids.get(spinner.getSelectedItemPosition());
        Cursor cursor = controller.selectCuenta(cuentaid);
        cursor.moveToLast();
        float balance = cursor.getFloat(cursor.getColumnIndexOrThrow("balance")) - egreso ;
        controller.insertEgreso(egreso, cuentaid);
        controller.editarCuenta(cuentaid, cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.CuentaEntry.COLUMN_NAME_NOMBRE)),balance);

    }
}
