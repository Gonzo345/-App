package aplicaciones.aitorgonzo.dondeestanmisamigos;

import java.io.BufferedReader;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

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

public class Followers extends Activity{
	
	private Button bteliminar;
	private ListView listafollowers;
	private String[] lista = {};
	private String iduser = "";
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.followers);
		
		// Bot—n y ListView
		listafollowers = (ListView) findViewById(R.id.listfollowers);
		bteliminar = (Button) findViewById(R.id.bteliminar);
		
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
		
		try {
			ObtenerLista("http://www.menorcapp.net/dema/listafollowers.php?email=" + iduser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		// Boton que nos elimina a los followers seleccionados
		bteliminar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
					
				//Procesado de eliminar en remoto
				SparseBooleanArray seleccionados = listafollowers.getCheckedItemPositions();
				
				eliminarFollower(seleccionados);
				
				}
		
		});
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		try {
			ObtenerLista("http://www.menorcapp.net/dema/listafollowers.php?email=" + iduser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void eliminarFollower(SparseBooleanArray seleccionados) {
		// MŽtodo para aceptar invitaciones
		
			if(seleccionados == null || seleccionados.size() == 0){
				// Si no se ha marcado ninguno
				toast("No hay followers a eliminar");
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
                    resultado.append(listafollowers.getItemAtPosition(seleccionados.keyAt(i)).toString()+"   ----------->  "+seleccionados.keyAt(i));
            
                    //Texto del emisor a eliminar
                    String follower = listafollowers.getItemAtPosition(seleccionados.keyAt(i)).toString();
                    // Conectamos al server y le pasamos el valor de la ListView marcados
                    try {
      
                    	
                    	System.out.println(follower.contains("\n"));
                    	
                    	//Nos cargamos los saltos de l’nea que nos matan la inserci—n
                    	follower = follower.replaceAll("\\n", "");
                    	System.out.println(follower.contains("\n"));
                    	toast("Se va a eliminar al follower = " + follower);
						ObtenerLista("http://www.menorcapp.net/dema/eliminarfollower.php?receptor=" + iduser + "&emisor=" + follower);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
                }
            }
        }
			//Actualizamos resultados en el activity
            try {
            	ObtenerLista("http://www.menorcapp.net/dema/listafollowers.php?email=" + iduser);
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

						lista = Parseo(response);

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
				android.R.layout.simple_list_item_checked, lista);

		listafollowers.setAdapter(adaptador);
		listafollowers.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

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
		Toast.makeText(Followers.this, msg, Toast.LENGTH_LONG).show();
	}

}
