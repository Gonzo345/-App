package com.example.dondeestanmisamigos;

import java.io.BufferedReader;
import java.util.StringTokenizer;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class EstadoInvitaciones extends Activity {

	private String id = "";
	private ListView listrecibidas, listenviadas;
	private String[] listarec = {};
	private String[] listaenv = {};
	private String resp_solicitudes, resp_invitaciones;
	private ArrayAdapter<String> adaptadoruno;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.estadoinvitaciones);

		// _______ Recuperamos el putextra_____________
		if (savedInstanceState == null) {
			savedInstanceState = getIntent().getExtras();
			if (savedInstanceState == null) {
				id = null;
			} else {
				id = savedInstanceState.getString("id");
			}
		} else {
			id = (String) savedInstanceState.getSerializable("id");
		}
		// ________________________

		listrecibidas = (ListView) findViewById(R.id.listrecibidas);
		adaptadoruno = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_checked, listarec);

		RellenarListas();
	}

	private void RellenarListas() {

		// String[] listarec;
		// String[] listaenv;

		try {
			ObtenerSolicitudes("http://www.menorcapp.net/dema/arraysolicitudes.php?id="
					+ id);
			// toast("final try");
		} catch (Exception e) {
			// toast("dins de catch");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ObtenerInvitaciones("http://www.menorcapp.net/dema/arrayinvitaciones.php?id="
					+ id);
			// toast("final try");
		} catch (Exception e) {
			// toast("dins de catch");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// // *********************************************
		// listarec = Parseo(resp_invitaciones);
		// listaenv = Parseo(resp_solicitudes);
		// // *********************************************
		// *********************************************

		// *********************************************

		// listrecibidas = (ListView) findViewById(R.id.listrecibidas);
		// ArrayAdapter<String> adaptadoruno = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_checked, listarec);
		// listrecibidas.setAdapter(adaptadoruno);
		// listrecibidas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		//
		// listenviadas = (ListView) findViewById(R.id.listenviadas);
		// ArrayAdapter<String> adaptadordos = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_checked, listaenv);
		// listenviadas.setAdapter(adaptadordos);
		// listenviadas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

	}

	private String[] Parseo(String chorizo) {

		// for(String token : chorizo.split(";")){
		// toast(token);
		// }
		String[] trozos = {};
		int a = 0;
		for (int j = 0; j < chorizo.lastIndexOf(";"); j++) {

			trozos[a] = chorizo.substring(j, chorizo.indexOf(";"));
			j = chorizo.indexOf(";");
			chorizo = chorizo.substring(j, chorizo.lastIndexOf(";"));
			a++;

		}

//		toast(chorizo);

		return trozos;
	}

	public void ObtenerSolicitudes(String url) throws Exception {
		BufferedReader in = null;
		String[] listasalida = {};
		try {

			AsyncHttpClient client = new AsyncHttpClient();
			client.get(url, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(String response) {

					try {

						resp_solicitudes = response;
						// toast("dins del try " + response);
						toast("1");
						toast("asignado: " + resp_solicitudes);
						toast("2");

						listarec = Parseo(resp_solicitudes);
						toast("3");
					

						listrecibidas.setAdapter(adaptadoruno);
						toast("4");
						listrecibidas
								.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
						toast("5");

					} catch (Exception e) {
						// toast("Final del catch onsucces");
						Log.e("peta",e+"");
						toast("peta");
					}
				}
			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
			// text.append(" ERROR ");
		}
	}

	public void ObtenerInvitaciones(String url) throws Exception {
		BufferedReader in = null;
		String[] listasalida;
		try {

			AsyncHttpClient client = new AsyncHttpClient();
			client.get(url, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(String response) {

					try {
						resp_invitaciones = response;

					} catch (Exception e) {

					}
				}
			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
			// text.append(" ERROR ");
		}
	}

	public void toast(String msg) {
		Toast.makeText(EstadoInvitaciones.this, msg, Toast.LENGTH_LONG).show();
	}
}
/*
 * listacesta = (ListView) findViewById(R.id.listacompra); ArrayAdapter<String>
 * adaptador = new ArrayAdapter<String>(this,
 * android.R.layout.simple_list_item_checked, NombresProductos);
 * 
 * listacesta.setAdapter(adaptador);
 * listacesta.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
 */