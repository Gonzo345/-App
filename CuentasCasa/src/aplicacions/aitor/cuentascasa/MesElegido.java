package aplicacions.aitor.cuentascasa;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MesElegido extends Activity {
	private String[] lista;
	private String respuesta = "", mesactual, anyactual;
	private ListView listacompras;
	private GregorianCalendar calendario;
	private TextView tvtotal, tvdata;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meselegido);

		// _______ Recuperamos el putextra_____________
		if (savedInstanceState == null) {
			savedInstanceState = getIntent().getExtras();
			if (savedInstanceState == null) {
				mesactual = null;
				anyactual = null;
			} else {
				mesactual = savedInstanceState.getString("mes");
				anyactual = savedInstanceState.getString("any");
			}
		} else {
			mesactual = (String) savedInstanceState.getSerializable("mes");
			anyactual = (String) savedInstanceState.getSerializable("any");
		}
		// ________________________
		
		
		tvtotal = (TextView) findViewById(R.id.tvtotal);
		tvdata = (TextView) findViewById(R.id.tvdata);

		tvdata.setText(mesactual+"/"+anyactual);
		
		RellenarLista();


	}
	

	private void RellenarLista() {
		try {
			ObtenerLista("http://www.menorcaapata.es/listarcompras.php?mes="
					+ mesactual + "&any=" + anyactual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ObtenerTotal("http://www.menorcaapata.es/totalcompras.php?mes="
					+ mesactual + "&any=" + anyactual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ObtenerLista(String url) throws Exception {
		BufferedReader in = null;

		try {

			AsyncHttpClient client = new AsyncHttpClient();
			client.get(url, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(String response) {
					System.out.println(response);
					try {

						respuesta = response;
						// toast(respuesta);

						lista = Parseo(respuesta);

						CargarSolicitudes();

					} catch (Exception e) {
						toast("Final del catch onsucces");
						Log.e("peta", e + "");
						// toast("peta por " + e);
					}
				}
			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
			// text.append(" ERROR ");
		}
	}

	public void ObtenerTotal(String url) throws Exception {
		BufferedReader in = null;

		try {

			AsyncHttpClient client = new AsyncHttpClient();
			client.get(url, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(String response) {
					System.out.println(response);
					try {

						tvtotal.setText("Total del mes: " + response + "Û");

					} catch (Exception e) {

					}
				}
			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
			// text.append(" ERROR ");
		}
	}

	public void CargarSolicitudes() {

		listacompras = (ListView) findViewById(R.id.listView1);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_checked, lista);

		listacompras.setAdapter(adaptador);
		listacompras.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

	}

	private String[] Parseo(String chorizo) {

		// Controlamos en numero de caracteres que pasamos en el response
		int contador = 0;
		for (int j = 0; j < chorizo.length(); j++) {
			if (chorizo.charAt(j) == ';') {
				contador++;
			}
		}

		String trozos[] = new String[contador];

		for (int j = 0; j < contador; j++) {
			trozos[j] = chorizo.substring(0, chorizo.indexOf(";"));

			chorizo = chorizo.substring(chorizo.indexOf(";") + 1,
					chorizo.lastIndexOf(";") + 1);
		}

		return trozos;
	}

	public void toast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}

	public void AfegirCompra() {

		Intent i = new Intent(MesElegido.this, AfegirCompra.class);
		startActivity(i);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		RellenarLista();
	}

}
