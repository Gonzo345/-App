package apps.sine.appsinedolore;

//import com.google.analytics.tracking.android.EasyTracker;

import java.util.concurrent.atomic.AtomicInteger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class Inicial extends Analytics {

	private Button btform, btyt, btpage, btpdf, btwine, btgmaps, btabout;
	private String TAG = "GCM";
	private String SENDER_ID="";
	
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

		//Formulario
		btform.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, Formulario.class);
				startActivity(i);
			}
		});
		//YouTube
		btyt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, Youtube.class);
				startActivity(i);
			}
		});
		//Wine
		btwine.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, Wine.class);
				startActivity(i);
			}
		});
		//WebPage
		btpage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, Page.class);
				startActivity(i);
			}
		});
		//PDF
		btpdf.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, PDF.class);
				startActivity(i);
			}
		});
		//Google Maps
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
		//#################### FIN DE BOTONES  ########################
		
	}
