package com.cendev.cenproyect.financialmanagement;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


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
        if (context instanceof Activity) {
            this.listener = (FragmentActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cuentas, container, false);
        setListener(v);
        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Guardar_Cuentas_Button:
                insertCuenta();
                break;
        }
    }

    private void setListener(View v) {
        Button insertar = (Button) v.findViewById(R.id.Guardar_Cuentas_Button);
        insertar.setOnClickListener(this);
    }

    private void insertCuenta() {
        final TextInputLayout NombreCuenta = (TextInputLayout) getView().findViewById(R.id.input_cuenta);
        SqliteController controller = new SqliteController(this.getContext());
        String NombreC = NombreCuenta.getEditText().getText().toString();
        CharSequence text = String.valueOf(NombreC);
        Spinner spinner = (Spinner) getView().findViewById(R.id.spinner4);
        int spinner_pos = spinner.getSelectedItemPosition();
        String[] Valor = getResources().getStringArray(R.array.BuscarCuenta_Value);
        int tipo = Integer.valueOf(Valor[spinner_pos]) +1; // 1, 2, 3


        controller.insertCuenta(String.valueOf(text), 0, tipo);
        Context context = getContext();
        CharSequence texto = "Se ha la guardado la cuenta!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


    }
}