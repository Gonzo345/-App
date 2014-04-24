package apps.sine.appsinedolore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class Formulario extends Analytics {
	private static ArrayList<String> arrPersonas = new ArrayList<String>();
	private static ArrayAdapter<String> adaptadorVista;

	private ListView lstListaPersonas;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
	}

	public void btnEnviar(View v) {
		ConexionServidor task = new ConexionServidor();
		task.execute(new String[] { "" });

	}

	private class ConexionServidor extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {
			String respuesta = "";

			// Inicializar, creando HttpClient y Post Header
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://menorcapp.net/pasarela.php");

			try {
				final String nombre = ((EditText) findViewById(R.id.tvNombre))
						.getText().toString();
				final String apellido = ((EditText) findViewById(R.id.tvApellido))
						.getText().toString();
				final String email = ((EditText) findViewById(R.id.tvEmail))
						.getText().toString();
				final String telefono = ((EditText) findViewById(R.id.tvTelefono))
						.getText().toString();

				// Agregar parámetros
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
						2);
				nameValuePairs.add(new BasicNameValuePair("nombre", nombre));
				nameValuePairs.add(new BasicNameValuePair("apellido", apellido));
				nameValuePairs.add(new BasicNameValuePair("email", email));
				nameValuePairs.add(new BasicNameValuePair("telefono", telefono));
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
	}

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
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