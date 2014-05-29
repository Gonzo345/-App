package aplicaciones.aitorgonzo.dondeestanmisamigos;

import java.io.BufferedReader;

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
import android.widget.Toast;

import aplicaciones.aitorgonzo.dondeestanmisamigos.R;
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
		
		// Botones de aceptar y rechazar invitaci—n
		Button btaceptar = (Button)findViewById(R.id.btaceptar);
		Button btrechazar = (Button)findViewById(R.id.btrechazar);
		
		btaceptar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				//Procesado de aceptar en remoto
				SparseBooleanArray seleccionados = listrecibidas.getCheckedItemPositions();
				
				aceptarSeleccionados(seleccionados);
//				id=username;
//				
//				try {
//					// URL de ejemplo: http://www.menorcapp.net/DEMA/%20login.php?id=gonzo&pass=1234567890
//					CogerResultadoPHP("http://www.menorcapp.net/DEMA/login.php?id="+ username + "&pass=" + password);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
			}
		});
		
		btrechazar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				//Procesado de rechazar en remoto
				SparseBooleanArray seleccionados = listrecibidas.getCheckedItemPositions();
				
				rechazarSeleccionados(seleccionados);
				
//				id=username;
//				
//				try {
//					// URL de ejemplo: http://www.menorcapp.net/DEMA/%20login.php?id=gonzo&pass=1234567890
//					CogerResultadoPHP("http://www.menorcapp.net/DEMA/login.php?id="+ username + "&pass=" + password);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
			}
		});

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
		
		RellenarListas();

	}
	
	public void aceptarSeleccionados(SparseBooleanArray seleccionados) {
		// MŽtodo para aceptar invitaciones
		
			if(seleccionados == null || seleccionados.size() == 0){
				// Si no se ha marcado ninguno
				toast("No hay invitaciones a aceptar");
			}
			else{
            // Si se han seleccionado, miro sus valores
            //Esto es para ir creando un mensaje largo que mostrar‡ al final
            StringBuilder resultado=new StringBuilder();
 
            //Recorro mi "array" de elementos seleccionados
            final int size=seleccionados.size();
            for (int i=0; i<size; i++) {
                //Si valueAt(i) es true, es que estaba seleccionado
                if (seleccionados.valueAt(i)) {
                    // en keyAt(i) obtengo su posici—n
                	// Toast.makeText(this, "Vuelta empezada", Toast.LENGTH_SHORT).show();	
                	
                	//Valor de la fila
                    resultado.append(listrecibidas.getItemAtPosition(seleccionados.keyAt(i)).toString()+"   ----------->  "+seleccionados.keyAt(i));
            
                    //Texto del emisor a aceptarle la invitaci—n
                    String invitacionde = listrecibidas.getItemAtPosition(seleccionados.keyAt(i)).toString();
                    // Conectamos al server y le pasamos el valor de la ListView marcados
                    try {
                    	toast("ID = " + id);
                    	
                    	System.out.println(invitacionde.contains("\n"));
                    	
                    	//Nos cargamos los saltos de l’nea que nos matan la inserci—n
                    	invitacionde = invitacionde.replaceAll("\\n", "");
                    	System.out.println(invitacionde.contains("\n"));
                    	toast("Invitacionde = " + invitacionde);
						CogerResultadoPHP("http://www.menorcapp.net/dema/crearamistad.php?email1=" + id + "&email2=" + invitacionde);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
                }
            }
            //Actualizamos resultados en el activity
            RellenarListas();
        }
	}
	
	public void rechazarSeleccionados(SparseBooleanArray seleccionados) {
		// MŽtodo para rechazar invitaciones
		if(seleccionados == null || seleccionados.size() == 0){
			// Si no se ha marcado ninguno
			toast("No hay invitaciones a rechazar");
		}
		else{
        // Si se han seleccionado, miro sus valores
        // Esto es para ir creando un mensaje largo que mostrar‡ al final
        StringBuilder resultado=new StringBuilder();

        //Recorro mi "array" de elementos seleccionados
        final int size=seleccionados.size();
        for (int i=0; i<size; i++) {
            //Si valueAt(i) es true, es que estaba seleccionado
            if (seleccionados.valueAt(i)) {
                // en keyAt(i) obtengo su posici—n
            	// Toast.makeText(this, "Vuelta empezada", Toast.LENGTH_SHORT).show();	
            	
            	//Valor de la fila
                resultado.append(listrecibidas.getItemAtPosition(seleccionados.keyAt(i)).toString()+"   ----------->  "+seleccionados.keyAt(i));
        
                //Texto del emisor a aceptarle la invitaci—n
                String invitacionde = listrecibidas.getItemAtPosition(seleccionados.keyAt(i)).toString();
                
                // Conectamos al server y le pasamos el valor de la ListView marcados
                try {
                	toast("ID = " + id);
                	
                	System.out.println(invitacionde.contains("\n"));
                	
                	//Nos cargamos los saltos de l’nea que nos matan la inserci—n
                	invitacionde = invitacionde.replaceAll("\\n", "");
                	System.out.println(invitacionde.contains("\n"));
                	toast("Invitacionde = " + invitacionde);
					CogerResultadoPHP("http://www.menorcapp.net/dema/crearamistad.php?email1=" + id + "&email2=" + invitacionde + "&cancelar=si");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            }
        }
        //Actualizamos resultados en el activity
        RellenarListas();
    }
}

	public void CargarSolicitudes() {

		listrecibidas = (ListView) findViewById(R.id.listrecibidas);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_checked, listarec);

		listrecibidas.setAdapter(adaptador);
		listrecibidas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

	}
	public void CargarInvitaciones() {
		
		listenviadas = (ListView) findViewById(R.id.listenviadas);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, listaenv);
		
		listenviadas.setAdapter(adaptador);
//		listenviadas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
	}

	private void RellenarListas() {

		try {// obtenemos las solicitudes
			ObtenerSolicitudes("http://www.menorcapp.net/dema/arraysolicitudes.php?id="
					+ id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {// obtenemos las invitaciones
			ObtenerInvitaciones("http://www.menorcapp.net/dema/arrayinvitaciones.php?id="
					+ id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void CogerResultadoPHP(String url) throws Exception {
		BufferedReader in = null;

		try {

			AsyncHttpClient client = new AsyncHttpClient();
			client.get(url, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(String response) {
					System.out.println(response);
					//text.setText(response);

					// toast(response);

					try {

						int ini = response.indexOf("1");
						response = response.substring(ini, ini + 1);

					} catch (Exception e) {

					}

					//Si va bien devuelve 1
					if (response.equals("1")) {
						
						toast("Invitaci—n/es aceptada/s con Žxito");
					} else {
					
					//Si va mal devuelve 0
						toast("Algo ha salido mal aceptando la/s invitaci—n/es");
					}
				}
			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}
		
		RellenarListas();
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
		
//		for (int j = 0; j < trozos.length; j++) {
//			toast("valor en la pos " + j + " " + trozos[j]);
//		}

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


						listarec = Parseo(resp_solicitudes);

						CargarSolicitudes();

					} catch (Exception e) {
						// toast("Final del catch onsucces");
						Log.e("peta", e + "");
						//toast("peta por " + e);
					}
				}

			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}
	}
	public void ObtenerInvitaciones(String url) throws Exception {

		try {
			AsyncHttpClient client = new AsyncHttpClient();
			client.get(url, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(String response) {

					try {

						resp_invitaciones = response;
						// toast("dins del try " + response);
						//toast("asignado: " + resp_invitaciones);

						listaenv = Parseo(resp_invitaciones);

						CargarInvitaciones();

					} catch (Exception e) {
						// toast("Final del catch onsucces");
						Log.e("peta", e + "");
						//toast("peta por " + e);
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