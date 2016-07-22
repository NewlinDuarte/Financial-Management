package com.cendev.cenproyec.financialmanagement;

/**
 * Created by Newlin on 6/8/2016.
 */

public class CuentaDataModel {
    int id;
    String nombre;
    float balance;

    public CuentaDataModel(String nombre, float balance, int id){
        this.id = id;
        this.nombre = nombre;
        this.balance = balance;
    }

    public int getId() {return id;}

    public String getNombre() {
        return nombre;
    }

    public float getBalance() {
        return balance;
    }
}
