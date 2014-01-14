package aplicaciones.AitorGonzo.sqlite;

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

		super(context, "NombreBD", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String query = "CREATE TABLE NombreTabla ("
				+ _ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, id_ruleta TEXT, num TEXT, fecha TEXT);";
		db.execSQL(query);
		
		ActualizarCount();
		
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS NombreTabla");
		onCreate(db);
	}

	public void Insertar(String id_ruleta, String num, String fecha) {
		ContentValues valores = new ContentValues();
		valores.put("id_ruleta", id_ruleta);
		valores.put("num", num);
		valores.put("fecha", fecha);

		this.getWritableDatabase().insert("NombreTabla", null, valores);
	}

	public void abrir() {
		this.getWritableDatabase();
	}

	public String leer() {
		String result="";
		String columnas[]={_ID,"id_ruleta", "num", "fecha"};// declaramos las columnas
		Cursor c = this.getReadableDatabase().query("NombreTabla", columnas, null, null, null, null, null);
		
		int id, idr,num,fec; // aqui ponemos los indices de las columnas en cada integer que hemos creado
		id=c.getColumnIndex(_ID);
		idr=c.getColumnIndex("id_ruleta");
		num=c.getColumnIndex("num");
		fec=c.getColumnIndex("fecha");
		
		c.moveToLast(); //lo movemos al ultimo registro insertado
		
		result=c.getString(id)+" "+c.getString(idr)+" "+c.getString(num)+" "+c.getString(fec)+"\n";
		
		return result;
	}
	
	
	public String[] leerArray() {
		
		String result[]= new String[COUNT];
		String columnas[]={_ID,"id_ruleta", "num", "fecha"};// declaramos las columnas
		Cursor c = this.getReadableDatabase().query("NombreTabla", columnas, null, null, null, null, null);
		
		int id, idr,num,fec; // aqui ponemos los indices de las columnas en cada integer que hemos creado
		id=c.getColumnIndex(_ID);
		idr=c.getColumnIndex("id_ruleta");
		num=c.getColumnIndex("num");
		fec=c.getColumnIndex("fecha");
		
		
		int contador=0;
		for(c.moveToFirst(); !c.isAfterLast();c.moveToNext()){
			result[contador]=c.getString(id)+" "+c.getString(idr)+" "+c.getString(num)+" "+c.getString(fec);
			contador++;
		}
		
		return result;
	}
	
	public void ActualizarCount(){
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor Nc= db.rawQuery("select count(*) from NombreTabla where id_ruleta='1'", null);
		Nc.moveToFirst();
		String a= Nc.getString(0);
		
		COUNT= Integer.parseInt(a);
		Nc.close();
		
		
	}

	public void cerrar() {
		this.close();

	}

}
