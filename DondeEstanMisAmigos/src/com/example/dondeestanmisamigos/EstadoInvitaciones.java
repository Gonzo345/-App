package com.example.dondeestanmisamigos;

import java.io.BufferedReader;
import java.util.StringTokenizer;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class EstadoInvitaciones extends Activity {

	private String id = "";
	private ListView listrecibidas, listenviadas;
	private String[] listarec;
	private String[] listaenv;

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

		RellenarListas();
	}

	private void RellenarListas() {

//		String[] listarec;
//		String[] listaenv;

		try {
			ObtenerSolicitudes("http://www.menorcapp.net/dema/arraysolicitudes.php?id="+ id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ObtenerSolicitudes("http://www.menorcapp.net/dema/arrayinvitaciones.php?id="+ id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		for(int a=0; a<listaenv.length;a++){
//			toast(listaenv[a]);
//		}
//		for(int a=0; a<listarec.length;a++){
//			toast(listarec[a]);
//		}

		listrecibidas = (ListView) findViewById(R.id.listrecibidas);
		ArrayAdapter<String> adaptadoruno = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_checked, listarec);
		listrecibidas.setAdapter(adaptadoruno);
		listrecibidas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		listenviadas = (ListView) findViewById(R.id.listenviadas);
		ArrayAdapter<String> adaptadordos = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_checked, listaenv);
		listenviadas.setAdapter(adaptadordos);
		listenviadas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

	}

	public void ObtenerSolicitudes(String url) throws Exception {
		BufferedReader in = null;
		String [] listasalida;
		try {

			AsyncHttpClient client = new AsyncHttpClient();
			client.get(url, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(String response) {

					try {
						String[] lista;
						StringTokenizer stresponse = new StringTokenizer(
								response, ";");

						int a = 0;
						while (stresponse.hasMoreTokens()) {
							listarec[a]=stresponse.nextToken();
							toast(stresponse.nextToken());
						}

					} catch (Exception e) {

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
		String [] listasalida;
		try {

			AsyncHttpClient client = new AsyncHttpClient();
			client.get(url, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(String response) {

					try {
						String[] lista;
						StringTokenizer stresponse = new StringTokenizer(
								response, ";");

						int a = 0;
						while (stresponse.hasMoreTokens()) {
							listaenv[a]=stresponse.nextToken();
						}

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