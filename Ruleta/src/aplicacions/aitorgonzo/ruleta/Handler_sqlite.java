package aplicacions.aitorgonzo.ruleta;

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

		super(context, "RuletaAmericana", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String query = "CREATE TABLE ruleta (idnombre INTEGER PRIMARY KEY AUTOINCREMENT, id_ruleta TEXT, num TEXT, fecha INTEGER);";
		db.execSQL(query);
		
//		Cursor Nc= db.rawQuery("select count(*) from ruleta", null);
//		Nc.moveToFirst();
//		COUNT= Nc.getInt(0);
//		Nc.close();
		
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS ruleta");
		onCreate(db);
	}

	public void Insertar(String id_ruleta, String num, String fecha) {
		ContentValues valores = new ContentValues();
//		valores.put("idnombre", idnombre);
		valores.put("id_ruleta", id_ruleta);
		valores.put("num", num);
		valores.put("fecha", fecha);

		this.getWritableDatabase().insert("ruleta", null, valores);
	}

	public void abrir() {
		this.getWritableDatabase();
	}

	public String leer() {
		String result="";
		String columnas[]={"idnombre","id_ruleta", "num", "fecha"};// declaramos las columnas
		Cursor c = this.getReadableDatabase().query("ruleta", columnas, null, null, null, null, null);
		
		int id, idr, num, fecha; // aqui ponemos los indices de las columnas en cada integer que hemos creado
		id=c.getColumnIndex("idnombre");
		idr=c.getColumnIndex("id_ruleta");
		num=c.getColumnIndex("num");
		fecha=c.getColumnIndex("fecha");
		
		c.moveToLast(); //lo movemos al ultimo registro insertado
		
		result=c.getString(id)+" "+c.getString(idr)+" "+c.getString(num)+" "+c.getString(fecha);
		
		return result;
	}
	
	
	public String[] leerArray() {
		int a=Integer.parseInt(BuscarSiExiste("hola"));
		String result[]= new String[a];
		String columnas[]={"idnombre","id_ruleta", "num", "fecha"};// declaramos las columnas
		Cursor c = this.getReadableDatabase().query("ruleta", columnas, null, null, null, null, null);
		
		int id, idr, num, fecha; // aqui ponemos los indices de las columnas en cada integer que hemos creado
		id=c.getColumnIndex("idnombre");
		idr=c.getColumnIndex("id_ruleta");
		num=c.getColumnIndex("num");
		fecha=c.getColumnIndex("fecha");
		
		int contador=0;
		for(c.moveToFirst(); !c.isAfterLast();c.moveToNext()){
			result[contador]=c.getString(id)+" "+c.getString(idr)+" "+c.getString(num)+" "+c.getString(fecha);
			contador++;
		}
		
		return result;
	}
	
	public String BuscarSiExiste(String barcode){
		
//		String result="";
//		String columnas[]={"barcode"};// declaramos las columnas
//		Cursor c = this.getReadableDatabase().query("ruleta", columnas, null, null, null, null, null);
//		
//		int bc; // aqui ponemos los indices de las columnas en cada integer que hemos creado
//		bc=c.getColumnIndex("barcode");
//		
//		c.moveToLast(); //lo movemos al ultimo registro insertado
//		
//		result=c.getString(bc);
		SQLiteDatabase db;
		
		db = this.getReadableDatabase();
		
		
		Cursor Nc= db.rawQuery("select count(*) from ruleta where id_ruleta='1' or  id_ruleta='2' or  id_ruleta='3' or  id_ruleta='4'", null);
		Nc.moveToFirst();
		String a= Nc.getString(0);
		Nc.close();
		
		return a;
	}

	public void cerrar() {
		this.close();

	}

}
