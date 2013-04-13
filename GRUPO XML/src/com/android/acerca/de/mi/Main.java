package com.android.acerca.de.mi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.view.View.OnClickListener;


public class Main extends Activity implements OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        View login=findViewById(R.id.button1);
        login.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	public void onClick(View v) {
		if(v.getId()==findViewById(R.id.button1).getId())
		{
			Intent i=new Intent(this,accionesperfil.class);
			startActivity(i);
		}
		
	}
}
