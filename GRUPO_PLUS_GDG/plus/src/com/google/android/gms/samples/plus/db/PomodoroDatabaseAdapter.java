package com.google.android.gms.samples.plus.db;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class PomodoroDatabaseAdapter extends Service {
	private final IBinder mBinder = new LocalBinder();

	//private PomodoroDatabaseHelper databaseHelper;
	private DBHelper dbHelper;
	private SQLiteDatabase db;
	SimpleDateFormat dateFormat;
	
	public class LocalBinder extends Binder {
		public PomodoroDatabaseAdapter getService() {
			return PomodoroDatabaseAdapter.this;
		}
	}
	@Override
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}

	public PomodoroDatabaseAdapter(Context context) {
		//databaseHelper = new PomodoroDatabaseHelper(context);
		dbHelper = new DBHelper(context);
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	public void abrir() {
		//db = databaseHelper.getWritableDatabase();
		db = dbHelper.getDataBase();
	}

	public void cerrar() {
		//databaseHelper.close();
		db.close();
	}

	public long insertPomodoro(String name, long estimated) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("type", "Pomodoro");
		contentValues.put("name", name);
		contentValues.put("estimated", estimated);
		contentValues.put("pomodoros", 0);
		contentValues.put("unplanned", 0);
		contentValues.put("interruptions", 0);
		contentValues.put("ordinal", 0);
		contentValues.put("visible", 1);
		contentValues.put("parent", 0);
		contentValues.put("done", 0);
		 
		Date date = new Date();
		ContentValues initialValues = new ContentValues(); 
		initialValues.put("created", dateFormat.format(date));
		
		return db.insert("pomodoro", null, contentValues);
	}
	public void updatePomodoroByClock(long id){
		String sql = "UPDATE pomodoro SET pomodoros=pomodoros+1 WHERE id=" + id+"";
		//Log.i("QUERY", "-->" + sql);
		db.execSQL(sql);
	}
	public int updatePomodoro(long id, String name, long estimated) {
		ContentValues cVal = new ContentValues();
		cVal.put("name", name);
		cVal.put("estimated", estimated);
		return db.update("pomodoro", cVal, "id=?", new String[] { id + "" });
	}

	public boolean deletePomodoro(long id) {
		return db.delete("pomodoro", "id=" + id, null) > 0;
	}

	public Cursor getPomodoroById(long id) {
		Log.i("INFO", "el id es --->" + id);
		return db.query("pomodoro", new String[] { "id", "name", "estimated", "pomodoros", "unplanned", "interruptions" },
				"id=?", new String[] { id + "" }, null, null, null);
	}
	/**
	 * Limpia todos los done a visible 0
	 */
	public void cleanDone(){
		String sql = "UPDATE pomodoro SET visible=0 WHERE done=1";
		//Log.i("QUERY", "-interruption->" + sql);
		db.execSQL(sql);
	}
	/**
	 * SW para done o no done
	 * @param id, id del pomodoro
	 */
	public void swDonePomodoro(long id) {
		String sql = "UPDATE pomodoro SET done=NOT(done) WHERE id=" + id+"";
		//Log.i("QUERY", "-interruption->" + sql);
		db.execSQL(sql);
	}
	
	/**
	 * Listado de todos los pomodoros menos interrupciones
	 * @return Cursor
	 */
	public Cursor getAllPomodoros() {
		return db.query("pomodoro", new String[] { "id", "name", "type",
				"estimated", "pomodoros", "unplanned", "interruptions","done",
				"created" }, "(type=? OR type=?) AND visible=1", new String[] { "Pomodoro","Unplanned"}, null, null, null);
	}
	public long insertUnplaned(long id,String name, long estimated) {
		//actualiza pomodoro actual
		String sql = "UPDATE pomodoro SET unplanned=unplanned+1 WHERE id=" + id+"";
		//Log.i("QUERY", "-interruption->" + sql);
		db.execSQL(sql);
		ContentValues contentValues = new ContentValues();
		contentValues.put("type", "Unplanned");
		contentValues.put("name", name);
		contentValues.put("estimated", estimated);
		contentValues.put("pomodoros", 0);
		contentValues.put("unplanned", 0);
		contentValues.put("interruptions", 0);
		contentValues.put("ordinal", 0);
		contentValues.put("visible", 1);
		contentValues.put("parent", id);
		contentValues.put("done", 0);
		 
		Date date = new Date();
		ContentValues initialValues = new ContentValues(); 
		initialValues.put("created", dateFormat.format(date));
		
		return db.insert("pomodoro", null, contentValues);
	}
	
	/**
	 * Insercion de una interrupcion siempre regquiere un padre
	 * @param  long id, id del pomodoro padre
	 * @param string name, nombre de la interrupcion
	 * @return long id, de la interrupcion insertadoa
	 */
	public long insertPlus(String name) {
		String sql = "INSERT INTO pomodoro (name) VALUES ('" + name+"')";
		//Log.i("QUERY", "-interruption->" + sql);
		db.execSQL(sql);
		return 1;
	}
	public long insertPlus1(String name) {
		ContentValues contentValues = new ContentValues();
		//--contentValues.put("name", name);
		/*contentValues.put("parent", id);*/
	//--return db.insert("pomodoro", null, contentValues);
	
	contentValues.put("type", "Pomodoro");
	contentValues.put("name", name);
	contentValues.put("estimated", 1);
	contentValues.put("pomodoros", 0);
	contentValues.put("unplanned", 0);
	contentValues.put("interruptions", 0);
	contentValues.put("ordinal", 0);
	contentValues.put("visible", 0);
	contentValues.put("parent", 0);
	contentValues.put("done", 0);
	 
	Date date = new Date();
	ContentValues initialValues = new ContentValues(); 
	initialValues.put("created", dateFormat.format(date));
	
	return db.insert("pomodoro", null, contentValues);
//		return 1;
	}
	/**
	 * Obtiene todos los datos de configuracion
	 * @return cursor 
	 */
	public Cursor getAllConfigs(){
		return db.rawQuery("SELECT name, value FROM config WHERE name='pomodoroLength' OR name='time.shortBreakLength' OR name='time.longBreakLength' OR name='time.longBreakInterval'",null);
	}
	public long updateConfig(String name, String value) {
		ContentValues cVal = new ContentValues();
		cVal.put("name", name);
		cVal.put("value", value);
		return db.update("config", cVal, "name=?", new String[] { name + "" });
	}


	/**
	 * Clase que determina la estructura de la base de datos.
	 */
	private static class PomodoroDatabaseHelper extends SQLiteOpenHelper {

		public PomodoroDatabaseHelper(Context context) {
			super(context, "pomodrive.db", null, 1);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			//db.execSQL("CREATE TABLE personas (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, telefono INTEGER, correo TEXT, sexo TEXT);");
			db.execSQL("CREATE TABLE pomodoro (id INTEGER PRIMARY KEY ASC, name TEXT, type TEXT, pomodoros INTEGER, unplanned INTEGER, interruptions INTEGER, created DATETIME, closed DATETIME, parent INTEGER, visible BOOLEAN, ordinal INTEGER, done BOOLEAN, estimated INTEGER , \"user\" VARCHAR);");
			db.execSQL("CREATE TABLE config( name TEXT PRIMARY KEY, value TEXT );");
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS pomodoro");
			db.execSQL("DROP TABLE IF EXISTS config");
			onCreate(db);
		}
	}
}
