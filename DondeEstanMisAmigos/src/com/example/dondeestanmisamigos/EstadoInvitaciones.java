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
		RellenarListas();

		listrecibidas = (ListView) findViewById(R.id.listrecibidas);
		adaptadoruno = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_checked, listarec);

	}

	private void RellenarListas() {

		try {//obtenemos las solicitudes
			ObtenerSolicitudes("http://www.menorcapp.net/dema/arraysolicitudes.php?id="
					+ id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {//obtenemos las invitaciones
			ObtenerInvitaciones("http://www.menorcapp.net/dema/arrayinvitaciones.php?id="
					+ id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String[] Parseo(String chorizo) {

		//Controlamos en numero de caracteres que pasamos en el response
		int contador=0;
		for(int j =0; j<chorizo.length();j++){
			if(chorizo.charAt(j)==';'){
				contador++;
			}
		}
		
		String trozos[]= new String[contador];
		
		for (int j = 0; j < contador; j++) {
			trozos[contador-1] = chorizo.split(";").toString();
			chorizo = chorizo.substring(chorizo.indexOf(";"),
					chorizo.lastIndexOf(";"));
		}

		return trozos;
	}

	public void ObtenerSolicitudes(String url) throws Exception {

		try {
			AsyncHttpClient client = new AsyncHttpClient();
			client.get(url, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(String response) {

					try {

						resp_solicitudes = response;
						// toast("dins del try " + response);
						toast("asignado: " + resp_solicitudes);
						
						listarec = Parseo(resp_solicitudes);
						
						CargarSolicitudes(listarec);

					} catch (Exception e) {
						// toast("Final del catch onsucces");
						Log.e("peta", e + "");
						toast("peta");
					}
				}

			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}
	}

	private void CargarSolicitudes(String[] solicitudes) {

		ArrayAdapter<String> adaptadoruno = new ArrayAdapter<String>(this,
				 android.R.layout.simple_list_item_checked, solicitudes);
				 listrecibidas.setAdapter(adaptadoruno);
				 listrecibidas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
	}
	public void ObtenerInvitaciones(String url) throws Exception {

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