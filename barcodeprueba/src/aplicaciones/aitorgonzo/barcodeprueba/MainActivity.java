package aplicaciones.aitorgonzo.barcodeprueba;

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

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.barcodeprueba.R;

public class MainActivity extends Activity {

	private InsertBdOnline IBO = new InsertBdOnline();
	private String[] arg;
	private String URL = "http://www.menorcapp.net/pasarelaInsertScann.php",
			str_id, str_nombre="no especificado", str_precio="no especificado", str_descripcion="no especificado", str_supermercado="no especificado",
			str_marca="no especificado";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button1 = (Button) findViewById(R.id.button1);

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ejecutascan();
			}
		});

	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				str_id = intent.getStringExtra("resultat");

				Toast.makeText(this, str_id, Toast.LENGTH_SHORT).show();
				
				EnviarDatosInsert(URL);
				
				
				

			} else if (resultCode == RESULT_CANCELED) {
				// Si se cancelo la captura.
			}
		}
	}

	public void ejecutascan() {
		Intent intent = new Intent("com.example.barcodeprueba.SCAN");
		startActivityForResult(intent, 0);

	}

	public void EnviarDatosInsert(String URL) {

		ConexionServidor task = new ConexionServidor();
		task.execute(new String[] { "" });

	}

	private class ConexionServidor extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {

			String respuesta = "";

			// Inicializar, creando HttpClient y Post Header
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(URL);

			try {
				// Agregar parámetros
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
						2);
				nameValuePairs.add(new BasicNameValuePair("id", str_id));
				nameValuePairs
						.add(new BasicNameValuePair("nombre", str_nombre));
				nameValuePairs
						.add(new BasicNameValuePair("precio", str_precio));
				nameValuePairs.add(new BasicNameValuePair("descripcion",
						str_descripcion));
				nameValuePairs.add(new BasicNameValuePair("supermercado",
						str_supermercado));
				nameValuePairs.add(new BasicNameValuePair("marca", str_marca));

				// nameValuePairs.add(new BasicNameValuePair("id", valores[0]));
				// nameValuePairs.add(new BasicNameValuePair("nombre",
				// valores[1]));
				// nameValuePairs.add(new BasicNameValuePair("precio",
				// valores[2]));
				// nameValuePairs.add(new BasicNameValuePair("descripcion",
				// valores[3]));
				// nameValuePairs.add(new BasicNameValuePair("supermercado",
				// valores[4]));
				// nameValuePairs.add(new BasicNameValuePair("marca",
				// valores[5]));

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
