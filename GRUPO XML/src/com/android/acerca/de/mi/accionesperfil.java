package com.android.acerca.de.mi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.view.View.OnClickListener;


public class accionesperfil extends Activity implements OnClickListener {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accionesperfil);
        View mostrar=findViewById(R.id.button1);
        View editar=findViewById(R.id.button2);
        editar.setOnClickListener(this);
        mostrar.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
	   	 case R.id.button1:
	   		try{
	   			
	   			}
	   		catch(Exception e){
	   			
	   		}
	   		
	   	break;
	   	
	   	 case R.id.button2:
	   		 try{
	   			if(v.getId()==findViewById(R.id.button2).getId())
	   			{
	   				Intent i=new Intent(this,actividad2.class);
	   				startActivity(i);
	    		}
	   		 }
	    		catch(Exception e){
	    			
	    		}
	   	    	break; 
		
	}
	}
}
	
