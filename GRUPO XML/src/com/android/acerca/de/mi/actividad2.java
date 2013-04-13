package com.android.acerca.de.mi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class actividad2 extends Activity implements OnClickListener{
	
	  
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad2);
        View botonguardar=findViewById(R.id.btnguardar);
        View botoncancelar=findViewById(R.id.btncancelar);
        botonguardar.setOnClickListener(this);
        botoncancelar.setOnClickListener(this);
    }

	public void onClick(View v) {
		switch (v.getId()){
   	 case R.id.btnguardar:
   		TextView country = null;
   		TextView depto = null;
   		TextView Industry = null;
   		try{
   			String c=country.getText().toString();
   			String d=depto.getText().toString();
   			String i=Industry.getText().toString();
   		}
   		catch(Exception e){
   			
   		}
   		
   	break;
   	
   	 case R.id.btncancelar:
   		 try{
   			if(v.getId()==findViewById(R.id.btncancelar).getId())
   			{
   				Intent i=new Intent(this,accionesperfil.class);
   				startActivity(i);
    		}
    		}
    		catch(Exception e){
    			
    		}
   	    	break; 
   	 }
   	 
   }
		
	}
