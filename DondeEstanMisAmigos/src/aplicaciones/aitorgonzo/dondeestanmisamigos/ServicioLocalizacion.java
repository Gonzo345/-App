package aplicaciones.aitorgonzo.dondeestanmisamigos;

import java.io.BufferedReader;
import java.util.List;
import java.util.Timer;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class ServicioLocalizacion extends Service {

	private static final String[] A = { "n/d", "preciso", "impreciso" };
	private static final String[] P = { "n/d", "bajo", "medio", "alto" };
	private static final String[] E = { "fuera de servicio",
			"temporalmente no disponible ", "disponible" };

	private MyLocationListener mylistener;

	private static final int MY_NOTIFICATION_ID = 1;
	NotificationManager notificationManager;
	Notification myNotification;

	Location localizacion;

	static final int UPDATE_INTERVAL = 180000;
	static final int UPDATE_INTERVAL2 = 10000;
	// static final int UPDATE_INTERVAL = 18000000;
	private Timer timer = new Timer();

	private LocationManager manejador;
	private String proveedor, salida, latit, longi, iduser;
	private GoogleMap mMap;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	public void toast(String cadena) {
		// toast.makeText(this, cadena, toast.LENGTH_LONG).show();
	}

	public void onDestroy() {
		// toast("destruido");
	}

	// ################################
	public void onCreate() {
		// Inicio();

	}

	public void Inicio() {
		// TODO Auto-generated method stub
		// toast("servicio creado");

		manejador = (LocationManager) getSystemService(LOCATION_SERVICE);
		log("Proveedores de localizacion: \n ");
		muestraProveedores();

		Criteria criterio = new Criteria();
		criterio.setCostAllowed(false);
		criterio.setAltitudeRequired(false);
		criterio.setAccuracy(Criteria.ACCURACY_FINE);
		proveedor = manejador.getBestProvider(criterio, true);

		if (proveedor == null) {
			toast("Actualmente no tienes ningún proveedor de localización");
		} else {

			log("Mejor proveedor: " + proveedor + "\n");
			log("Comenzamos con la ultima localizacion conocida:");

			mylistener = new MyLocationListener();

			if (localizacion != null) {
				mylistener.onLocationChanged(localizacion);
			} else {
				Intent intent = new Intent(
						Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				startActivity(intent);
			}

			// localizacion = manejador.getLastKnownLocation(proveedor);
			manejador.requestLocationUpdates(proveedor, 200, 1, mylistener);
			muestraLocaliz(localizacion);
		}
	}

	// MŽtodos de la interfaz LocationListener
	public void onLocationChanged(Location location) {
		log("Nueva localizaci—n: ");
		muestraLocaliz(location);
		// //toast.makeText(this, location.toString(),
		// //toast.LENGTH_LONG).show();
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

	}

	private void muestraLocaliz(Location localizacion) {
		if (localizacion == null)
			log("Localizaci—n desconocida\n");
		else {
			log(localizacion.toString() + "\n");

			latit = localizacion.getLatitude() + "";
			longi = localizacion.getLongitude() + "";

//			Notificacion();
			InsertarCoordenadas(latit, longi);

		}

	}

	private void InsertarCoordenadas(String latit2, String longi2) {
		BufferedReader in = null;

		try {

			AsyncHttpClient client = new AsyncHttpClient();
			client.get(
					"http://www.menorcapp.net/dema/insertarcoordenadas.php?id="
							+ iduser + "&latitud=" + latit2 + "&longitud="
							+ longi2, new AsyncHttpResponseHandler() {
						@Override
						public void onSuccess(String response) {
							System.out.println(response);

						}

					});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
			// text.append(" ERROR ");
		}

	}

	private void Comprobacion() {
		try {

			AsyncHttpClient client = new AsyncHttpClient();
			client.get(
					"http://www.menorcapp.net/dema/comprobacionpeticiones.php?id="
							+ iduser, new AsyncHttpResponseHandler() {
						@Override
						public void onSuccess(String response) {
							System.out.println(response);

							if (response != "0") {
								Inicio();
							} else {

							}

						}

					});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
			// text.append(" ERROR ");
		}
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

//	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//	public void Notificacion() {
//
//		Context context = getApplicationContext();
//		Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("aitor"));
//		PendingIntent pendingIntent = PendingIntent.getActivity(
//				ServicioLocalizacion.this, 0, myIntent,
//				Intent.FLAG_ACTIVITY_NEW_TASK);
//
//		myNotification = new Notification.Builder(context)
//				.setContentTitle("Servicio rastreando")
//				.setContentText(latit + "  " + longi)
//				.setTicker("Coordenadas: " + latit + "," + longi)
//				.setWhen(System.currentTimeMillis())
//				.setContentIntent(pendingIntent)
//				// .setDefaults(Notification.DEFAULT_LIGHTS).setAutoCancel(true)
//				.setSmallIcon(R.drawable.ic_launcher).build();
//
//		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//		notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
//
//	}

	private class MyLocationListener implements LocationListener {
		private String loquehace;

		@Override
		public void onLocationChanged(Location location) {
			// Initialize the location fields
			loquehace = "Latitude: " + String.valueOf(location.getLatitude());
			loquehace = loquehace + "Longitude: "
					+ String.valueOf(location.getLongitude());
			loquehace = loquehace + proveedor + " provider has been selected.";

			Toast.makeText(ServicioLocalizacion.this, "Location changed!",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			Toast.makeText(ServicioLocalizacion.this,
					provider + "'s status changed to " + status + "!",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onProviderEnabled(String provider) {
			Toast.makeText(ServicioLocalizacion.this,
					"Provider " + provider + " enabled!", Toast.LENGTH_SHORT)
					.show();

		}

		@Override
		public void onProviderDisabled(String provider) {
			Toast.makeText(ServicioLocalizacion.this,
					"Provider " + provider + " disabled!", Toast.LENGTH_SHORT)
					.show();
		}
	}
}