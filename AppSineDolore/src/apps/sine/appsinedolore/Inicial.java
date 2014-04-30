package apps.sine.appsinedolore;

//import com.google.analytics.tracking.android.EasyTracker;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class Inicial extends Analytics {

	private static final String SENDER_ID = "973082549612";

	public static final String EXTRA_MESSAGE = "message";
	public static final String PROPERTY_REG_ID = "registration_id";
	private static final String PROPERTY_APP_VERSION = "appVersion";
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

	private Button btform, btyt, btpage, btpdf, btwine, btgmaps, btabout;
	private String TAG = "GCM";

	TextView mDisplay;
	GoogleCloudMessaging gcm;
	AtomicInteger msgId = new AtomicInteger();
	SharedPreferences prefs;
	Context context;
	String regid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inicial4);

		// Linea que impide el cambio de la orientaci—n del dispositivo
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


		context = getApplicationContext();

		if (checkPlayServices()) {
			gcm = GoogleCloudMessaging.getInstance(this);
			regid = getRegistrationId(context);

			if (regid.isEmpty()) {
				registerInBackground();
			}
		} else {
			Log.i(TAG, "No valid Google Play Services APK found.");
		}

		/*
		 * / C—digo que nos permite registrarnos en el sistema de push try {
		 * 
		 * GCMRegistrar.checkDevice(this); GCMRegistrar.checkManifest(this);
		 * 
		 * final String regId = GCMRegistrar.getRegistrationId(this);
		 * 
		 * Toast.makeText(this, "Dentro de registro Push ", Toast.LENGTH_LONG)
		 * .show();
		 * 
		 * if (regId.equals("")) { GCMRegistrar.register(this, SENDER_ID); }
		 * else { Log.v(TAG, "Ya estoy registrado"); }
		 * 
		 * } catch (UnsupportedOperationException e) { Log.e(TAG,
		 * "El dispositivo no soporta GCM.", e); } catch (IllegalStateException
		 * e) { Log.e(TAG, "El manifest no est‡ bien configurado.", e); } //
		 * #############################################
		 */

		// la siguiente linea nos permite quitar el servicio de notificaciones
		// push
		// GCMRegistrar.unregister(this);

		// declaraci—n de los botones
		btform = (Button) findViewById(R.id.btform);
		btwine = (Button) findViewById(R.id.btwine);
		btpage = (Button) findViewById(R.id.btpage);
		btpdf = (Button) findViewById(R.id.btpdf);
		btyt = (Button) findViewById(R.id.btyt);
		btgmaps = (Button) findViewById(R.id.btgmaps);
		btabout = (Button) findViewById(R.id.btabout);

		// Formulario
		btform.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, Formulario.class);
				startActivity(i);
			}
		});
		// YouTube
		btyt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, Youtube.class);
				startActivity(i);
			}
		});
		// Wine
		btwine.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, Wine.class);
				startActivity(i);
			}
		});
		// WebPage
		btpage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, Page.class);
				startActivity(i);
			}
		});
		// PDF
		btpdf.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, PDF.class);
				startActivity(i);
			}
		});
		// Google Maps
		btgmaps.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, Maps.class);
				startActivity(i);
			}
		});

		btabout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, About.class);
				startActivity(i);
			}

		});
		// #################### FIN DE BOTONES ########################

	}

	private void registerInBackground() {
		new AsyncTask<Object, Object, Object>() {
			protected String doInBackground(Void... params) {
				String msg = "";
				try {
					if (gcm == null) {
						gcm = GoogleCloudMessaging.getInstance(context);
					}
					regid = gcm.register(SENDER_ID);
					msg = "Device registered, registration ID=" + regid;

					sendRegistrationIdToBackend();

					storeRegistrationId(context, regid);
				} catch (IOException ex) {
					msg = "Error :" + ex.getMessage();
					// If there is an error, don't just keep trying to register.
					// Require the user to click a button again, or perform
					// exponential back-off.
				}
				return msg;
			}

			protected void onPostExecute(String msg) {
				mDisplay.append(msg + "\n");
			}

			@Override
			protected Object doInBackground(Object... params) {
				// TODO Auto-generated method stub
				return null;
			}
		}.execute(null, null, null);
	}

	private boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(this);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				GooglePlayServicesUtil.getErrorDialog(resultCode, this,
						PLAY_SERVICES_RESOLUTION_REQUEST).show();
			} else {
				Log.i(TAG, "This device is not supported.");
				finish();
			}
			return false;
		}
		return true;
	}

	private String getRegistrationId(Context context) {
		final SharedPreferences prefs = getGCMPreferences(context);
		String registrationId = prefs.getString(PROPERTY_REG_ID, "");
		if (registrationId.isEmpty()) {
			Log.i(TAG, "Registration not found.");
			return "";
		}
		// Check if app was updated; if so, it must clear the registration ID
		// since the existing regID is not guaranteed to work with the new
		// app version.
		int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION,
				Integer.MIN_VALUE);
		int currentVersion = getAppVersion(context);
		if (registeredVersion != currentVersion) {
			Log.i(TAG, "App version changed.");
			return "";
		}
		return registrationId;
	}

	/**
	 * @return Application's {@code SharedPreferences}.
	 */
	private SharedPreferences getGCMPreferences(Context context) {
		// This sample app persists the registration ID in shared preferences,
		// but
		// how you store the regID in your app is up to you.
		return getSharedPreferences(Inicial.class.getSimpleName(),
				Context.MODE_PRIVATE);
	}

	private static int getAppVersion(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			// should never happen
			throw new RuntimeException("No se ha podido coger el package: " + e);
		}
	}

	private void sendRegistrationIdToBackend() {
		// Your implementation here.
		Log.e("entram", "dins de sendRegistrationid...");
	}

	private void storeRegistrationId(Context context, String regId) {
		final SharedPreferences prefs = getGCMPreferences(context);
		int appVersion = getAppVersion(context);
		Log.i(TAG, "Saving regId on app version " + appVersion);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(PROPERTY_REG_ID, regId);
		editor.putInt(PROPERTY_APP_VERSION, appVersion);
		editor.commit();
	}

}
