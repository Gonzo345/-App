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

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.example.barcodeprueba.R;

public class InsertBdOnline {
	private String URL_destino;
	private String [] valores;
	

//	public void EnviarDatosInsert(String URL, String [] arg) {
//
//		URL_destino=URL;
//		for(int j=0; j<arg.length;j++){
//			
//		valores[j]=arg[j];
//		}
//		ConexionServidor task = new ConexionServidor();
//		task.execute(new String[] { "" });
//
//	}
//
//	private class ConexionServidor extends AsyncTask<String, Void, String> {
//		@Override
//		protected String doInBackground(String... urls) {
//			
//			
//			String respuesta = "";
//
//			// Inicializar, creando HttpClient y Post Header
//			HttpClient httpclient = new DefaultHttpClient();
//			HttpPost httppost = new HttpPost(URL_destino);
//
//			try {
//				// Agregar parámetros
//				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
//				nameValuePairs.add(new BasicNameValuePair("id", str_id));
//				nameValuePairs.add(new BasicNameValuePair("nombre",str_nombre));
//				nameValuePairs.add(new BasicNameValuePair("precio",str_precio));
//				nameValuePairs.add(new BasicNameValuePair("descripcion",str_descripcion));
//				nameValuePairs.add(new BasicNameValuePair("supermercado", str_supermercado));
//				nameValuePairs.add(new BasicNameValuePair("marca", str_marca));
////				nameValuePairs.add(new BasicNameValuePair("id", valores[0]));
////				nameValuePairs.add(new BasicNameValuePair("nombre", valores[1]));
////				nameValuePairs.add(new BasicNameValuePair("precio", valores[2]));
////				nameValuePairs.add(new BasicNameValuePair("descripcion", valores[3]));
////				nameValuePairs.add(new BasicNameValuePair("supermercado", valores[4]));
////				nameValuePairs.add(new BasicNameValuePair("marca", valores[5]));
//
//				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//
//				// Ejecutar la petición HTTP Post
//				HttpResponse response = httpclient.execute(httppost);
//
//				InputStream is = response.getEntity().getContent();
//
//				String datos = convertStreamToString(is);
//
//				datos = datos.substring(0, datos.indexOf("]") + 1);
//				respuesta = datos;
//
//			} catch (ClientProtocolException e) {
//				// TODO Auto-generated catch block
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//			}
//			return respuesta;
//		}
//
//		@Override
//		protected void onPostExecute(String result) {
//			// textView.setText(result);
//		}
//	}
//
//	private static String convertStreamToString(InputStream is) {
//
//		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//		StringBuilder sb = new StringBuilder();
//
//		String line = null;
//		try {
//			while ((line = reader.readLine()) != null) {
//				sb.append(line + "\n");
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				is.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return sb.toString();
//	}

}
