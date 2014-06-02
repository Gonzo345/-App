package aplicaciones.aitorgonzo.dondeestanmisamigos;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Followers extends Activity{
	
	private Button bteliminar;
	private ListView listafollowers;
	private String[] lista = {};
	private String iduser = "";
	
	protected void onCreate(Bundle savedInstanceState) {
		
		//Bot—n y ListView
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
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.followers);
		
	}
	
	
	
	private void RellenarListas() {

		try {// obtenemos las solicitudes
			ObtenerSolicitudes("http://www.menorcapp.net/dema/arraysolicitudes.php?id="
					+ iduser);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void CargarSolicitudes() {

		// listaamigos = (ListView) findViewById(R.id.listView1);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, lista);

		listafollowers.setAdapter(adaptador);
		listafollowers.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

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

}
