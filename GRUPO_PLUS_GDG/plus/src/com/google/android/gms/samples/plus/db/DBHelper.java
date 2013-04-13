package com.google.android.gms.samples.plus.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * @author Miguel S. Mendoza
 *
 */
public class DBHelper extends SQLiteOpenHelper {

	// Ruta por defecto de las bases de datos en el sistema Android
	private static String DB_PATH = "/data/data/com.google.android.gms.samples.plus/databases/";

	private static String DB_NAME = "pomodrive.db";

	private static int DATABASE_VERSION = 1;

	private final Context myContext;

	/**
	 * Constructor: Toma referencia hacia el contexto de la aplicaci�n que lo
	 * invoca para poder acceder a los 'assets' y 'resources' de la aplicaci�n.
	 * Crea un objeto DBOpenHelper que nos permitir� controlar la apertura de la
	 * base de datos.
	 * 
	 * @param context
	 */
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
		this.myContext = context;
	}

	/**
	 * Crea una base de datos vac�a en el sistema y la reescribe con nuestro
	 * fichero de base de datos.
	 * */
	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {
			// la base de datos existe y no hacemos nada.
		} else {
			// Llamando a este m�todo se crea la base de datos vac�a en la ruta
			// por defecto del sistema de nuestra aplicaci�n
			// por lo que podremos sobreescribirla con
			// nuestra base de datos.
			this.getReadableDatabase();

			try {

				copyDataBase();

			} catch (IOException e) {
				throw new Error("Error copiando Base de Datos");
			}
		}

	}

	/**
	 * Comprueba si la base de datos existe para evitar copiar siempre el
	 * fichero cada vez que se abra la aplicaci�n.
	 * 
	 * @return true si existe, false si no existe
	 */
	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			Log.i("BASE_1","ANTES DE ABRIR");
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READWRITE);
			Log.i("BASE_1","PASO DE ABRIR");

		} catch (SQLiteException e) {
			Log.i("BASE_1","EXCEPTION");
			// si llegamos aqui es porque la base de datos no existe todav�a.

		}
		if (checkDB != null) {

			checkDB.close();

		}
		return checkDB != null ? true : false;
	}

	/**
	 * Copia nuestra base de datos desde la carpeta assets a la reci�n creada
	 * base de datos en la carpeta de sistema, desde d�nde podremos acceder a
	 * ella. Esto se hace con bytestream.
	 * */
	private void copyDataBase() throws IOException {

		// Abrimos el fichero de base de datos como entrada
		InputStream myInput = myContext.getAssets().open(DB_NAME);

		// Ruta a la base de datos vac�a reci�n creada
		String outFileName = DB_PATH + DB_NAME;

		// Abrimos la base de datos vac�a como salida
		OutputStream myOutput = new FileOutputStream(outFileName);

		// Transferimos los bytes desde el fichero de entrada al de salida
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Liberamos los streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	/**
	 * Inicia el proceso de copia del fichero de base de datos, o crea una base
	 * de datos vac�a en su lugar
	 * */
	public SQLiteDatabase getDataBase() {
		// Abre la base de datos
		try {
			createDataBase();
		} catch (IOException e) {
			throw new Error("Ha sido imposible crear la Base de Datos");
		}

		String myPath = DB_PATH + DB_NAME;
		return SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
