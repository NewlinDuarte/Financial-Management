package com.cendev.cenproyect.financialmanagement;

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
        public static final String COLUMG_NAME_CUENTA_ID = "cuentaid";

    }

    public static abstract class EgresoEntry implements BaseColumns{
        public static final String TABLE_NAME = "egresos";
        public static final String COLUMN_NAME_EGRESO_ID = "egresoid";
        public static final String COLUMN_NAME_CANTIDAD = "cantidad";
        public static final String COLUMN_NAME_CUENTA_ID = "cuentaid";

    }

    public static abstract class CuentaEntry implements BaseColumns{
        public static final String TABLE_NAME = "cuentas";
        public static final String COLUMN_NAME_CUENTA_ID = "cuentaid";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_TIPO = "tipo";
        public static final String COLUMN_NAME_BALANCE = "balance";
    }

}
