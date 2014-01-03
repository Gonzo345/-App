package aplicaciones.aitorgonzo.db;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity {
	int id = 0;
	String fecha = "1989", num = "Aitor";
	DBHelper dbh ;
	TextView tv1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button bt1 = (Button)findViewById(R.id.button1);
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
		
		
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dbh.abrir();// abre la bd
				dbh.Insertar(10, num, fecha);// inserta el
				tv1.setText(dbh.leer());
				dbh.cerrar();
				
			}
		});
		

	}

//	public void InsertarBoto() {
//		TextView tv1 = (TextView) findViewById(R.id.tv1);
//		DBHelper dbh = new DBHelper(this);
//		dbh.abrir();// abre la bd
//		dbh.Insertar(id, num, fecha);// inserta el
//
//		// String res[]= dbh.leer();
//		// tv1.setText(res.length);
//		//
//		// for(int j=0; j<res.length;j++){
//		// tv1.setText(res[j]);
//		// }
//
//		tv1.setText(dbh.leer());
//		dbh.cerrar();// cierra la bd
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
