package com.cendev.cenproyect.BLL;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.cendev.cenproyect.financialmanagement.DatabaseContract;
import com.cendev.cenproyect.financialmanagement.SqliteController;


/**
 * Created by Newlin on 6/24/2016.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        CharSequence text =  arg1.getStringExtra("success");
        SqliteController controller = new SqliteController(arg0);
        controller.insertIngreso(arg1.getFloatExtra("ingreso", 0), arg1.getIntExtra("cuentaid", 0));
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(arg0, text, duration);
        toast.show();
    }

    public void onReceive(Context arg0, Intent arg, float Valor) {

    }


}
