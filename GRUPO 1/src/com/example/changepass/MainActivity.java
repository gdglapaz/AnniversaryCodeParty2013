package com.example.changepass;


import android.content.ContentValues;
import android.database.Cursor;


import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;
import android.os.storage.OnObbStateChangeListener;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.database.SQLException;


import android.app.AlertDialog; //para mostrar mensaje de confirmación
import android.content.DialogInterface;
import android.content.Context;

import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import android.widget.TextView;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		EditText editText1 = (EditText) findViewById(R.id.editText1);
		EditText editText2 = (EditText) findViewById(R.id.editText2);
		editText2.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				TextView TEXT = (TextView) findViewById(R.id.TextView01);
    			EditText PWD2 = (EditText) findViewById(R.id.editText2);
		EditText editText1 = (EditText) findViewById(R.id.editText1);
			Button button1 = (Button) findViewById(R.id.button1);
		if(editText1.getText().toString().equals(PWD2.getText().toString())){
		TEXT.setText(" las contraseñas coinciden");
		//aqui viene el update a la bd con el pasword nuevo
			button1.setEnabled(true);
			}else{
   				TEXT.setText(" revise las contraseñas");
   			
   				//PWD2.setText("");
   				//editText1.setText("");
				button1.setEnabled(false);			}
			}}); 
		
		Button button1=(Button) findViewById(R.id.button1);
		
		button1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
	        //aca viene el if a la contraseña antigua
				
				
				Button button1 = (Button) findViewById(R.id.button1);
				TextView TEXT = (TextView) findViewById(R.id.TextView01);
				EditText editText1 = (EditText) findViewById(R.id.editText1);
				EditText editText2 = (EditText) findViewById(R.id.editText2);
				EditText editText3 = (EditText) findViewById(R.id.editText3);

				if(editText3.getText().toString().equals("")){
				TEXT.setText(" Ingrese la contraseña antigua");
				editText1.setText("");
				editText2.setText("");
				editText3.setText("");
				button1.setEnabled(false);
				
				//aqui viene el update a la bd con el pasword nuevo
					}else{
						modificacion();
						
	       				TEXT.setText("CORRECTO!!!");
	       				}
	        
	    
			}});
		
		
	Button button2=(Button) findViewById(R.id.button2);
	
	button2.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
        //aca viene el if a la contraseña antigua
			
			
			alta();
        
    
		}});
	
	}
		//base de datos
	
	private SQLiteDatabase baseDatos;   
	  private static final String TAG = "bdagenda";   
	  private static final String nombreBD = "agenda";   
	  private static final String tablaContacto = "contacto";
	  private static final String crearTablaContacto = "create table if not exists "  
			  + " contacto (codigo integer primary key autoincrement, "  
			  + " usuario text not null, password text not null unique);";   

	//Procedimiento para abrir la base de datos
	  //si no existe se creará, también se creará la tabla contacto        
	 
	 private SQLiteDatabase myDB=null;
	 public void abrir (String nombreBD){
		 myDB = this.openOrCreateDatabase(nombreBD,0, null);
	 }
	 public void crearTabla(String BD_TABLA, String TABLA_CAMPOS){
		 myDB.execSQL(crearTablaContacto);
		 }
	
	@Override
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}
	  public void alta() {
		  EditText editText1 = (EditText) findViewById(R.id.editText1);
			EditText editText2 = (EditText) findViewById(R.id.editText2);
	        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
	                "administracion", null, 1);
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        String usuario = editText1.getText().toString();
	        String password = editText2.getText().toString();
	      
	        ContentValues registro = new ContentValues();
	        registro.put("usuario", usuario);
	        registro.put("password", password);
	    
	        bd.insert("contactos", null, registro);
	        bd.close();
	        editText1.setText("");
	        editText2.setText("");
	       
	        Toast.makeText(this, "Se cargaron los datos de la persona",
	                Toast.LENGTH_SHORT).show();
	    }
	   public void modificacion( ) {
		   EditText editText2 = (EditText) findViewById(R.id.editText2);
			EditText editText3 = (EditText) findViewById(R.id.editText3);
	        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
	        SQLiteDatabase bd=admin.getWritableDatabase();
	        String usuario = editText2.getText().toString();
	        String password = editText3.getText().toString();
	        ContentValues registro=new ContentValues();
	        registro.put("usuario", usuario);
	        registro.put("password", password);
	    
	        int cant = bd.update("contactos", registro, "usuario="+password, null);
	        bd.close();
	        if (cant==1)
	            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT).show();
	        else
	            Toast.makeText(this, "no se modifico", Toast.LENGTH_SHORT).show();        
	    }
	

}
