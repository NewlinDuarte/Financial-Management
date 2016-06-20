package com.cendev.cenproyect.BLL;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;


import com.cendev.cenproyect.financialmanagement.SqliteController;


/**
 * Created by chris on 09/06/2016.
 */
public class CuentasClass {
    public int CuentaId;
    public String Nombre;
    public float Balance;
    public int Tipo;
    Context Contexto;
    SqliteController SqliteController = new SqliteController(Contexto);


    public int getCuentaId() {
        return CuentaId;
    }

    public void setCuentaId(int cuentaId) {
        CuentaId = cuentaId;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public float getBalance() {
        return Balance;
    }

    public void setBalance(float balance) {
        Balance = balance;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int tipo) {
        Tipo = tipo;
    }

    public CuentasClass() {
        CuentaId = 0;
        Nombre = "";
        Balance = 0f;
        Tipo = 0;
    }

    public CuentasClass(int cuentaId, String nombre, float balance, int tipo) {
        CuentaId = cuentaId;
        Nombre = nombre;
        Balance = balance;
        Tipo = tipo;
    }

    public ArrayList<CuentasClass> getCuentas() {
        Cursor cursor = SqliteController.listarCuentas();
        ArrayList<CuentasClass> ObjetoCuentas = new ArrayList<CuentasClass>();
        cursor.moveToFirst();
        do {
            CuentasClass objetoCuentas = new CuentasClass();
            objetoCuentas.setCuentaId(cursor.getInt(0));
            objetoCuentas.setNombre(cursor.getString(1));

            ObjetoCuentas.add(objetoCuentas);
        } while (cursor.moveToNext());
        return ObjetoCuentas;
    }

}

/*Newlin No borre eto COÑO*/
/*aqui vamos a trabajar las clases de las cuentas, de manera*/