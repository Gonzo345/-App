package apps.sine.appsinedolore;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Maps extends Activity {
	
	private GoogleMap mMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maps);
		
		//Creamos marcador
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		//Marcador de Mah�n 1
		mMap.addMarker(new MarkerOptions()
	    .position(new LatLng(39.881478, 4.252449))
	    .title("Hospital General Mateu Orfila"));
		
		//Marcador de Mah�n 2
		mMap.addMarker(new MarkerOptions()
        .position(new LatLng(39.8854506, 4.2575863))
        .title("Instituto Cl�nico del Dolor - Dr. Jordi Moya Riera"));
		
		//Marcador de Mah�n 2
		mMap.addMarker(new MarkerOptions()
        .position(new LatLng(39.8854506, 4.2575863))
        .title("Policl�nica Virgen de Gracia - Dr. Manuel Corral Rosado"));
		
		//Marcador de Ciudadela
		mMap.addMarker(new MarkerOptions()
        .position(new LatLng(40.005547, 3.841367))
        .title("Cl�nica Juaneda Menorca - Dr. Manuel Corral Rosado"));
		
		//Marcador de Girona
		mMap.addMarker(new MarkerOptions()
        .position(new LatLng(41.979602, 2.82067))
        .title("Cl�nica Bofill - Dr. Josep Vilaplana"));
		
		//Marcador de Son Espases
		mMap.addMarker(new MarkerOptions()
        .position(new LatLng(39.606978, 2.646117))
        .title("Hospital Universitario Son Espases"));
		
		//Marcador de Le�n
		mMap.addMarker(new MarkerOptions()
        .position(new LatLng(39.606978, 2.646117))
        .title("Hospital Ntra. Sra. de Regla - Dr. Jorge Lu�s Rodr�guez Gonz�lez"));
		
		//Marcador de Madrid
		mMap.addMarker(new MarkerOptions()
        .position(new LatLng(39.606978, 2.646117))
        .title("Dr. Alfonso Vidal Marcos"));
		
		//Marcador de Alicante
		mMap.addMarker(new MarkerOptions()
        .position(new LatLng(38.358907, -0.357549))
        .title("Hospital Internacional Medimar"));
		
		//Marcador de Barcelona 1
		mMap.addMarker(new MarkerOptions()
        .position(new LatLng(41.406374, 2.127423))
        .title("Cl�nica del Dolor Teknon"));
		
		//Marcador de Barcelona 2
		mMap.addMarker(new MarkerOptions()
        .position(new LatLng(41.403976, 2.141958))
        .title("Avantm�dic - Unidad de tratamiento del Dolor"));
		
		//Marcador de Bilbao
		mMap.addMarker(new MarkerOptions()
        .position(new LatLng(43.268507, -2.946698))
        .title("Cl�nica del Dolor Praxis Bilbao"));
		
		//Marcador de Burgos (1 y 2)
		mMap.addMarker(new MarkerOptions()
        .position(new LatLng(42.344065, -3.683322))
        .title("Hospital Recoletas"));
	}
}
