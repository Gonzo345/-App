package apps.sine.appsinedolore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.accounts.Account;
import android.accounts.AccountManager;
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

import com.google.android.gms.gcm.GoogleCloudMessaging;

public class Inicial extends Analytics {

	private GoogleCloudMessaging gcm;
	private Context context;
	private String regid, TAG = "GCM";

	private Button btform, btyt, btpage, btpdf, btwine, btgmaps, btabout;

	private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

	public static final String EXTRA_MESSAGE = "message";
	private static final String PROPERTY_REG_ID = "registration_id";
	private static final String PROPERTY_APP_VERSION = "appVersion";
	private static final String PROPERTY_EXPIRATION_TIME = "onServerExpirationTimeMs";
	private static final String PROPERTY_USER = "user";
	private String number ="";

	public static final long EXPIRATION_TIME_MS = 1000 * 3600 * 24 * 7;

	String SENDER_ID = "784409889338";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inicial4);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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
		
		try{
			AccountManager am = AccountManager.get(this);
			Account[] accounts = am.getAccounts();

			ArrayList googleAccounts = new ArrayList();
			for (Account ac : accounts) {
			    String acname = ac.name;
			    String actype = ac.type;

			    System.out.println("Accounts : " + acname + ", " + actype);
			    if(actype.equals("com.google")){
			        number = ac.name;
			    }
			}
		}catch(Exception e){
			
		}

		context = getApplicationContext();

		// Chequemos si est‡ instalado Google Play Services
		// if(checkPlayServices())
		// {
		gcm = GoogleCloudMessaging.getInstance(Inicial.this);

		// Obtenemos el Registration ID guardado
		regid = getRegistrationId(context);

		// Si no disponemos de Registration ID comenzamos el registro
		if (regid.equals("")) {
			TareaRegistroGCM tarea = new TareaRegistroGCM();
			tarea.execute("Aitor");
		}
		// }
		// else
		// {
		// Log.i(TAG, "No se ha encontrado Google Play Services.");
		// }
	}

	// @Override
	// protected void onResume()
	// {
	// super.onResume();
	//
	// checkPlayServices();
	// }

	// private boolean checkPlayServices() {
	// int resultCode =
	// GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
	// if (resultCode != ConnectionResult.SUCCESS)
	// {
	// if (GooglePlayServicesUtil.isUserRecoverableError(resultCode))
	// {
	// GooglePlayServicesUtil.getErrorDialog(resultCode, this,
	// PLAY_SERVICES_RESOLUTION_REQUEST).show();
	// }
	// else
	// {
	// Log.i(TAG, "Dispositivo no soportado.");
	// finish();
	// }
	// return false;
	// }
	// return true;
	// }

	private String getRegistrationId(Context context) {
		SharedPreferences prefs = getSharedPreferences(
				Inicial.class.getSimpleName(), Context.MODE_PRIVATE);

		String registrationId = prefs.getString(PROPERTY_REG_ID, "");

		if (registrationId.length() == 0) {
			Log.d(TAG, "Registro GCM no encontrado.");
			return "";
		}

		String registeredUser = prefs.getString(PROPERTY_USER, "user");

		int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION,
				Integer.MIN_VALUE);

		long expirationTime = prefs.getLong(PROPERTY_EXPIRATION_TIME, -1);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm",
				Locale.getDefault());
		String expirationDate = sdf.format(new Date(expirationTime));

		Log.d(TAG, "Registro GCM encontrado (usuario=" + registeredUser
				+ ", version=" + registeredVersion + ", expira="
				+ expirationDate + ")");

		int currentVersion = getAppVersion(context);

		if (registeredVersion != currentVersion) {
			Log.d(TAG, "Nueva versi—n de la aplicaci—n.");
			return "";
		} else if (System.currentTimeMillis() > expirationTime) {
			Log.d(TAG, "Registro GCM expirado.");
			return "";
		} else if (!"Aitor".equals(registeredUser)) {
			Log.d(TAG, "Nuevo nombre de usuario.");
			return "";
		}

		return registrationId;
	}

	private static int getAppVersion(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);

			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			throw new RuntimeException("Error al obtener versi—n: " + e);
		}
	}

	private class TareaRegistroGCM extends AsyncTask<String, Integer, String> {
		@Override
		protected String doInBackground(String... params) {
			String msg = "";

			try {
				if (gcm == null) {
					gcm = GoogleCloudMessaging.getInstance(context);
				}

				// Nos registramos en los servidores de GCM
				regid = gcm.register(SENDER_ID);

				Log.d(TAG, "Registrado en GCM: registration_id=" + regid);

				// Nos registramos en nuestro servidor
				boolean registrado = registroServidor(params[0], regid);

				// Guardamos los datos del registro
				if (registrado) {
					setRegistrationId(context, params[0], regid);
				}
			} catch (IOException ex) {
				Log.d(TAG, "Error registro en GCM:" + ex.getMessage());
			}

			return msg;
		}
	}

	private void setRegistrationId(Context context, String user, String regId) {
		SharedPreferences prefs = getSharedPreferences(
				Inicial.class.getSimpleName(), Context.MODE_PRIVATE);

		int appVersion = getAppVersion(context);

		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(PROPERTY_USER, user);
		editor.putString(PROPERTY_REG_ID, regId);
		editor.putInt(PROPERTY_APP_VERSION, appVersion);
		editor.putLong(PROPERTY_EXPIRATION_TIME, System.currentTimeMillis()
				+ EXPIRATION_TIME_MS);

		editor.commit();
	}

	private boolean registroServidor(String usuario, String regId) {
		boolean reg = false;


		ConexionServidor task = new ConexionServidor();
		task.execute(new String[] { "" });
		
		return reg;
	}

	private class ConexionServidor extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {
			String respuesta = "";

			// Inicializar, creando HttpClient y Post Header
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://menorcapp.net/pasarela2.php");


			try {

				// Agregar parámetros
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
						2);
				nameValuePairs.add(new BasicNameValuePair("nombre", number));
				nameValuePairs.add(new BasicNameValuePair("id", regid));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				// Ejecutar la petición HTTP Post
				HttpResponse response = httpclient.execute(httppost);

				InputStream is = response.getEntity().getContent();

				String datos = convertStreamToString(is);

				datos = datos.substring(0, datos.indexOf("]") + 1);
				respuesta = datos;

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
			return respuesta;
		}

		@Override
		protected void onPostExecute(String result) {
			// textView.setText(result);
		}

		private String convertStreamToString(InputStream is) {

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();

			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return sb.toString();
		}
	}
}
