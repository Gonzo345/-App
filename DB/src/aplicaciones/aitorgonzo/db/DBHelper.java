package aplicaciones.aitorgonzo.db;

import static android.provider.BaseColumns._ID;
import static android.provider.BaseColumns._COUNT;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	int COUNT;

	// Sentencia SQL para crear la tabla de Usuarios
	String sqlCreate = "CREATE TABLE ruleta ("
			+ _ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, id_ruleta INTEGER, num TEXT, fecha TEXT)";

	public DBHelper(Context contexto) {
		super(contexto, "ruleta", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Se ejecuta la sentencia SQL de creación de la tabla

		MCount(db);

	}

	public void MCount(SQLiteDatabase db) {
		// Se ejecuta la sentencia SQL de creación de la tabla

		Cursor mCount = db.rawQuery("select count(*) from ruleta", null);
		mCount.moveToFirst();
		COUNT = mCount.getInt(0);
		mCount.close();

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnterior,
			int versionNueva) {
		// Por simplicidad del ejemplo aquí utilizamos directamente
		// la opción de eliminar la tabla anterior y crearla de nuevo
		// vacía con el nuevo formato.
		// Sin embargo lo normal será que haya que migrar datos de la
		// tabla antigua a la nueva, por lo que este método debería
		// ser más elaborado.

		// Se elimina la versión anterior de la tabla
		db.execSQL("DROP TABLE IF EXISTS ruleta");

		// Se crea la nueva versión de la tabla
		db.execSQL(sqlCreate);
	}

	public void Insertar(int id_ruleta, String num, String fecha) {

		ContentValues valores = new ContentValues();// con esto creamos un
													// Objeto para que nos
													// guarde los valores que
													// iremos insetando

		valores.put("id_ruleta", id_ruleta);// inserta el id_ruleta
		valores.put("num", num);// Inserta el numero que ha salido
		valores.put("fecha", fecha);// Inserta la fecha de la bola
		this.getWritableDatabase().insert("ruleta", null, valores);
		/*
		 * this sirve para el contexto getWritableDatabase la pone en modo
		 * escritura insert nombre de la tabla
		 */

	}

	public String leer() {

		// String result[] = new String[COUNT];
		String result;
		String columnas[] = { _ID, "id_ruleta", "num", "fecha" };
		Cursor c = this.getReadableDatabase().query("ruleta", columnas, null,
				null, null, null, null);

		int id, idr, inum, ifecha;
		id = c.getColumnIndex(_ID);
		idr = c.getColumnIndex("id_ruleta");
		inum = c.getColumnIndex("num");
		ifecha = c.getColumnIndex("fecha");

		c.moveToLast();

		result = c.getString(idr) + " " + c.getString(idr) + " "
				+ c.getString(inum) + " " + c.getString(ifecha);

		return result;
		// int contador = 0;
		// for (c.moveToFirst(); !c.moveToLast(); c.moveToNext()) {
		// result[contador] = c.getString(idr) + " " + c.getString(idr) + " "
		// + c.getString(inum) + " " + c.getString(ifecha);
		// contador++;
		// }
		// return result;

	}

	public String[] getData(String Id) {
		Cursor c = null;

		String[] row = new String[10];

		c = this.getReadableDatabase().rawQuery("Select * FROM ruleta ", null);
		c.moveToFirst();
		// while(!c.isAfterLast())
		// {
		// arrayListProductName.add(c.getString(c.getColumnIndex("Column1")));
		// arrayListProductId.add(c.getString(c.getColumnIndex("Column2 ")));
		// c.moveToNext();
		// }

		for (int j = 0; j < 10; j++) {
			row[j] = c.getString(c.getColumnIndex("num"));
			c.moveToNext();
		}
		c.close();
		this.close();

		return row;
	}

	public void abrir() {
		this.getWritableDatabase();
	}

	public void cerrar() {
		this.close();
	}
}
