package com.example.dondeestanmisamigos;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class EstadoInvitaciones extends Activity {

	private String id = "";
	private ListView listrecibidas, listenviadas;
	private String[] listarec = {}, mierda={"hola","kaka","adios"};
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
<<<<<<< HEAD

		listrecibidas = (ListView) findViewById(R.id.listrecibidas);
//		adaptadoruno = new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_checked, listarec);

=======

//		listrecibidas = (ListView) findViewById(R.id.listrecibidas);
//		adaptadoruno = new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_checked, listarec);

	}
	public void RecuperarCesta() {

		listrecibidas = (ListView) findViewById(R.id.listrecibidas);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_checked, listarec);

		listrecibidas.setAdapter(adaptador);
		listrecibidas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

>>>>>>> origin/Aitor
	}

	private void RellenarListas() {

<<<<<<< HEAD
		try {//obtenemos las solicitudes
=======
		try {// obtenemos las solicitudes
>>>>>>> origin/Aitor
			ObtenerSolicitudes("http://www.menorcapp.net/dema/arraysolicitudes.php?id="
					+ id);
		} catch (Exception e) {
			e.printStackTrace();
		}
<<<<<<< HEAD
		try {//obtenemos las invitaciones
=======
		try {// obtenemos las invitaciones
>>>>>>> origin/Aitor
			ObtenerInvitaciones("http://www.menorcapp.net/dema/arrayinvitaciones.php?id="
					+ id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String[] Parseo(String chorizo) {

<<<<<<< HEAD
		//Controlamos en numero de caracteres que pasamos en el response
		int contador=0;
		for(int j =0; j<chorizo.length();j++){
			if(chorizo.charAt(j)==';'){
				contador++;
			}
		}
		
		String trozos[]= new String[contador];
		
		for (int j = 0; j < contador; j++) {
            trozos[j-1] = chorizo.substring(0,chorizo.indexOf(";"));
            
            chorizo = chorizo.substring(chorizo.indexOf(";")+1,
                            chorizo.lastIndexOf(";")+1);
            String cadena=trozos[contador-1].toString();
            toast("Dentro de trozos: " + cadena);
            toast(chorizo);
    }
		
=======
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
		
		for(int j=0; j<trozos.length;j++){
			toast("valor en la pos "+j+" "+trozos[j]);
		}

>>>>>>> origin/Aitor
		return trozos;
	}

	public void ObtenerSolicitudes(String url) throws Exception {
<<<<<<< HEAD
			// Solicitudes entrantes
=======

>>>>>>> origin/Aitor
		try {
			AsyncHttpClient client = new AsyncHttpClient();
			client.get(url, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(String response) {

					try {

						resp_solicitudes = response;
						// toast("dins del try " + response);
						toast("asignado: " + resp_solicitudes);
<<<<<<< HEAD
						
						toast("2");
						listarec = Parseo(resp_solicitudes);
						
						CargarSolicitudes(listarec);

					} catch (Exception e) {
						// toast("Final del catch onsuccess");
						Log.e("peta", e + "");
						toast("peta");
=======

						listarec = Parseo(resp_solicitudes);

//						CargarSolicitudes();
						RecuperarCesta();

					} catch (Exception e) {
						// toast("Final del catch onsucces");
						Log.e("peta", e + "");
						toast("peta por " + e);
>>>>>>> origin/Aitor
					}
				}

			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}
	}

<<<<<<< HEAD
	private void CargarSolicitudes(String[] solicitudes) {

		ArrayAdapter<String> adaptadoruno = new ArrayAdapter<String>(this,
				 android.R.layout.simple_list_item_checked, solicitudes);
				 listrecibidas.setAdapter(adaptadoruno);
				 listrecibidas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
	}
	public void ObtenerInvitaciones(String url) throws Exception {
			// Invitaciones salientes
=======
//	private void CargarSolicitudes() {
//
//		// ArrayAdapter<String> adaptadoruno = new ArrayAdapter<String>(this,
//		// android.R.layout.simple_list_item_checked, listarec);
//		// listrecibidas.setAdapter(adaptadoruno);
//		// listrecibidas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//
//		listrecibidas = (ListView) findViewById(R.id.listrecibidas);
//		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_checked, listarec);
//
//		listrecibidas.setAdapter(adaptador);
//		listrecibidas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//
//	}

	public void ObtenerInvitaciones(String url) throws Exception {

>>>>>>> origin/Aitor
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