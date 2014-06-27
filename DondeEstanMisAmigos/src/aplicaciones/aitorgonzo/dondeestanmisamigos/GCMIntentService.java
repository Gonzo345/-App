package aplicaciones.aitorgonzo.dondeestanmisamigos;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gcm.GCMBaseIntentService;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class GCMIntentService extends GCMBaseIntentService {
	private UtilidadesGCM UGCM = new UtilidadesGCM();
	private static Context context;

	Location localizacion;
	private LocationManager manejador;
	private String proveedor, salida, latit, longi, iduser, emisor;
	private MyLocationListener mylistener;
	private int bandera = 0;

	@Override
	protected void onError(Context context, String msgError) {
		UGCM.mostrarMensaje(context, "Error:" + msgError);
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String mensaje = intent.getStringExtra("mensaje");
		emisor = intent.getStringExtra("emisor");

		Log.e("",mensaje);
		if (emisor.contains("Coordenadas")) {
			Notificacion(context, mensaje, emisor);
			MostrarPosicion MP = new MostrarPosicion();

			
			String latitud = emisor.substring(emisor.indexOf(":")+1,
					emisor.indexOf(";"));
			Log.e("", "Antes d´entrar a la longitut");
			String longitud = emisor.substring(emisor.indexOf(";")+1,
					emisor.length());

			MP.AnadirMarcador(Double.parseDouble(latitud),
					Double.parseDouble(longitud), emisor);

			Log.e("", "Dins de la notificacio de las coordenades");
		} else {
			Notificacion(context, mensaje, emisor);
			Log.e("", "Dins de la notificacio de la notificacio");

		}

		getLocation();

		try {

			mylistener = new MyLocationListener();

			if (localizacion != null) {
				mylistener.onLocationChanged(localizacion);
			} else {
				Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				startActivity(i);
			}

			// localizacion = manejador.getLastKnownLocation(proveedor);
			if (bandera <= 10) {
				manejador.requestLocationUpdates(proveedor, 200, 1, mylistener);
				bandera++;
			} else {
				manejador.removeUpdates(mylistener);
				bandera = 0;
			}

			Log.e("Dins del try despres de enviar", "Dins del try enviat");
		} catch (Exception e) {
			Log.e("Dins del catch despres de enviar", "Dins del catch enviat");
		}
	}

	private void toast(String string) {
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onRegistered(Context arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void Notificacion(Context context2, String mensaje, String emisor) {

		Context context = getApplicationContext();
		Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("aitor"));
		PendingIntent pendingIntent = PendingIntent.getActivity(
				GCMIntentService.this, 0, myIntent,
				Intent.FLAG_ACTIVITY_NEW_TASK);

		Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
		Notification myNotification = new Notification.Builder(context)
				.setContentTitle(mensaje)
				.setContentText("Hola, " + emisor + " te esta buscando")
				.setTicker(emisor).setWhen(System.currentTimeMillis())
				.setContentIntent(pendingIntent)
				.setDefaults(Notification.DEFAULT_SOUND).setAutoCancel(true)
				.setDefaults(Notification.DEFAULT_LIGHTS).setAutoCancel(true)
				.setDefaults(Notification.DEFAULT_VIBRATE).setAutoCancel(true)
				.setSmallIcon(R.drawable.ic_launcher).build();

		Context context1 = getApplicationContext();
		Intent notificationIntent = new Intent(this, Login.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);

		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(0, myNotification);

	}

	public void getLocation() {
		try {
			manejador = (LocationManager) getSystemService(LOCATION_SERVICE);

			Criteria criterio = new Criteria();
			criterio.setCostAllowed(false);
			criterio.setAltitudeRequired(false);
			criterio.setAccuracy(Criteria.ACCURACY_FINE);
			proveedor = manejador.getBestProvider(criterio, true);
			localizacion = manejador.getLastKnownLocation(proveedor);
			Toast.makeText(
					this,
					"getLastLocation()=  " + localizacion.getLatitude() + "   "
							+ localizacion.getLongitude(), Toast.LENGTH_LONG)
					.show();
			localizacion.reset();

			Toast.makeText(
					this,
					"reset()=  " + localizacion.getLatitude() + "   "
							+ localizacion.getLongitude(), Toast.LENGTH_LONG)
					.show();

		} catch (Exception e) {
			Log.e("falla", e + "");
			Toast.makeText(this, "catch", Toast.LENGTH_LONG).show();
		}
	}

	public void onLocationChanched(Location loc) {
		String localizacion = loc.getLongitude() + "," + loc.getLatitude();

		Toast.makeText(this, localizacion, Toast.LENGTH_LONG).show();
		Log.i("servicio", "estamos en otro lugar");

	}

	private void DevolverCoordenadas(String coordenadas) {
		try {

			AsyncHttpClient client = new AsyncHttpClient();
			client.get("http://www.menorcapp.net/dema/GCM.php?emisor="
					+ "Coordenadas:" + coordenadas + "&receptor=" + emisor,
					new AsyncHttpResponseHandler() {
						@Override
						public void onSuccess(String response) {
							System.out.println(response);
							// text.setText(response);

							// toast(response);

							try {

								int ini = response.indexOf("0");
								response = response.substring(ini, ini + 1);
								Log.e("", "Se ha podido crear enviar la PUSH");

							} catch (Exception e) {
								Log.e("",
										"No se ha podido crear enviar la PUSH");

							}

							if (response.equals("0")) {
								// toast("0");
							} else {
								Log.e("",
										"No se ha podido crear enviar la PUSH");
							}
						}
					});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}
	}

	private class MyLocationListener implements LocationListener {
		private String loquehace;

		@Override
		public void onLocationChanged(Location location) {
			// Devolvemos las coordenadas nuevas
			loquehace = "Latitude: " + String.valueOf(location.getLatitude());
			loquehace = loquehace + "Longitude: "
					+ String.valueOf(location.getLongitude());
			loquehace = loquehace + proveedor + " provider has been selected.";

			Log.e("Ha canviat la localitzacio", "canviada");
			DevolverCoordenadas(location.getLatitude() + ";"
					+ location.getLongitude());

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			Log.e("proveidor canviat", "proveidor canviat");

		}

		@Override
		public void onProviderEnabled(String provider) {
			Log.e("", "ences " + provider);

		}

		@Override
		public void onProviderDisabled(String provider) {
			Log.e("desactivat", "desactivat " + provider);

		}
	}

}