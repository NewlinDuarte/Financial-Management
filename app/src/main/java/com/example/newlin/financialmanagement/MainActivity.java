package com.example.newlin.financialmanagement;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void ingresoClick (View v){
        Intent myIntent = new Intent(MainActivity.this, IngresoActivity.class);
        startActivity(myIntent);
    }

    protected void egresoClick (View v){
        Intent myIntent = new Intent(MainActivity.this, EgresoActivity.class);
        startActivity(myIntent);
    }
}
