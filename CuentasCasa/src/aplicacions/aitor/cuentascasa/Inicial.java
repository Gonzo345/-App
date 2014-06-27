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

public class Inicial extends Activity {
	private String[] lista;
	private String respuesta = "", mesactual, anyactual;
	private ListView listacompras;
	private GregorianCalendar calendario;
	private TextView tvtotal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inicial);

		tvtotal = (TextView) findViewById(R.id.tvtotal);

		Date date = new Date();
		SimpleDateFormat a = new SimpleDateFormat("M");
		mesactual = a.format(date);

		a = new SimpleDateFormat("yyyy");
		anyactual = a.format(date);

		mesactual = Integer.parseInt(mesactual) - 1 + "";

		Button btafegircompra = (Button) findViewById(R.id.btafegir);
		Button bteliminar = (Button) findViewById(R.id.bteliminar);
		Button bttriar = (Button) findViewById(R.id.bttriar);

		RellenarLista();

		btafegircompra.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AfegirCompra();
			}
		});

		bteliminar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SparseBooleanArray seleccionados = listacompras
						.getCheckedItemPositions();

				eliminarSeleccionados(seleccionados);

			}

		});
		bttriar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Inicial.this,TriarMes.class);
				startActivity(i);
			}
			
		});

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

		Intent i = new Intent(Inicial.this, AfegirCompra.class);
		startActivity(i);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		RellenarLista();
	}

	public void eliminarSeleccionados(SparseBooleanArray seleccionados) {
		// MŽtodo para aceptar invitaciones

		if (seleccionados == null || seleccionados.size() == 0) {
			// Si no se ha marcado ninguno
			toast("No hi ha res triat");
		} else {
			// Si se han seleccionado, miro sus valores
			// Esto es para ir creando un mensaje largo que mostrar‡ al final
			StringBuilder resultado = new StringBuilder();

			// Recorro mi "array" de elementos seleccionados
			final int size = seleccionados.size();
			for (int i = 0; i < size; i++) {
				// Si valueAt(i) es true, es que estaba seleccionado
				if (seleccionados.valueAt(i)) {
					// en keyAt(i) obtengo su posici—n

					// Texto del emisor a eliminar
					String valor = listacompras.getItemAtPosition(
							seleccionados.keyAt(i)).toString();
					// Conectamos al server y le pasamos el valor de la ListView
					// marcados
					try {

						System.out.println(valor.contains("\n"));

						// Nos cargamos los saltos de l’nea que nos matan la
						// inserci—n
						valor = valor.replaceAll("\\n", "");
						System.out.println(valor);
						EnviarDelete("http://www.menorcaapata.es/eliminarcompra.php?cadena="
								+ valor
								+ "&mes="
								+ mesactual
								+ "&any="
								+ anyactual);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
		// Actualizamos resultados en el activity
		RellenarLista();
	}

	public void EnviarDelete(String url) throws Exception {
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

}
