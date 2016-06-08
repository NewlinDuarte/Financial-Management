package com.cendev.cenproyects.financialmanagement;

import android.provider.BaseColumns;

/**
 * Created by Newlin on 6/2/2016.
 */

public final class DatabaseContract {

    public DatabaseContract(){}

    public static abstract class IngresoEntry implements BaseColumns{
        public static final String TABLE_NAME = "ingresos";
        public static final String COLUMN_NAME_INGRESO_ID = "ingresoid";
        public static final String COLUMN_NAME_CANTIDAD = "cantidad";

    }

    public static abstract class CuentaEntry implements BaseColumns{
        public static final String TABLE_NAME = "cuentas";
        public static final String COLUMN_NAME_CUENTA_ID = "cuentaid";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_BALANCE = "balance";
    }

}
