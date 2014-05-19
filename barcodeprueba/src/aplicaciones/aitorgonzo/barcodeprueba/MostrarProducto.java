package aplicaciones.aitorgonzo.barcodeprueba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barcodeprueba.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MostrarProducto extends Activity {

	private String URL = "http://www.menorcapp.net/mostrarproductoid.php?id=",
			id = "";
	private Button btno, btok;
	private TextView txname, txcost, txid, txmarca;
	Handler_sqlite DBH = new Handler_sqlite(MostrarProducto.this);

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mostrarproducto);

		btno = (Button) findViewById(R.id.btcancel);
		btok = (Button) findViewById(R.id.btok);

		txname = (TextView) findViewById(R.id.txname);
		txcost = (TextView) findViewById(R.id.txcost);
		txid = (TextView) findViewById(R.id.txid);
		txmarca = (TextView) findViewById(R.id.txmarca);

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
		toast(id);

		try {

			// toast("dins de la connexi— amnb " + URL + id);
			AsyncHttpClient client = new AsyncHttpClient();
			client.get(URL + id, new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(String response) {
					int ini = 0, fin = 0;
					System.out.println(response);

					// toast(response);
//					toast(response.substring(ini, response.indexOf("&")));
					String texto=response.substring(0, response.indexOf("&"));
					
					txname.setText(texto);
					txcost.setText(response.substring(response.indexOf("&") + 1,response.lastIndexOf("&")));
					txmarca.setText(response.substring(response.lastIndexOf("&") + 1, response.length()));

					try {

					} catch (Exception e) {

					}

				}
			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
			toast("Error al intentar connectar");
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
				DBH.Insertar(txid.getText().toString(), txname.getText()
						.toString(), txcost.getText().toString(), txmarca
						.getText().toString());
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
}
