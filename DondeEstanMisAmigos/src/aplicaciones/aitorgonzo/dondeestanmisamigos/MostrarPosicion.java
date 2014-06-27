package aplicaciones.aitorgonzo.dondeestanmisamigos;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MostrarPosicion extends Activity implements LocationListener {

	private String id = "", amigo = "", longitud_amigo = "",
			latitud_amigo = "";
	private static final long TIEMPO_MIN = 10 * 1000; // 10 segundos
	private static final long DISTANCIA_MIN = 5; // 5 metros
	private static final String[] A = { "n/d", "preciso", "impreciso" };
	private static final String[] P = { "n/d", "bajo", "medio", "alto" };
	private static final String[] E = { "fuera de servicio",
			"temporalmente no disponible ", "disponible" };
	private LocationManager manejador;
	private String proveedor, salida;
	private GoogleMap mMap;
	private AlertDialog alert = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mostrarposicion);

		// _______ Recuperamos el putextra_____________
		if (savedInstanceState == null) {
			savedInstanceState = getIntent().getExtras();
			if (savedInstanceState == null) {
				id = null;
				amigo = null;
			} else {
				id = savedInstanceState.getString("id");
				amigo = savedInstanceState.getString("amigo");
			}
		} else {
			id = (String) savedInstanceState.getSerializable("id");
			amigo = (String) savedInstanceState.getSerializable("amigo");
		}
		
		final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		//pregunta para abrir el GPS
		if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			AlertNoGps();
		}

		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		manejador = (LocationManager) getSystemService(LOCATION_SERVICE);
		log("Proveedores de localizacion: \n ");
		muestraProveedores();

		Criteria criterio = new Criteria();
		criterio.setCostAllowed(false);
		criterio.setAltitudeRequired(false);
		criterio.setAccuracy(Criteria.ACCURACY_FINE);
		proveedor = manejador.getBestProvider(criterio, true);
		log("Mejor proveedor: " + proveedor + "\n");
		log("Comenzamos con la ultima localizacion conocida:");
		Location localizacion = manejador.getLastKnownLocation(proveedor);
		muestraLocaliz(localizacion);

		localizaramigo();

	}

	// MŽtodos del ciclo de vida de la actividad
	@Override
	protected void onResume() {
		super.onResume();
		// Activamos notificaciones de localizaci—n
		manejador.requestLocationUpdates(proveedor, TIEMPO_MIN, DISTANCIA_MIN,
				this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		manejador.removeUpdates(this);
	}

	// MŽtodos de la interfaz LocationListener
	public void onLocationChanged(Location location) {
		log("Nueva localizaci—n: ");
		muestraLocaliz(location);
		// Toast.makeText(this, location.toString(), Toast.LENGTH_LONG).show();
	}

	public void onProviderDisabled(String proveedor) {
		log("Proveedor deshabilitado: " + proveedor + "\n");
	}

	public void onProviderEnabled(String proveedor) {
		log("Proveedor habilitado: " + proveedor + "\n");
	}

	public void onStatusChanged(String proveedor, int estado, Bundle extras) {
		log("Cambia estado proveedor: " + proveedor + ", estado="
				+ E[Math.max(0, estado)] + ", extras=" + extras + "\n");
	}

	// MŽtodos para mostrar informaci—n
	private void log(String cadena) {
		salida = salida + cadena;
		// Toast.makeText(MostrarPosicion.this, salida,
		// Toast.LENGTH_LONG).show();

	}

	private void localizaramigo() {
		try {

			AsyncHttpClient client = new AsyncHttpClient();
			client.get("http://www.menorcapp.net/dema/userposconcreto.php?id="
					+ amigo, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(String response) {
					System.out.println(response);

					try {
						latitud_amigo = response.substring(0,
								response.indexOf(";"));
						longitud_amigo = response.substring(
								response.indexOf(";") + 1,
								response.length() - 1);

						// mMap.addMarker(new MarkerOptions().position(
						// new LatLng(Double.parseDouble(latitud_amigo),
						// Double.parseDouble(longitud_amigo)))
						// .title("Este es el amigo: " + amigo));
						AnadirMarcador(Double.parseDouble(latitud_amigo),
								Double.parseDouble(longitud_amigo), amigo);

					} catch (Exception e) {

					}
				}

			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}
	}

	public void AnadirMarcador(double latitud, double longitud, String amigo) {

		try{
			mMap.addMarker(new MarkerOptions().position(
				new LatLng(latitud, longitud)).title(amigo).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
		}catch(Exception e){
			Log.e("","No se ha podido añadir el marcador "+e);
		}

	}

	private void GuardarPosicion(double lati, double longi) {
		try {

			AsyncHttpClient client = new AsyncHttpClient();
			client.get(
					"http://www.menorcapp.net/dema/insertarcoordenadas.php?id="
							+ id + "&latitud=" + lati + "&longitud=" + longi,
					new AsyncHttpResponseHandler() {
						@Override
						public void onSuccess(String response) {
							System.out.println(response);

							try {
								
							} catch (Exception e) {

							}
						}
					});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}
	}

	private void muestraLocaliz(Location localizacion) {
		if (localizacion == null)
			log("Localizaci—n desconocida\n");
		else {


			log(localizacion.toString() + "\n");
			Toast.makeText(
					this,
					proveedor + " " + localizacion.getLatitude() + " "
							+ localizacion.getLongitude(), Toast.LENGTH_LONG)
					.show();
			Toast.makeText(this, proveedor, Toast.LENGTH_LONG).show();

			localizacion.getLatitude();
			localizacion.getLongitude();

			GuardarPosicion(localizacion.getLatitude(),
					localizacion.getLongitude());

			try {
				mMap.addMarker(new MarkerOptions().position(
						new LatLng(localizacion.getLatitude(), localizacion
								.getLongitude())).title(id).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
				
				 LatLng latlng = new LatLng(localizacion.getLatitude(), localizacion.getLongitude());
				 CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latlng, 15);
				    mMap.animateCamera(cameraUpdate);
			} catch (Exception e) {

			}
		}

	}

	private void AlertNoGps() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("El sistema GPS esta desactivado, ¿Desea activarlo?")
				.setCancelable(false)
				.setPositiveButton("Si", new DialogInterface.OnClickListener() {
					public void onClick(
							@SuppressWarnings("unused") final DialogInterface dialog,
							@SuppressWarnings("unused") final int id) {
						startActivity(new Intent(
								android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
					}
				})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog,
							@SuppressWarnings("unused") final int id) {
						dialog.cancel();
					}
				});
		alert = builder.create();
		alert.show();
	}

	private void muestraProveedores() {
		log("Proveedor de localizaci—n: \n");
		List<String> proveedores = manejador.getAllProviders();
		for (String proveedor : proveedores) {
			muestraProveedor(proveedor);
		}
	}

	private void muestraProveedor(String proveedor) {
		LocationProvider info = manejador.getProvider(proveedor);
		log("LocationProvider[ " + "getName=" + info.getName()
				+ ", isProviderEnabled="
				+ manejador.isProviderEnabled(proveedor) + ", getAccuracy="
				+ A[Math.max(0, info.getAccuracy())] + ", getPowerRequirement="
				+ P[Math.max(0, info.getPowerRequirement())]
				+ ", hasMonetaryCost=" + info.hasMonetaryCost()
				+ ", requiresCell=" + info.requiresCell()
				+ ", requiresNetwork=" + info.requiresNetwork()
				+ ", requiresSatellite=" + info.requiresSatellite()
				+ ", supportsAltitude=" + info.supportsAltitude()
				+ ", supportsBearing=" + info.supportsBearing()
				+ ", supportsSpeed=" + info.supportsSpeed() + " ]\n");
	}
}
