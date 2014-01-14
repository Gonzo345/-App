package aplicacions.aitorgonzo.barcode;

import static android.provider.BaseColumns._ID;
import static android.provider.BaseColumns._COUNT;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Handler_sqlite extends SQLiteOpenHelper {
	int COUNT;

	public Handler_sqlite(Context context) {

		super(context, "iBuyBy", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String query = "CREATE TABLE productos (barcode TEXT PRIMARY KEY, nombre TEXT, precio INTEGER, descripcion TEXT, imagen TEXT);";
		db.execSQL(query);
		
//		Cursor Nc= db.rawQuery("select count(*) from ruleta", null);
//		Nc.moveToFirst();
//		COUNT= Nc.getInt(0);
//		Nc.close();
		
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS productos");
		onCreate(db);
	}

	public void Insertar(String barcode, String nombre, int precio, String descripcion, String imagen) {
		ContentValues valores = new ContentValues();
		valores.put("barcode", barcode);
		valores.put("nombre", nombre);
		valores.put("precio", precio);
		valores.put("descripcion", descripcion);
		valores.put("imagen", imagen);

		this.getWritableDatabase().insert("productos", null, valores);
	}

	public void abrir() {
		this.getWritableDatabase();
	}

	public String leer() {
		String result="";
		String columnas[]={"barcode","nombre", "precio", "descripcion", "imagen"};// declaramos las columnas
		Cursor c = this.getReadableDatabase().query("productos", columnas, null, null, null, null, null);
		
		int bc, nom,pre, des, ima; // aqui ponemos los indices de las columnas en cada integer que hemos creado
		bc=c.getColumnIndex("barcode");
		nom=c.getColumnIndex("nombre");
		pre=c.getColumnIndex("precio");
		des=c.getColumnIndex("descripcion");
		ima=c.getColumnIndex("imagen");
		
		c.moveToLast(); //lo movemos al ultimo registro insertado
		
		result=c.getString(bc)+" "+c.getString(nom)+" "+c.getString(pre)+" "+c.getString(des)+" "+c.getString(ima);
		
		return result;
	}
	
	
	public String[] leerArray() {
		
		String result[]= new String[100];
		String columnas[]={"barcode","nombre", "precio", "descripcion", "imagen"};// declaramos las columnas
		Cursor c = this.getReadableDatabase().query("productos", columnas, null, null, null, null, null);
		
		int bc, nom,pre, des, ima; // aqui ponemos los indices de las columnas en cada integer que hemos creado
		bc=c.getColumnIndex("barcode");
		nom=c.getColumnIndex("nombre");
		pre=c.getColumnIndex("precio");
		des=c.getColumnIndex("descripcion");
		ima=c.getColumnIndex("imagen");

		
		int contador=0;
		for(c.moveToFirst(); !c.isAfterLast();c.moveToNext()){
			result[contador]=c.getString(bc)+" "+c.getString(nom)+" "+c.getString(pre)+" "+c.getString(des)+" "+c.getString(ima);
			contador++;
		}
		
		return result;
	}
	
	public String BuscarSiExiste(String barcode){
		
//		String result="";
//		String columnas[]={"barcode"};// declaramos las columnas
//		Cursor c = this.getReadableDatabase().query("productos", columnas, null, null, null, null, null);
//		
//		int bc; // aqui ponemos los indices de las columnas en cada integer que hemos creado
//		bc=c.getColumnIndex("barcode");
//		
//		c.moveToLast(); //lo movemos al ultimo registro insertado
//		
//		result=c.getString(bc);
		SQLiteDatabase db;
		
		db = this.getReadableDatabase();
		
		
		Cursor Nc= db.rawQuery("select count(*) from productos where barcode='"+barcode+"'", null);
		Nc.moveToFirst();
		String a= Nc.getString(0);
		Nc.close();
		
		return a;
	}

	public void cerrar() {
		this.close();

	}

}
