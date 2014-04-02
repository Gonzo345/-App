package aplicacions.aitorgonzo.ruleta;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import aplicacions.acj.ruleta.R;

public class Inicial extends Activity {

	protected PowerManager.WakeLock wakelock;
	private RadioGroup radioMesas;
	private RadioButton radioMesasbt;

	private AdView adView;
	private LinearLayout lytMain;

	int contadornums = 0;
	SeekBar seek;

	Button btinsertar, btinsertar2, btinsertar3, btinsertar4, btestadistica,
			btborrar, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8,
			btn9, btnborrar, btvermesa, bthelp;
	String numsel;
	TextView num1, txLista, lblnum;
	// Llamamos a la clase de base de datos
	Handler_sqlite DBH = new Handler_sqlite(Inicial.this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inicial3);

		// seek = (SeekBar) findViewById(R.id.seek);
		radioMesas = (RadioGroup) findViewById(R.id.radio_mesas);
		btinsertar = (Button) findViewById(R.id.btinsertar);
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
		btvermesa = (Button) findViewById(R.id.btvermesa);
		bthelp= (Button)findViewById(R.id.help);

		txLista = (TextView) findViewById(R.id.txLista);
		lblnum = (TextView) findViewById(R.id.lblnum);

		// ******************* BANNER ********************************

		lytMain = (LinearLayout) findViewById(R.id.lytMain);
		adView = new AdView(this, AdSize.BANNER,
				"ca-app-pub-1825821127744760/1018796934");
		lytMain.addView(adView);
		adView.bringToFront();
		adView.loadAd(new AdRequest());

		// *************************Impedir apagar la pantalla ***********************

		final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		this.wakelock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK,
				"etiqueta");
		wakelock.acquire();

		// *******************************Declaracion de los botones que
		// insertar�n los numeros teclado***********************

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
		btvermesa.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int selectedId = radioMesas.getCheckedRadioButtonId();
				// find the radiobutton by returned id
				radioMesasbt = (RadioButton) findViewById(selectedId);

				MostarMesa(radioMesasbt.getHint().toString());

			}
		});

		// ****************************************************************************************

		// Boton insertar en la ruleta
		btinsertar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int selectedId = radioMesas.getCheckedRadioButtonId();
				// find the radiobutton by returned id
				radioMesasbt = (RadioButton) findViewById(selectedId);

				LlamadaInsert(radioMesasbt.getHint() + "");

			}
		});

		btestadistica.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int selectedId = radioMesas.getCheckedRadioButtonId();

				radioMesasbt = (RadioButton) findViewById(selectedId);

				MostrarEstadistica(radioMesasbt.getHint() + "");

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
		
		
		bthelp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i= new Intent(Inicial.this, Help.class);
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
			if (numhint.equals("1") || numhint.equals("2")) {
				AnadirNormal(numhint);
			}

			if (numhint.equals("3")) {
				AnadirEspecial(numhint);
			}

			if (numhint.equals("4") || numhint.equals("5")
					|| numhint.equals("6") || numhint.equals("7")
					|| numhint.equals("8") || numhint.equals("9")
					|| numhint.equals("0")) {
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

			// Toast.makeText(Inicial.this, numsel + " en la mesa " + idruleta,
			// Toast.LENGTH_SHORT).show();

			SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss - dd/MM/yyyy");
			String format = s.format(new Date());

			// Toast.makeText(Inicial.this, format, Toast.LENGTH_SHORT).show();

			DBH.Insertar(idruleta, numsel, format);

			txLista.setText("");

			DBH.BuscarSiExiste("hola");

			DBH.close();

		} else {
			String cad = this.getString(R.string.noseleccionado);
			Toast.makeText(Inicial.this, cad, Toast.LENGTH_SHORT).show();
		}
	}

	public void MostarMesa(String num) {
		Intent i = new Intent(Inicial.this, MostrarNumerosMesa.class);

		i.putExtra("Mesa", num);
		startActivity(i);
	}

	public void MostrarEstadistica(String num) {
		Intent i = new Intent(Inicial.this, Estadistica.class);
		i.putExtra("Estadistica", num);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inicial, menu);
		return true;
	}

	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
	}
	
	
	
	/*Esto, junto con el onDestroy, hacen que la pantalla siga encendida hasta que la actividad termine*/
	protected void onDestroy(){
	        super.onDestroy();
	       
	        this.wakelock.release();
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