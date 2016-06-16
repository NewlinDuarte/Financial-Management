package com.cendev.cenproyects.financialmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Newlin on 6/2/2016.
 */

public class SqliteController extends SQLiteOpenHelper {

    public SqliteController(Context applicationcontext) {
        super(applicationcontext, "androidsqlite.db", null, 1);
    }




    @Override
    public void onCreate(SQLiteDatabase db){
        String query;
        // Creacion de la tabla cuentas
        query = " CREATE TABLE "+ DatabaseContract.CuentaEntry.TABLE_NAME +" ( "+ DatabaseContract.CuentaEntry.COLUMN_NAME_CUENTA_ID+" INTEGER PRIMARY KEY, "+ DatabaseContract.CuentaEntry.COLUMN_NAME_NOMBRE+" TEXT," + DatabaseContract.CuentaEntry.COLUMN_NAME_BALANCE+" REAL, " + DatabaseContract.CuentaEntry.COLUMN_NAME_TIPO + " INTEGER);";
        db.execSQL(query);
        // Creacion de la tabla ingresos
        query = "CREATE TABLE "+ DatabaseContract.IngresoEntry.TABLE_NAME +" ( "+ DatabaseContract.IngresoEntry.COLUMN_NAME_INGRESO_ID+" INTEGER PRIMARY KEY, "+ DatabaseContract.IngresoEntry.COLUMN_NAME_CANTIDAD+" REAL, "+DatabaseContract.IngresoEntry.COLUMG_NAME_CUENTA_ID+" INTEGER, FOREIGN KEY("+DatabaseContract.IngresoEntry.COLUMG_NAME_CUENTA_ID+") REFERENCES  "+ DatabaseContract.CuentaEntry.TABLE_NAME +"("+DatabaseContract.CuentaEntry.COLUMN_NAME_CUENTA_ID+"));";
        db.execSQL(query);

        // Creacion de la tabla egresos
        query = "CREATE TABLE "+ DatabaseContract.EgresoEntry.TABLE_NAME +" ( "+ DatabaseContract.EgresoEntry.COLUMN_NAME_EGRESO_ID+" INTEGER PRIMARY KEY, "+ DatabaseContract.EgresoEntry.COLUMN_NAME_CANTIDAD+" REAL, "+DatabaseContract.EgresoEntry.COLUMN_NAME_CUENTA_ID+" INTEGER, FOREIGN KEY("+DatabaseContract.EgresoEntry.COLUMN_NAME_CUENTA_ID+") REFERENCES  "+ DatabaseContract.CuentaEntry.TABLE_NAME +"("+DatabaseContract.CuentaEntry.COLUMN_NAME_CUENTA_ID+"));";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int a, int b){


    }

    //Metodos para tabla Ingresos

    public void insertIngreso(float cantidad, int cuentaId){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.IngresoEntry.COLUMN_NAME_CANTIDAD, cantidad);
        values.put(DatabaseContract.IngresoEntry.COLUMG_NAME_CUENTA_ID, cuentaId );
        database.insert("ingresos", null, values);
    }

    public long selectIngreso(int id){
        SQLiteDatabase database = this.getReadableDatabase();
        // TODO: agregar condicion where en el query
        Cursor cursor = database.query(DatabaseContract.IngresoEntry.TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToLast();
        long item = cursor.getLong(cursor.getColumnIndexOrThrow("cantidad"));
        return item;
    }

    public long totalIngreso(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "Select sum("+DatabaseContract.IngresoEntry.COLUMN_NAME_CANTIDAD+") as Total from "+DatabaseContract.IngresoEntry.TABLE_NAME;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        long item = cursor.getLong(cursor.getColumnIndexOrThrow("Total"));
        return item;
    }
    // Metodos para tabla Egresos

    public void insertEgreso(float cantidad, int cuentaId){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.EgresoEntry.COLUMN_NAME_CANTIDAD, cantidad);
        values.put(DatabaseContract.EgresoEntry.COLUMN_NAME_CUENTA_ID, cuentaId );
        database.insert(DatabaseContract.EgresoEntry.TABLE_NAME, null, values);
    }


    // Metodos para tabla Cuentas

    public void insertCuenta(String nombre,float balance, int tipo){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.CuentaEntry.COLUMN_NAME_NOMBRE, nombre);
        values.put(DatabaseContract.CuentaEntry.COLUMN_NAME_BALANCE, balance);
        values.put(DatabaseContract.CuentaEntry.COLUMN_NAME_TIPO, tipo);
        database.insert(DatabaseContract.CuentaEntry.TABLE_NAME, null, values);
    }

    public Cursor listarCuentas(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "Select * from " + DatabaseContract.CuentaEntry.TABLE_NAME;
        Cursor c = database.rawQuery(query,null);
        return c;
    }

    public Cursor selectCuenta(int cuentaId){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseContract.CuentaEntry.TABLE_NAME + " WHERE " + DatabaseContract.CuentaEntry.COLUMN_NAME_CUENTA_ID + " = " + cuentaId + ";";
        Cursor c = database.rawQuery(query,null);
        return c;
    }


  //  public ArrayList<CuentasFragment>


    public void  editarCuenta(int cuentaId, String nombre, float balance){
        SQLiteDatabase database = this.getReadableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put(DatabaseContract.CuentaEntry.COLUMN_NAME_NOMBRE, nombre);
        newValues.put(DatabaseContract.CuentaEntry.COLUMN_NAME_BALANCE, balance);

        database.update(DatabaseContract.CuentaEntry.TABLE_NAME, newValues, DatabaseContract.CuentaEntry.COLUMN_NAME_CUENTA_ID + " = "+cuentaId, null);
        database.close();
    }

    public List<String> getCuentasLabels(){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseContract.CuentaEntry.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }

    public List<Integer> getCuentasId()
    {
        List<Integer> ids = new ArrayList<Integer>();

        String selectQuery = "SELECT  " + DatabaseContract.CuentaEntry.COLUMN_NAME_CUENTA_ID + " FROM " + DatabaseContract.CuentaEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ids.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning Id
        return ids;
    }
}
