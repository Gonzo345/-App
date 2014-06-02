package aplicaciones.aitorgonzo.dondeestanmisamigos;

import java.io.BufferedReader;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import aplicaciones.aitorgonzo.dondeestanmisamigos.R;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class ListarAmigos extends Activity {

	private Button btsolicitudes, btanadir, btfollowers;
	private ListView listaamigos;
	private String[] lista = {};
	private String iduser = "", resp_amigos = "";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listaramigos);
		
		//Arrancamos el servicio
		 startService(new Intent(ListarAmigos.this,ServicioLocalizacion.class));

		 
		listaamigos = (ListView) findViewById(R.id.listView1);
		btsolicitudes = (Button) findViewById(R.id.btsolicitudes);
		btanadir = (Button) findViewById(R.id.btanadir);
		btfollowers = (Button) findViewById(R.id.btfollowers);

		// _______ Recuperamos el putextra_____________
		if (savedInstanceState == null) {
			savedInstanceState = getIntent().getExtras();
			if (savedInstanceState == null) {
				iduser = null;
			} else {
				iduser = savedInstanceState.getString("id");
			}
		} else {
			iduser = (String) savedInstanceState.getSerializable("id");
		}
		// ________________________
		// __________GUARDAMOS LAS VARIABLES EN SHAREDPREFERENCES PARA UTILIZARLAS EN EL SERVICIO______________
		SharedPreferences prefes= getSharedPreferences("pref_variables", MODE_PRIVATE);
		SharedPreferences.Editor editor= prefes.edit();
		editor.putString("id", iduser);
		editor.commit();
		// ________________________
		
		

		try {
			ComprobarSolicitudes("http://www.menorcapp.net/dema/comprobarinvitaciones.php?email="
					+ iduser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ObtenerLista("http://www.menorcapp.net/dema/obtenerlistaamigos.php?email="
					+ iduser);
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
				i.putExtra("id", iduser);
				startActivity(i);

			}
		});

		// Boton que nos lleva para agregar a un nuevo ususario
		btanadir.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ListarAmigos.this, Invitar.class);
				i.putExtra("id", iduser);
				startActivity(i);
			}
		});
		
		// Boton que nos lleva a las solicitudes pendientes
		btfollowers.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ListarAmigos.this,Followers.class);
				i.putExtra("id", iduser);
				startActivity(i);
			}
		});

		// Tratamos la selecci—n de un contacto al clicar en Žl
		listaamigos.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String selectedFromList = (listaamigos
						.getItemAtPosition(position).toString());
				
				CrearPeticiona(selectedFromList);
				
				Intent i = new Intent(ListarAmigos.this, MostrarPosicion.class);
				i.putExtra("id",iduser);
				i.putExtra("amigo", selectedFromList);
				startActivity(i);

			}

			private void CrearPeticiona(String selectedFromList) {
				try {

					AsyncHttpClient client = new AsyncHttpClient();
					client.get("http://www.menorcapp.net/dema/crearpeticion.php?emisor="+iduser+"&receptor="+selectedFromList, new AsyncHttpResponseHandler() {
						@Override
						public void onSuccess(String response) {
							System.out.println(response);
							// text.setText(response);

							// toast(response);

							try {

								int ini = response.indexOf("0");
								response = response.substring(ini, ini + 1);

							} catch (Exception e) {

							}

							if (response.equals("0")) {
								// toast("0");
							} else {
								toast("No se ha podido crear la petici—n...");
							}
						}
					});

				} catch (Exception e) {
					Log.e("log_tag", "Error in http connection " + e.toString());
				}
			}
		});

	}
	
	@Override
	protected void onResume() {
		super.onResume();
		try {
			ObtenerLista("http://www.menorcapp.net/dema/obtenerlistaamigos.php?email="
					+ iduser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
						// toast("0");
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
					try {

						resp_amigos = response;

						lista = Parseo(resp_amigos);

						CargarSolicitudes();

					} catch (Exception e) {
						// toast("Final del catch onsucces");
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

	public void CargarSolicitudes() {

		// listaamigos = (ListView) findViewById(R.id.listView1);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, lista);

		listaamigos.setAdapter(adaptador);
		listaamigos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

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
		Toast.makeText(ListarAmigos.this, msg, Toast.LENGTH_LONG).show();
	}

}
