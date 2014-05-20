package aplicaciones.aitorgonzo.barcodeprueba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
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
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barcodeprueba.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class Inicial extends Activity {

	private String[] arg;
	public String URL = "http://www.menorcapp.net/pasarelaInsertScann.php",
			URLExiste = "http://www.menorcapp.net/existe.php?id=",
			str_id = "no especificado", str_nombre = "no especificado",
			str_precio = "no especificado",
			str_descripcion = "no especificado",
			str_supermercado = "no especificado",
			str_marca = "no especificado";

	private Connection conexionMySQL;
	private TextView textResultadoSQL, text, txtotal;

	Button bteliminar;
	ListView listacesta;
	Handler_sqlite DBH = new Handler_sqlite(Inicial.this);
	String[] NombresProductos = {};
	String[] PrecioProductos = {};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button1 = (Button) findViewById(R.id.button1);
		bteliminar = (Button) findViewById(R.id.bteliminar);

		text = (TextView) findViewById(R.id.text);// label grande
		txtotal = (TextView) findViewById(R.id.txtotal);

		textResultadoSQL = (TextView) findViewById(R.id.textResultadoSQL);// label
																			// para
																			// el
																			// id
		textResultadoSQL = (TextView) findViewById(R.id.textResultadoSQL);

		// llamada a la recuperaci—n de la cesta
		RecuperarCesta();

		// ################ Llamada al scaner de la app ##################
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ejecutascan();
			}
		});
		// ************ FIN DE LA LLAMADA AL SCANER ********************
		bteliminar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SparseBooleanArray seleccionados = listacesta
						.getCheckedItemPositions();

				EliminarSeleccionados(seleccionados);

			}

		});
	}

	// metodo que nos devuelve el resultado del scanner
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				str_id = intent.getStringExtra("resultat");

				textResultadoSQL.setText(str_id);

				try {
					// llamamos a la url concatenando el resultado del scaner
					executeHttpGet(URLExiste + str_id);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

					   Log.e("falllalalalalalallal", "error");

					// toast("Hola desde catch del try encargado de sacar el codigo fuente");
				}

				// EnviarDatosInsert(URL);

			} else if (resultCode == RESULT_CANCELED) {
				// Si se cancelo la captura.
			}
		}
	}

	private void toast(String string) {
		// TODO Auto-generated method stub
		Toast.makeText(Inicial.this, string, Toast.LENGTH_LONG).show();
	}

	public void ejecutascan() {
		Intent intent = new Intent("com.example.barcodeprueba.SCAN");
		startActivityForResult(intent, 0);

	}

	public void EnviarDatosInsert(String URL) {

		ConexionServidor task = new ConexionServidor();
		task.execute(new String[] { "" });

	}

	// acceso a BBDD SQLITE para llenar el listview
	public void RecuperarCesta() {

		String numtotal = DBH.BuscarExistentes();// buscar
//		toast(numtotal);
		// numeros
		// que
		// pertenecen
		// a una
		// mesa en
		// concreto
		NombresProductos = DBH.leerArray(numtotal); // recupera los valores de
													// la cesta
		PrecioProductos = DBH.leerArrayPrecio(numtotal); // recupera los valores
															// de la cesta
		listacesta = (ListView) findViewById(R.id.listacompra);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_checked, NombresProductos);

		listacesta.setAdapter(adaptador);
		listacesta.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		try {

			float total = 0;
			for (int j = 0; j < PrecioProductos.length; j++) {
				total += Integer.parseInt(PrecioProductos[j]);

			}

			txtotal.setText(total + "");
		} catch (Exception e) {
			toast("peta: " + e);
		}

	}

	private class ConexionServidor extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {

			String respuesta = "";

			// Inicializar, creando HttpClient y Post Header
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(URL);

			try {

				// Agregar par‡metros
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
						2);
				nameValuePairs.add(new BasicNameValuePair("id", getStr_id()));
				nameValuePairs.add(new BasicNameValuePair("nombre",
						getStr_nombre()));
				nameValuePairs.add(new BasicNameValuePair("precio",
						getStr_precio()));
				nameValuePairs.add(new BasicNameValuePair("descripcion",
						getStr_descripcion()));
				nameValuePairs.add(new BasicNameValuePair("supermercado",
						getStr_supermercado()));
				nameValuePairs.add(new BasicNameValuePair("marca",
						getStr_marca()));

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

	public void executeHttpGet(String url) throws Exception {
		BufferedReader in = null;

		try {

			AsyncHttpClient client = new AsyncHttpClient();
			client.get(url, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(String response) {
					System.out.println(response);
//					text.setText(response);

					// toast(response);

					try {

						int ini = response.indexOf("1");
						response = response.substring(ini, ini + 1);

					} catch (Exception e) {

					}

					if (response.equals("1")) {

						// toast("Existe");
						// llamada a la actividad de vista producto
						Intent i = new Intent(Inicial.this,
								MostrarProducto.class);
						i.putExtra("id", str_id);
						startActivity(i);

					} else {
						// toast("No existe");
						// llamada a la actividad encargada de registrarla
						Intent i = new Intent(Inicial.this, CrearProducto.class);
						i.putExtra("id", str_id);
						startActivity(i);
					}
				}
			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
//			text.append(" ERROR ");
		}
		// return "ERROR";
	}

	// ############### Declaraci—n de los getters y setters para poder acceder a
	// los parametros que tenemos que ir rellenando de los productos

	public String getStr_id() {
		return str_id;
	}

	public void setStr_id(String str_id) {
		this.str_id = str_id;
	}

	public String getStr_nombre() {
		return str_nombre;
	}

	public void setStr_nombre(String str_nombre) {
		this.str_nombre = str_nombre;
	}

	public String getStr_precio() {
		return str_precio;
	}

	public void setStr_precio(String str_precio) {
		this.str_precio = str_precio;
	}

	public String getStr_descripcion() {
		return str_descripcion;
	}

	public void setStr_descripcion(String str_descripcion) {
		this.str_descripcion = str_descripcion;
	}

	public String getStr_supermercado() {
		return str_supermercado;
	}

	public void setStr_supermercado(String str_supermercado) {
		this.str_supermercado = str_supermercado;
	}

	public String getStr_marca() {
		return str_marca;
	}

	public void setStr_marca(String str_marca) {
		this.str_marca = str_marca;
	}

	// ############### FIN getters setters#####################

	// Metodo que se encargara de eliminar los items seleccionados
	private void EliminarSeleccionados(SparseBooleanArray seleccionados) {

		SQLiteDatabase db = DBH.getWritableDatabase();

		if (seleccionados == null || seleccionados.size() == 0) {

			Toast.makeText(this, "No hay elementos seleccionados",
					Toast.LENGTH_SHORT).show();

		} else {
			// si los había, miro sus valores

			// Esto es para ir creando un mensaje largo que mostraré al final
			StringBuilder resultado = new StringBuilder();
			resultado
					.append("Se eliminarán los siguientes elementos:\n VALOR         Posicion \n");

			// Recorro my "array" de elementos seleccionados
			final int size = seleccionados.size();
			for (int i = 0; i < size; i++) {
				// Si valueAt(i) es true, es que estaba seleccionado
				if (seleccionados.valueAt(i)) {
					// en keyAt(i) obtengo su posición
					resultado.append(listacesta.getItemAtPosition(
							seleccionados.keyAt(i)).toString()
							+ "   ----------->  "
							+ seleccionados.keyAt(i)
							+ "\n");

					DBH.Buscar_Eliminar(listacesta.getItemAtPosition(
							seleccionados.keyAt(i)).toString());
				}
			}
			
			RecuperarCesta();

		}
	}

}
