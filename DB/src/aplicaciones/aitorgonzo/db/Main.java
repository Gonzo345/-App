package aplicaciones.aitorgonzo.db;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity {
	int id = 1;
	String fecha = "1989", num = "Aitor";
	DBHelper dbh;
	TextView tv1;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button bt1 = (Button) findViewById(R.id.button1);
		Button bt2 = (Button) findViewById(R.id.bt2);
		Button bt3 = (Button) findViewById(R.id.bt3);
		tv1 = (TextView) findViewById(R.id.tv1);
		dbh = new DBHelper(this);

		tv1.setText("hola");
		dbh.abrir();// abre la bd
		dbh.Insertar(id, num, fecha);// inserta el

		// String res[]= dbh.leer();
		// tv1.setText(res.length);
		//
		// for(int j=0; j<res.length;j++){
		// tv1.setText(res[j]);
		// }

		tv1.setText(dbh.leer());
		dbh.cerrar();// cierra la bd
		db=dbh.getReadableDatabase();// con esto hacemos que la bd pueda ser accedida con los metodos rawquery o query del objeto SQLiteDatabase

		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// dbh.abrir();// abre la bd
				// dbh.Insertar(10, num, fecha);// inserta el
				// tv1.setText(dbh.leer());
				// dbh.cerrar();

				Cursor c = db.rawQuery("SELECT * FROM ruleta",
						null);

				// Recorremos los resultados para mostrarlos en pantalla
				tv1.setText("");
				if (c.moveToFirst()) {
					// Recorremos el cursor hasta que no haya más registros
					do {
						String id = c.getString(0);
						String  id_ruleta= c.getString(1);
						String  num= c.getString(2);
						String  fecha= c.getString(3);

						tv1.append(" " + id + " - " +" " + id_ruleta + " - " +" " + num + " - " + fecha + "\n");
					} while (c.moveToNext());
				}

			}
		});
		
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tv1.setText("");
			}
		});

		bt3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dbh.Insertar(3, "Hola Gonzo", "fecha en la que se juega");
			}
		});
	}

	// public void InsertarBoto() {
	// TextView tv1 = (TextView) findViewById(R.id.tv1);
	// DBHelper dbh = new DBHelper(this);
	// dbh.abrir();// abre la bd
	// dbh.Insertar(id, num, fecha);// inserta el
	//
	// // String res[]= dbh.leer();
	// // tv1.setText(res.length);
	// //
	// // for(int j=0; j<res.length;j++){
	// // tv1.setText(res[j]);
	// // }
	//
	// tv1.setText(dbh.leer());
	// dbh.cerrar();// cierra la bd
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
