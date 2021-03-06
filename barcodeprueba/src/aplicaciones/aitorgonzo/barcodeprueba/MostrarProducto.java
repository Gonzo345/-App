package aplicaciones.aitorgonzo.barcodeprueba;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barcodeprueba.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MostrarProducto extends Activity {

	private String URL = "http://www.menorcapp.net/mostrarproductoid.php?id=",
			id = "";
	private Button btno, btok;
	private TextView txcost, txid, txmarca, txname;
	private Bitmap bm;
	private ImageView iv;

	Handler_sqlite DBH = new Handler_sqlite(MostrarProducto.this);

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mostrarproducto);

		btno = (Button) findViewById(R.id.btcancel);
		btok = (Button) findViewById(R.id.btok);

		iv = (ImageView) findViewById(R.id.imagen);

		txcost = (TextView) findViewById(R.id.txcost);
		txid = (TextView) findViewById(R.id.txid);
		txmarca = (TextView) findViewById(R.id.txmarca);
		txname = (TextView) findViewById(R.id.txname);

		if (savedInstanceState == null) {
			savedInstanceState = getIntent().getExtras();
			if (savedInstanceState == null) {
				id = null;
			} else {
				id = savedInstanceState.getString("id");
				txid.setText(id);
			}
		} else {
			id = (String) savedInstanceState.getSerializable("id");
			txid.setText(id);
		}
//		toast(id);

		try {

			// toast("dins de la connexi� amb " + URL + id);
			AsyncHttpClient client = new AsyncHttpClient();
			client.get(URL + id, new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(String response) {
					int ini = 0, fin = 0;
					System.out.println(response);

					// toast(response);

					// toast(txname.getText().toString());
					txname.setText(response.substring(1, response.indexOf("&")));
					txcost.setText(response.substring(
							response.indexOf("&") + 1,
							response.lastIndexOf("&")));
					txmarca.setText(response.substring(
							response.lastIndexOf("&") + 1, response.length()));

				}
			});

			new DownloadImageTask().execute("http://www.menorcapp.net/images/"+id+".jpg");

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
			toast("Error al intentar connectar " + e);
		}
		
		
		

		// Boton cancelar
		btno.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MostrarProducto.this, Inicial.class);
				startActivity(i);
				// finish();
			}
		});
		// Boton ok
		btok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DBH.Insertar("Hola", txid.getText().toString(), txname
						.getText().toString(), txcost.getText().toString(),
						txmarca.getText().toString());
				Intent i = new Intent(MostrarProducto.this, Inicial.class);
				startActivity(i);
				finish();
			}
		});
	}

	private void toast(String string) {
		// TODO Auto-generated method stub
		Toast.makeText(MostrarProducto.this, string, Toast.LENGTH_LONG).show();
	}

	

	 class DownloadImageTask extends AsyncTask<String, Void, Drawable>
     {


            protected void onPreExecute()
            {
            }
            protected Drawable doInBackground(String... urls)
            {
                Log.d("DEBUG", "drawable");

                return downloadImage(urls[0]);

            }

            protected void onPostExecute(Drawable imagen)
            {

                iv.setImageDrawable(imagen);
            }

            /**
 * Devuelve una imagen desde una URL
 * @param url Url de la imagen
 * @return Una imagen
 */
            private Drawable downloadImage(String imageUrl)
            {
                try
                {
                    URL url = new URL(imageUrl);
                    InputStream is = (InputStream)url.getContent();
                    return Drawable.createFromStream(is, "src");
                }
                catch (MalformedURLException e)
                {
                    e.printStackTrace();
                    return null;
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    return null;
                }
            }
     }
}
