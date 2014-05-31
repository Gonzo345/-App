package aplicaciones.aitorgonzo.dondeestanmisamigos;

import java.io.BufferedReader;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class ServicioLocalizacion extends Service {
	private static final long TIEMPO_MIN = 10 * 1000; // 10 segundos
	private static final long DISTANCIA_MIN = 5; // 5 metros

	private static final String[] A = { "n/d", "preciso", "impreciso" };
	private static final String[] P = { "n/d", "bajo", "medio", "alto" };
	private static final String[] E = { "fuera de servicio",
			"temporalmente no disponible ", "disponible" };

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
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				Inicio();
			}

		}, 0, UPDATE_INTERVAL);
		// #############################
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				Comprobacion();
			}

		}, 0, UPDATE_INTERVAL2);

		// #############################
		SharedPreferences prefes = getSharedPreferences("pref_variables",
				MODE_PRIVATE);

		iduser = prefes.getString("id", "");

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
		log("Mejor proveedor: " + proveedor + "\n");
		log("Comenzamos con la ultima localizacion conocida:");
		localizacion = manejador.getLastKnownLocation(proveedor);
		muestraLocaliz(localizacion);
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

			Notificacion();
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

							if (response!="0") {
								Inicio();
							}else{
								
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

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void Notificacion() {

		Context context = getApplicationContext();
		Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("aitor"));
		PendingIntent pendingIntent = PendingIntent.getActivity(
				ServicioLocalizacion.this, 0, myIntent,
				Intent.FLAG_ACTIVITY_NEW_TASK);

		myNotification = new Notification.Builder(context)
				.setContentTitle("Servicio rastreando").setContentText(latit+"  "+longi)
				.setTicker("Coordenadas: " + latit + "," + longi)
				.setWhen(System.currentTimeMillis())
				.setContentIntent(pendingIntent)
//				.setDefaults(Notification.DEFAULT_LIGHTS).setAutoCancel(true)
				.setSmallIcon(R.drawable.ic_launcher).build();

		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(MY_NOTIFICATION_ID, myNotification);

	}
}
