package aplicaciones.aitorgonzo.barcodeprueba;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.barcodeprueba.R;

public class CrearProducto extends Activity{
	
	private RadioButton rbfull, rbgallery;
	public String URL = "http://www.menorcapp.net/pasarelaInsertScann.php",
			URLExiste = "http://www.menorcapp.net/existe.php?id=",
			str_id = "no especificado", str_nombre = "no especificado",
			str_precio = "no especificado",
			str_descripcion = "no especificado",
			str_supermercado = "no especificado",
			str_marca = "no especificado";
	private String id="",name="";
	
	//Constantes para identificar la acci—n
	private static int TAKE_PICTURE = 1;
	private static int SELECT_PICTURE = 2;
	
	private TextView txname, txcost, txid, txmarca;
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.crearproducto);
		
		txname = (TextView) findViewById(R.id.txname);
		txcost = (TextView) findViewById(R.id.txcost);
		txid = (TextView) findViewById(R.id.txid);
		txmarca = (TextView) findViewById(R.id.txmarca);
		
		name= Environment.getExternalStorageDirectory() + "/test.jpg";
		
		Button btenviar = (Button) findViewById(R.id.btenviar);	//Confirmamos env’o de datos
		Button btfoto = (Button) findViewById(R.id.btfoto);	//Bot—n para foto
		
		
		rbfull = (RadioButton) findViewById(R.id.rbcamara);
		rbgallery = (RadioButton) findViewById(R.id.rbgaleria);
		
		
		btenviar.setOnClickListener(new OnClickListener() {
			//Al hacer click en bot—n enviar env’a informai—n a servidor
			@Override
			public void onClick(View arg0) {
			
				str_nombre = txname.getText().toString();
				str_precio = txcost.getText().toString();
				str_id = txid.getText().toString();
				str_marca = txmarca.getText().toString();
				
				EnviarDatosInsert(URL);
				
			}
		});
		
		btfoto.setOnClickListener(new OnClickListener() {
			//Al hacer click en boton foto hace foto
			@Override
			public void onClick(View arg0) {
			
				int code = TAKE_PICTURE;	
				Intent intent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				//Usamos los radiobuttons
				if (rbfull.isChecked()) {
					
					//Tiramos de c‡mara
					Uri output = Uri.fromFile(new File(name));
					intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
				} 
				else if (rbgallery.isChecked()){
					
					//Tiramos de galer’a
					intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
					code = SELECT_PICTURE;
				}
				
				startActivityForResult(intent, code);
			}
		});
		
		if (savedInstanceState == null) {
		    savedInstanceState = getIntent().getExtras();
		    if(savedInstanceState == null) {
		        id = null;
		    } else {
		        id = savedInstanceState.getString("id");
		    }
		} else {
		    id= (String) savedInstanceState.getSerializable("id");		
		}
		
		TextView barcode = (TextView) findViewById(R.id.txid);
		barcode.setText(id);
	}
	
	@Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	 //Comprobamos si la imagen viene de la c‡mara (TAKE_PICTURE) o de la galer’a (SELECT_PICTURE)
    	if (requestCode == TAKE_PICTURE) {
    		 //Si se reciben datos en el intent tenemos una vista previa (thumbnail)
    		
    		if (data != null) {
    			
    			//En el caso de una vista previa, obtenemos el extra data del intent y lo mostramos en el ImageView
    			if (data.hasExtra("data")) { 
    				ImageView iv = (ImageView)findViewById(R.id.imagen);
    				iv.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
    			}
    		//Sino, es una imagen completa  			
    		} else {
    			
    			//A partir del nombre del archivo ya definido lo buscamos y creamos el bitmap para el ImageView
    			ImageView iv = (ImageView)findViewById(R.id.imagen);
    			
    			//Asignamos imagen a ImageView
    			iv.setImageBitmap(BitmapFactory.decodeFile(name));
    			
    			//Para guardar la imagen en la galer’a, utilizamos una conexi—n a un MediaScanner
    			new MediaScannerConnectionClient() {
    				private MediaScannerConnection msc = null; {
    					msc = new MediaScannerConnection(getApplicationContext(), this); msc.connect();
    				}
    				public void onMediaScannerConnected() { 
    					msc.scanFile(name, null);
    				}
    				public void onScanCompleted(String path, Uri uri) { 
    					msc.disconnect();
    				} 
    			};				
    		}
    	
    		//Recibimos el URI de la imagen y construimos un Bitmap a partir de un stream de Bytes
    	} else if (requestCode == SELECT_PICTURE){
    		Uri selectedImage = data.getData();
    		InputStream is;
    		try {
    			is = getContentResolver().openInputStream(selectedImage);
    	    	BufferedInputStream bis = new BufferedInputStream(is);
    	    	Bitmap bitmap = BitmapFactory.decodeStream(bis);            
    	    	ImageView iv = (ImageView)findViewById(R.id.imagen);
    	    	iv.setImageBitmap(bitmap);						
    		} catch (FileNotFoundException e) {}
    	}
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

				//Agregar par‡metros
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

				//Ejecutar la petici—n HTTP Post
				HttpResponse response = httpclient.execute(httppost);

				InputStream is = response.getEntity().getContent();

				String datos = convertStreamToString(is);

				datos = datos.substring(0, datos.indexOf("]") + 1);
				respuesta = datos;
				
				Intent intent = new Intent(CrearProducto.this,MostrarProducto.class);
				intent.putExtra("id", id);
				startActivityForResult(intent, 0);

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
			return respuesta;
		}
		
//		private static String convertStreamToString(InputStream is) {
			private String convertStreamToString(InputStream is) {

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
	
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getURLExiste() {
		return URLExiste;
	}

	public void setURLExiste(String uRLExiste) {
		URLExiste = uRLExiste;
	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
