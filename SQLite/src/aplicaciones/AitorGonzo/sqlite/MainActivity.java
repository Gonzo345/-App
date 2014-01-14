package aplicaciones.AitorGonzo.sqlite;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView tv1;
	Handler_sqlite dbh;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dbh = new Handler_sqlite(this);
		db= dbh.getReadableDatabase();

		tv1 = (TextView) findViewById(R.id.tv1);
		Button btinsertar = (Button) findViewById(R.id.btInsertar);
		Button btborrar = (Button) findViewById(R.id.btborrar);
		Button btconsultar = (Button) findViewById(R.id.btconsultar);

		dbh.abrir();

		dbh.Insertar("1", "Aitor Costa", "1989");
		dbh.Insertar("1", "Nidia", "1990");
		dbh.Insertar("1", "Box", "2013");

		dbh.cerrar();

		btinsertar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dbh.Insertar("2", "Info enviada con el método", "avui");
				
			}
		});
		
		btborrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tv1.setText("");
				dbh.ActualizarCount();
			}
		});
		btconsultar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				dbh.abrir();
				
				tv1.setText("");
				String x[] = dbh.leerArray();

				for (int j = 0; j < x.length; j++) {
					tv1.setText(tv1.getText() + "\n" + x[j]);
				}
                
                dbh.cerrar();
			}
			
		});

	}

}
