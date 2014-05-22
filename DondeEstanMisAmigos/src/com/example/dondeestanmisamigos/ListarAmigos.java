package com.example.dondeestanmisamigos;

import java.io.BufferedReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class ListarAmigos extends Activity {

	private Button btsolicitudes, btanadir;
	private ListView listaamigos;
	private String id="";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listaramigos);

		btsolicitudes = (Button) findViewById(R.id.btsolicitudes);
		btanadir = (Button) findViewById(R.id.btanadir);
		
		//_______ Recuperamos el putextra_____________
		if (savedInstanceState == null) {
		    savedInstanceState = getIntent().getExtras();
		    if(savedInstanceState == null) {
		        id= null;
		    } else {
		        id= savedInstanceState.getString("id");
		    }
		} else {
		    id= (String) savedInstanceState.getSerializable("id");
		}
		//________________________

		// comprobamos si tenemos solicitudes pendientes
		try {
			ComprobarSolicitudes("http://www.menorcapp.net/dema/comprobarinvitaciones.php?email="+id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ObtenerLista("http://www.menorcapp.net/dema/obtenerlistaamigos.php?email="+id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Boton que nos lleva a las solicitudes pendientes
		btsolicitudes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(ListarAmigos.this,
						EstadoInvitaciones.class);
				i.putExtra("id",id);
				startActivity(i);

			}
		});

		// Boton que no lleva para agregar a un nuevo ususario
		btanadir.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ListarAmigos.this, Invitar.class);
				startActivity(i);
			}
		});

	}

	public void ComprobarSolicitudes(String url) throws Exception {
		BufferedReader in = null;

		try {

			AsyncHttpClient client = new AsyncHttpClient();
			client.get(url, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(String response) {
					System.out.println(response);
					// text.setText(response);

					// toast(response);

					try {

						int ini = response.indexOf("1");
						response = response.substring(ini, ini + 1);

					} catch (Exception e) {

					}

					if (response.equals("0")) {
						toast("0");
					} else {
						toast("Tienes " + response + " soliditudes pendientes");
					}
				}
			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
			// text.append(" ERROR ");
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
					// text.setText(response);

					// toast(response);

					try {

						toast(response);

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
		Toast.makeText(ListarAmigos.this, msg, Toast.LENGTH_LONG).show();
	}

}
