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

	int contadornums = 0;
	SeekBar seek;
	Button btinsertar, btinsertar2, btinsertar3, btinsertar4, btestadistica,
			btborrar, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8,
			btn9, btnborrar;
	String numsel;
	TextView num1, txLista, lblnum;
	// Llamamos a la clase de base de datos
	Handler_sqlite DBH = new Handler_sqlite(Inicial.this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inicial2);

		// seek = (SeekBar) findViewById(R.id.seek);
		btinsertar = (Button) findViewById(R.id.btinsetar);
		btinsertar2 = (Button) findViewById(R.id.btinsertar2);
		btinsertar3 = (Button) findViewById(R.id.btinsertar3);
		btinsertar4 = (Button) findViewById(R.id.btinsertar4);
		btestadistica = (Button) findViewById(R.id.btestadistica);
		btborrar = (Button) findViewById(R.id.btborrar);
		btn0 = (Button) findViewById(R.id.btn0);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button) findViewById(R.id.btn4);
		btn5 = (Button) findViewById(R.id.btn5);
		btn6 = (Button) findViewById(R.id.btn6);
		btn7 = (Button) findViewById(R.id.btn7);
		btn8 = (Button) findViewById(R.id.btn8);
		btn9 = (Button) findViewById(R.id.btn9);
		btnborrar = (Button) findViewById(R.id.btnborrar);

		txLista = (TextView) findViewById(R.id.txLista);
		lblnum = (TextView) findViewById(R.id.lblnum);

		// *******************************Declaracion de los botones que
		// insertarán los numeros teclado***********************

		btnborrar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	BorrarNumTeclado();

			}
		});

		btn0.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlHint(btn0.getHint().toString());
				contadornums++;

			}
		});

		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlHint(btn1.getHint().toString());

			}
		});

		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlHint(btn2.getHint().toString());

			}
		});

		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlHint(btn3.getHint().toString());

			}
		});

		btn4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlHint(btn4.getHint().toString());
				contadornums++;

			}
		});

		btn5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlHint(btn5.getHint().toString());
				contadornums++;

			}
		});

		btn6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlHint(btn6.getHint().toString());
				contadornums++;

			}
		});

		btn7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlHint(btn7.getHint().toString());
				contadornums++;

			}
		});

		btn8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlHint(btn8.getHint().toString());
				contadornums++;

			}
		});

		btn9.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlHint(btn9.getHint().toString());
				contadornums++;

			}
		});

		// ****************************************************************************************
		// ****************************************************************************************
		// ****************************************************************************************

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

				Intent i = new Intent(Inicial.this, Borrar.class);
				startActivity(i);
			}
		});

	}

	protected void BorrarNumTeclado() {
		lblnum.setText("");
		contadornums = 0;

		btn7.setEnabled(true);
		btn8.setEnabled(true);
		btn9.setEnabled(true);
		
	}

	// Metodo que controla los numeros que se van a passar al label del teclado
	public void ControlHint(String numhint) {
		if (contadornums <= 1) {
			if (numhint.equals("1") || numhint.equals("2")){
				AnadirNormal(numhint);
			}
			
			if (numhint.equals("3")){
				AnadirEspecial(numhint);
			}
			
			if (numhint.equals("4") || numhint.equals("5") || numhint.equals("6") || numhint.equals("7") || numhint.equals("8") || numhint.equals("9") || numhint.equals("0")){
				AnadirSoloUno(numhint);
			}
			
			// switch (numhint) {
			// case "1" :
			// AnadirNormal(numhint);
			// break;
			
			// case "2":
			// AnadirNormal(numhint);
			// break;
			
			// case "3":
			// AnadirEspecial(numhint);
			// break;
			
			// case "4":
			// AnadirSoloUno(numhint);
			// break;
			
			// case "5":
			// AnadirSoloUno(numhint);
			// break;
			
			// case "6":
			// AnadirSoloUno(numhint);
			// break;
			
			// case "7":
			// AnadirSoloUno(numhint);
			// break;
			
			// case "8":
			// AnadirSoloUno(numhint);
			// break;
			
			// case "9":
			// AnadirSoloUno(numhint);
			// break;
			
			// case "0":
			// AnadirSoloUno(numhint);
			// break;
			//
			// default:
			// break;
			// }
		}

	}

	private void AnadirEspecial(String numhint) {
		// TODO Auto-generated method stub
		lblnum.setText(lblnum.getText() + numhint);
		contadornums++;
		btn7.setEnabled(false);
		btn8.setEnabled(false);
		btn9.setEnabled(false);

	}

	private void AnadirSoloUno(String numhint) {
		// TODO Auto-generated method stub
		lblnum.setText(lblnum.getText() + numhint);
		contadornums++;
		contadornums++;
		contadornums++;

	}

	private void AnadirNormal(String numhint) {
		// TODO Auto-generated method stub
		lblnum.setText(lblnum.getText() + numhint);
		contadornums++;
	}

	// este metodo ejecuta el insert
	public void LlamadaInsert(String idruleta) {
		if (contadornums != 0) {

			numsel = lblnum.getText().toString();
			BorrarNumTeclado();

			Toast.makeText(Inicial.this, numsel + " en la mesa " + idruleta,
					Toast.LENGTH_SHORT).show();

			DBH.Insertar(idruleta, numsel, "FECHA");

			txLista.setText("");

			DBH.BuscarSiExiste("hola");

		} else {
			Toast.makeText(Inicial.this, "no se han insertado números...",
					Toast.LENGTH_SHORT).show();
		}
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