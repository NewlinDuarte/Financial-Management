package com.cendev.cenproyects.financialmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Newlin on 6/2/2016.
 */

public class SqliteController extends SQLiteOpenHelper {

    public SqliteController(Context applicationcontext) {
        super(applicationcontext, "androidsqlite.db", null, 1);
    }




    @Override
    public void onCreate(SQLiteDatabase db){
        String query; query = "CREATE TABLE "+ DatabaseContract.IngresoEntry.TABLE_NAME +" ( "+ DatabaseContract.IngresoEntry.COLUMN_NAME_INGRESO_ID+" INTEGER PRIMARY KEY, "+ DatabaseContract.IngresoEntry.COLUMN_NAME_CANTIDAD+" REAL)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int a, int b){


    }

    public void insertIngreso(float cantidad){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cantidad", cantidad);
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
}
