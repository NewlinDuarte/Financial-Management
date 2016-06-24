package com.cendev.cenproyect.financialmanagement;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Switch;

import com.cendev.cenproyect.BLL.CuentasClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.media.CamcorderProfile.get;


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
        /*Button mostrar = (Button) v.findViewById(R.id.GuardarButton);
        mostrar.setOnClickListener(this);*/
        CuentasClass Cuentaclass = new CuentasClass();
        Spinner Spin = (Spinner) v.findViewById(R.id.spinner);
        SqliteController sqlite = new SqliteController(getContext());
        List<String> labels = sqlite.getCuentasLabels();
        ArrayAdapter spinnerCuentasAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,labels );
        Spin.setAdapter(spinnerCuentasAdapter);
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        Button dias = (Button) v.findViewById(R.id.fechabutton);
        dias.setText(thisDate);
        SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm a");
        Date time = new Date();
        String thistime = currenttime.format(time);
        Button tiempo = (Button) v.findViewById(R.id.tiempobutton);
        tiempo.setText(thistime);
        Button insertar = (Button) v.findViewById(R.id.GuardarButton);
        insertar.setOnClickListener(this);
        return v;



    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.GuardarButton:
                insertIngreso();
                break;
           /* case R.id.MostrarButton:
                mostrarIngreso();
                break; */
        }
    }

    private void habilitarPeriodico(){
        Switch switchs = (Switch) getView().findViewById(R.id.switch1);
        if(switchs != null) {
            Button tiempo = (Button) getView().findViewById(R.id.tiempobutton);
            Button dias = (Button) getView().findViewById(R.id.fechabutton);
            tiempo.setVisibility(View.VISIBLE);
            dias.setVisibility(View.VISIBLE);
        }
        //HACER QUE SI EL SWITCH ESTA ENCENDIDO APAREZCAN LOS BOTONES.
    }

    private void insertIngreso() {
        SqliteController controller = new SqliteController(this.getContext());
        EditText edit = (EditText) getView().findViewById(R.id.IngresoTextBox);
        CharSequence text = String.valueOf(edit.getText());
        float ingreso = Float.parseFloat(String.valueOf(text));
        int cuentaid = 0;
        List<Integer> ids = controller.getCuentasId();
        Spinner spinner = (Spinner) getView().findViewById(R.id.spinner);
        cuentaid =  ids.get(spinner.getSelectedItemPosition());
        Cursor cursor = controller.selectCuenta(cuentaid);
        cursor.moveToLast();
        float balance = cursor.getFloat(cursor.getColumnIndexOrThrow("balance")) + ingreso ;
        controller.insertIngreso(ingreso, cuentaid);
        controller.editarCuenta(cuentaid, cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.CuentaEntry.COLUMN_NAME_NOMBRE)),balance);

    }

    private void cancelarMovimiento(View v) {
        getActivity().getFragmentManager().popBackStack();
    }
/*    private void mostrarIngreso(){
        SqliteController controller = new SqliteController(this.getContext());
        long item = controller.selectIngreso(1);
        long total = controller.totalIngreso();
        TextView textView = (TextView) getView().findViewById(R.id.TotalIngresoTextBox);
        textView.setText(String.valueOf(total));
        textView.setVisibility(View.VISIBLE);

        // para probar
        CharSequence text =  String.valueOf(item);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this.getContext(), text, duration);
        toast.show();

    } */
}