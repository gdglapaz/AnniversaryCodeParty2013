package com.example.registro;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.view.View;

public class Ingreso extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_ingreso, menu);
        return true;
    }
    public void Registrar(View view)
    {
    	Intent i = new Intent(this, Registro.class);
    	startActivity(i);    	
    }
    public void Ingresar(View view)
    {
    	Intent i = new Intent(this, Ingresar.class);
    	startActivity(i);    	
    }
    
   ;
}
