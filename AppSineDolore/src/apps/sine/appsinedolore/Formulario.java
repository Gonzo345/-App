package apps.sine.appsinedolore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Formulario extends Analytics {
	private static ArrayList<String> arrPersonas = new ArrayList<String>();
	private static ArrayAdapter<String> adaptadorVista;

	private Button btEnviar;
	// campos interesantes clinica, procedencia...
	private String nombre, apellido, email;

	private ListView lstListaPersonas;
	private EditText etnombre, etapellidos, etemail;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);

		btEnviar = (Button) findViewById(R.id.btEnviar);
		btEnviar.setEnabled(true);

		etnombre = (EditText) findViewById(R.id.tvNombre);
		etapellidos = (EditText) findViewById(R.id.tvApellido);
		etemail = (EditText) findViewById(R.id.tvEmail);

		etnombre.setText("");
		etapellidos.setText("");
		etemail.setText("");

		btEnviar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				btnEnviar();

			}
		});
	}

	public void btnEnviar() {

		if (etnombre.getText().toString().equals("")
				|| etapellidos.getText().toString().equals("")
				|| etemail.getText().toString().equals("")) {

			Toast.makeText(Formulario.this,
					this.getString(R.string.formtoastError), Toast.LENGTH_LONG)
					.show();
		} else {

			if (ComprobarEmail()) {
				ConexionServidor task = new ConexionServidor();
				task.execute(new String[] { "" });

				Toast.makeText(Formulario.this,
						this.getString(R.string.formtoastOk), Toast.LENGTH_LONG)
						.show();
				finish();
			}else{
				Toast.makeText(Formulario.this,
						this.getString(R.string.formtoastEmail), Toast.LENGTH_LONG)
						.show();
			}
		}
	}

	private boolean ComprobarEmail() {

		
		String cadena= etemail.getText().toString();
		String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	        
	        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
	 
	        Matcher matcher = pattern.matcher(cadena);
	        return matcher.matches();
		
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
				nombre = ((EditText) findViewById(R.id.tvNombre)).getText()
						.toString();
				apellido = ((EditText) findViewById(R.id.tvApellido)).getText()
						.toString();
				email = ((EditText) findViewById(R.id.tvEmail)).getText()
						.toString();

				// Agregar parámetros
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
						2);
				nameValuePairs.add(new BasicNameValuePair("nombre", nombre));
				nameValuePairs
						.add(new BasicNameValuePair("apellido", apellido));
				nameValuePairs.add(new BasicNameValuePair("email", email));
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