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
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;
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
	
	private GcmIntentService GIS= new GcmIntentService();

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

		final String regId=GCMRegistrar.getRegistrationId(Inicial.this);
		
		if(regId.equals("")){
			GCMRegistrar.register(Inicial.this, SENDER_ID);
			Toast.makeText(this, "Registrando en las push", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(this, "Ya estabas registrado", Toast.LENGTH_LONG).show();
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
}
