package aplicaciones.aitorgonzo.dondeestanmisamigos;

//import static android.support.v4.app.FragmentActivity.TAG;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gcm.GCMRegistrar;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Inicial extends ActionBarActivity {

	private GoogleMap Gmap;
	private String TAG="Capturado";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inicial);

		// Menu lateral navigation drawer
		ListView drawer = (ListView) findViewById(R.id.drawer);
		DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		String[] opciones = { "Opci—n 1", "Opci—n 2", "Opci—n 3" };
		drawer.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				opciones));

		drawer.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Inicial.this, Localizacion.class);
				startActivity(i);

			}
		});
		
		try{
			 
			   GCMRegistrar.checkDevice(this);
			   GCMRegistrar.checkManifest(this);
			 
			   final String regId = GCMRegistrar.getRegistrationId(this);
			 
			   if (regId.equals("")) {
			      GCMRegistrar.register(this, "AIzaSyDSq-nSBn_VpwjtTS8yXsi3zV3mRB9JGcE");
			   } else {
			      Log.v(TAG, "Ya estoy registrado");
			   }
			 
			} catch (UnsupportedOperationException e) {
			   Log.e(TAG, "El dispositivo no soporta GCM.", e);
			} catch (IllegalStateException e) {
			   Log.e(TAG, "El manifest no est‡ bien configurado.", e);
			}

		// *************** Final navigation drawer
		



	}

	public void CrearMarcador(double latit, double longi, String cadena) {

		Gmap.addMarker(new MarkerOptions().position(new LatLng(latit, longi))
				.title(cadena));
	}

}