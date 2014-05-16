package aplicaciones.aitorgonzo.barcodeprueba;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barcodeprueba.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MostrarProducto extends Activity {

	private String URL = "http://www.menorcapp.com/mostrarproductoid.php?id=",
			id = "";
	private Button btno, btok;
	private TextView txname, txcost, txid, txmarca;

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

			AsyncHttpClient client = new AsyncHttpClient();
			client.get(URL + id, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(String response) {
					System.out.println(response);

					toast(response);

					try {

						int ini = response.indexOf("1");
						response = response.substring(ini, ini + 1);

					} catch (Exception e) {

					}

				}
			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}

	}

	private void toast(String string) {
		// TODO Auto-generated method stub
		Toast.makeText(MostrarProducto.this, string, Toast.LENGTH_LONG).show();
	}
}
