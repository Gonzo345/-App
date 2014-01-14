package aplicacions.aitorgonzo.ruleta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import aplicacions.acj.ruleta.R;

public class Inicial extends Activity {

	SeekBar seek;
	Button btinsertar, btinsertar2, btinsertar3, btinsertar4, btestadistica,
			btborrar;
	String numsel;
	TextView num1, txLista;
	// Llamamos a la clase de base de datos
	Handler_sqlite DBH = new Handler_sqlite(Inicial.this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inicial);

		// seek = (SeekBar) findViewById(R.id.seek);
		btinsertar = (Button) findViewById(R.id.btinsetar);
		btinsertar2 = (Button) findViewById(R.id.btinsertar2);
		btinsertar3 = (Button) findViewById(R.id.btinsertar3);
		btinsertar4 = (Button) findViewById(R.id.btinsertar4);
		btestadistica = (Button) findViewById(R.id.btestadistica);
		btborrar = (Button) findViewById(R.id.btborrar);

		txLista = (TextView) findViewById(R.id.txLista);

		// creamos el combobox
		Spinner spin = (Spinner) findViewById(R.id.spi1);
		ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
				R.array.num, android.R.layout.simple_spinner_dropdown_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adapter);

		spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int pos, long id) {

				numsel = parentView.getItemAtPosition(pos).toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		// Boton insertar en la ruleta 1
		btinsertar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				LlamadaInsert("1");

			}
		});
		// Boton insertar en la ruleta 2
		btinsertar2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				LlamadaInsert("2");

			}
		});
		// Boton insertar en la ruleta 3
		btinsertar3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				LlamadaInsert("3");

			}
		});
		// Boton insertar en la ruleta 4
		btinsertar4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				LlamadaInsert("4");

			}
		});

		btestadistica.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Inicial.this, VerEstadisticas.class);
				startActivity(i);
			}
		});

		btborrar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i= new Intent(Inicial.this, Borrar.class);
				startActivity(i);
			}
		});

	}

	public void LlamadaInsert(String idruleta) {
		Toast.makeText(Inicial.this, numsel + " en la mesa " + idruleta,
				Toast.LENGTH_SHORT).show();

		DBH.Insertar(idruleta, numsel, "FECHA");

		txLista.setText("");
		// String x[] = DBH.leerArray();
		// for (int j = 0; j < x.length; j++) {
		// txLista.setText(txLista.getText()+"\n"+x[j]);
		// }

		DBH.BuscarSiExiste("hola");

		// Toast.makeText(Inicial.this, DBH.BuscarSiExiste("hola"),
		// Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inicial, menu);
		return true;
	}

}

// *******************************************************************************
// btestadistica.setOnClickListener(new OnClickListener() {
//
// @Override
// public void onClick(View v) {
//
// Intent i = new Intent(Inicial.this, Estadistica.class);
// startActivity(i);
//
// }
// });

// *******************************************************************************
// Toast.makeText(Inicial.this, "el num es: " + numsel,
// Toast.LENGTH_SHORT).show();
// String id_ruleta = "Hola";
//
// DBH.Insertar("1", numsel, "FECHA");
//
// txLista.setText("");
// String x[] = DBH.leerArray();
// for (int j = 0; j < x.length; j++) {
// txLista.setText(txLista.getText()+"\n"+x[j]);
// }
//
// DBH.BuscarSiExiste("hola");
//
// Toast.makeText(Inicial.this, DBH.BuscarSiExiste("hola"),
// Toast.LENGTH_SHORT).show();