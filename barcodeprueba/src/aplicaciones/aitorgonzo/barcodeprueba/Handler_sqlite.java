package aplicaciones.aitorgonzo.barcodeprueba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Handler_sqlite extends SQLiteOpenHelper {
	int COUNT;

	public Handler_sqlite(Context context) {

		super(context, "Carrito", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String query = "CREATE TABLE cesta (id TEXT, nombre TEXT, precio TEXT, marca TEXT);";
		db.execSQL(query);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// db.execSQL("DROP TABLE IF EXISTS cesta");// comentada para evitar el
		// drop por actualizazion
		onCreate(db);
	}

	public void Insertar(String id, String nombre, String precio, String marca) {
		ContentValues valores = new ContentValues();
		valores.put("id", id);
		valores.put("nombre", nombre);
		valores.put("precio", precio);
		valores.put("marca", marca);

		this.getWritableDatabase().insert("cesta", null, valores);
	}

	public void abrir() {
		this.getWritableDatabase();
	}

	public String leer() {
		String result = "";
		String columnas[] = { "id", "nombre", "precio", "marca" };// declaramos
																	// las
																	// columnas
		Cursor c = this.getReadableDatabase().query("cesta", columnas, null,
				null, null, null, null);

		int id, idr, precio, marca; // aqui ponemos los indices de las columnas
									// en
									// cada integer que hemos creado
		id = c.getColumnIndex("id");
		idr = c.getColumnIndex("nombre");
		precio = c.getColumnIndex("precio");
		marca = c.getColumnIndex("marca");

		c.moveToLast(); // lo movemos al ultimo registro insertado

		result = c.getString(id) + " " + c.getString(idr) + " "
				+ c.getString(precio) + " " + c.getString(marca);

		return result;
	}

	public String[] leerArray(String numtotal) {

		int a = Integer.parseInt(numtotal);
		String result[] = new String[a];
		String columnas[] = { "id", "nombre", "precio", "marca" };// declaramos
																	// las
																	// columnas
		// Cursor c = this.getReadableDatabase().query("ruleta", columnas, null,
		// null, null, null, null);
		Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM cesta",
				null);

		int id, nombre, precio, marca; // aqui ponemos los indices de las
										// columnas en
										// cada integer que hemos creado
		id = c.getColumnIndex("id");
		nombre = c.getColumnIndex("nombre");
		precio = c.getColumnIndex("precio");
		marca = c.getColumnIndex("marca");

		int contador = 0;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result[contador] = c.getString(nombre) + "=" + c.getString(precio);
			contador++;
		}

		return result;

	}

	public String[] leerArrayPrecio(String numtotal) {

		int a = Integer.parseInt(numtotal);
		String result[] = new String[a];
		String columnas[] = { "id", "nombre", "precio", "marca" };// declaramos
																	// las
																	// columnas
		// Cursor c = this.getReadableDatabase().query("ruleta", columnas, null,
		// null, null, null, null);
		Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM cesta",
				null);

		int id, nombre, precio, marca; // aqui ponemos los indices de las
										// columnas en
										// cada integer que hemos creado
		id = c.getColumnIndex("id");
		nombre = c.getColumnIndex("nombre");
		precio = c.getColumnIndex("precio");
		marca = c.getColumnIndex("marca");

		int contador = 0;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result[contador] = c.getString(precio);
			contador++;
		}

		return result;

	}

	// este método se encarga de eliminar los precioeros seleccionados
	public String Buscar_Eliminar(String nombreeliminar) {

		SQLiteDatabase db = this.getWritableDatabase();

		// Eliminar la marca del String de entrada

		nombreeliminar = nombreeliminar.substring(0,
				nombreeliminar.indexOf("="));

		int posespacio = nombreeliminar.indexOf(" ", 0); // Encontramos la
															// posición del
															// espacio
															// (separador
															// para marca)

		Log.e("String", nombreeliminar);

		Cursor c = this.getReadableDatabase().rawQuery(
				"SELECT * FROM cesta WHERE nombre='" + nombreeliminar + "'",
				null);

		int id, nombre, precio, marca; // aqui ponemos los indices de las
										// columnas
										// en
										// cada integer que hemos creado
		id = c.getColumnIndex("id");
		nombre = c.getColumnIndex("nombre");
		precio = c.getColumnIndex("precio");
		marca = c.getColumnIndex("marca");

		c.moveToLast();
		String result = c.getString(nombre);
		
		Log.e("result= : ", result);

		db.execSQL("DELETE FROM cesta WHERE nombre='" + result + "'");

		c.close();

		return id + " " + nombre + " " + precio + " " + marca;
	}

	public String BuscarExistentes() {// con este método nos devuelve
										// el número de registros que
										// tenemos en una id concreto

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor Nc = db.rawQuery("select count(*) from cesta", null);
		Nc.moveToFirst();
		String a = Nc.getString(0);
		Nc.close();

		return a;
	}

	public void Borrar() {
		// TODO Auto-generated method stub
		SQLiteDatabase db;
		db = this.getReadableDatabase();

		db.execSQL("DROP TABLE IF EXISTS cesta");
		onCreate(db);
	}

}
