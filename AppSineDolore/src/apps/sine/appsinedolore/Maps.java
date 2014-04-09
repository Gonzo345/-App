package apps.sine.appsinedolore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends Activity {

	private GoogleMap mMap;
	private Button bt_map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maps);
		
		
		bt_map= (Button)findViewById(R.id.bt_mapa);
		
		bt_map.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Maps.this, RSineDolore.class);
				startActivity(i);
			}
		});

		// Creamos marcador
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		LatLng menorca = new LatLng(40.26, 3.41);

		CameraPosition campos = new CameraPosition.Builder().target(menorca) // Centramos
																				// el
																				// mapa
																				// en
																				// menorca
				.zoom(5) // zoom de 1-18
				.bearing(0) // Orientacion N
				.tilt(20) // Vista camara a 70 grados
				.build();

		CameraUpdate camupd3 = CameraUpdateFactory.newCameraPosition(campos);
		mMap.animateCamera(camupd3);

		// Marcador de Mahón 1
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(39.881478, 4.252449)).title(
				"Hospital General Mateu Orfila - Dr. Jordi Moya Riera"));

		// Marcador de Mahón 2
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(39.8854506, 4.2575863)).title(
				"Instituto Clínico del Dolor - Dr. Jordi Moya Riera"));

		// Marcador de Mahón 2
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(39.8854506, 4.2575863)).title(
				"Policlínica Virgen de Gracia - Dr. Manuel Corral Rosado"));

		// Marcador de Ciudadela
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(40.005547, 3.841367)).title(
				"Clínica Juaneda Menorca - Dr. Manuel Corral Rosado"));

		// Marcador de Adeje
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(28.470986, -16.266733)).title(
				"USP Hospital La Colina - Dr. L. Javier Santos Yglesias"));

		// Marcador de Madrid 1
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(39.606978, 2.646117)).title(
				"Dr. Alfonso Vidal Marcos"));

		// Marcador de Madrid 2
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(40.409329, -3.8623285)).title(
				"Hospital Madrid Montepríncipe - Dr. José Mª Hernández García"));

		// Marcador de Madrid 3
		mMap.addMarker(new MarkerOptions()
				.position(new LatLng(40.4496858, -3.6520703))
				.title("Hospital Nuestra Señora de América - Dr. José Mª Hernández García"));

		// Marcador de Alicante 1
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(38.358907, -0.357549)).title(
				"Hospital Internacional Medimar - Dr. José M. Ramón Pérez"));

		// Marcador de Alicante 2
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(38.35274, -0.476365)).title(
				"Hospital Perpetuo Socorro - Dr. José M. Ramón Pérez"));

		// Marcador de Barcelona 1
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(41.406374, 2.127423)).title(
				"Clínica del Dolor Teknon - Dr. Luis Aliaga Font"));

		// Marcador de Barcelona 2
		mMap.addMarker(new MarkerOptions()
				.position(new LatLng(41.403976, 2.141958))
				.title("Avantmèdic: Unidad de tratamiento del Dolor - Dr. Jordi Guitart Vela"));

		// Marcador de Bilbao
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(43.268507, -2.946698)).title(
				"Clínica del Dolor Praxis Bilbao - Dra. Mª Luisa Franco Gay"));

		// Marcador de Burgos (1 y 2)
		mMap.addMarker(new MarkerOptions()
				.position(new LatLng(42.344065, -3.683322))
				.title("Hospital Recoletas - Dr. Pedro Olmos Lezaún y Dr. Emilio Jesús García Camarero"));

		// Marcador de Girona
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(41.979602, 2.82067)).title(
				"Clínica Bofill - Dr. Josep Vilaplana"));

		// Marcador de León 1
		mMap.addMarker(new MarkerOptions()
				.position(new LatLng(39.606978, 2.646117))
				.title("Hospital Ntra. Sra. de Regla - Dr. Jorge Luís Rodríguez González"));

		// Marcador de León 2
		mMap.addMarker(new MarkerOptions()
				.position(new LatLng(42.590259, -5.572257))
				.title("Clínica San Francisco de León - Dr. Jorge Luís Rodríguez González"));

		// Marcador de Lleida 1 y 2
		mMap.addMarker(new MarkerOptions()
				.position(new LatLng(42.590259, -5.572257))
				.title("Avantmèdic: Unitat de tractament del Dolor - Dra. Maria Marcè Matute Crespo y Dr. Antonio Montero Matamala"));

		// Marcador de Ourense 1 y 2
		mMap.addMarker(new MarkerOptions()
				.position(new LatLng(42.34155, -7.86118))
				.title("Centro Médico El Carmen - Dr. Marcos Casto Bande y Dra. Luz Cánovas Martínez"));

		// Marcador de Oviedo
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(43.361727, -5.861215)).title(
				"Clínica del Dolor - Dra. Maria Jesús Rodríguez Dintén"));

		// Marcador de Palma de Mallorca 1
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(39.5799161, 2.6481625)).title(
				"Instituto Balear del Dolor - Dr. Marcello G. Meli"));

		// Marcador de Palma de Mallorca 2
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(39.5787139, 2.6525172)).title(
				"Instituto Balear del Dolor - Dr. Ignacio García Praderas"));

		// Marcador de Sabadell 1
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(41.3988142, 2.1693714)).title(
				"Centre Mèdic Verdaguer - Dr. Jose Luis De Córdoba Benedicto"));

		// Marcador de Sabadell 2
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(41.412178, 2.13589)).title(
				"Clínica Sant Honorat - Dr. Jose Luis De Córdoba Benedicto"));

		// Marcador de Sabadell 3
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(41.544633, 2.107551)).title(
				"Dolor Salut - Dr. Jose Luis De Córdoba Benedicto"));

		// Marcador de San Sebastián
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(43.31845, -1.958078)).title(
				"Donostia Dolor - Dr. Fermín Haro Sanz"));

		// Marcador de Santander
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(43.46351, -3.8036015)).title(
				"Clínica del Dolor - Dr. J.M. Carceller Malo"));

		// Marcador de Sevilla 1
		mMap.addMarker(new MarkerOptions()
				.position(new LatLng(37.3635079, -5.9843385))
				.title("Centro de Cirugía Mayor Ambulatoria Ave María - Dr. Miguel Ángel Merino Méndez"));

		// Marcador de Sevilla 2
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(37.369044, -5.987381)).title(
				"Clínica Piñeiro - Dr. Domingo Ventura Vargas"));

		// Marcador de Toledo 1
		mMap.addMarker(new MarkerOptions()
				.position(new LatLng(39.883791, -4.041659))
				.title("IDC Hospital de Las Tres Culturas - Dr. Díaz Jara y Dr. De Andrés"));

		// Marcador de Toledo 2
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(39.868429, -4.0416722)).title(
				"Solimat Mutua - Dr. José Cid Calzada"));

		// Marcador de Toledo 3
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(39.8823249, -4.0305425)).title(
				"Centro Médico Integral - Dr. José Cid Calzada"));

		// Marcador de Valencia
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(39.4683187, -0.3769897)).title(
				"Clínica Santa Clara - Dr. Juan Carlos Tornero Tornero"));

		// Marcador de Valladolid 1
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(41.655963, -4.7291075)).title(
				"Clínica del Dolor Valladolid- Dr. Juan Manuel Vaca Miguel"));

		// Marcador de Valladolid 2
		mMap.addMarker(new MarkerOptions().position(
				new LatLng(41.649656, -4.720165)).title(
				"Sanatorio Sagrado Corazón - Dr. Juan Manuel Vaca Miguel"));

	}
}
