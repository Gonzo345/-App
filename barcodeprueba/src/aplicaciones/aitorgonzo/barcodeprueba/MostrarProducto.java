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

	private String URL = "http://www.menorcapp.net/mostrarproductoid.php?id=",
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
			

			toast("dins de la connexi— amnb " + URL+id);
			AsyncHttpClient client = new AsyncHttpClient();
			client.get(URL + id, new AsyncHttpResponseHandler() {
				
				@Override
				public void onSuccess(String response) {
					int ini=0, fin=0;
					System.out.println(response);

					toast(response);
					
					txname.setText(response.substring(ini, response.indexOf("&")));
					txcost.setText(response.substring(response.indexOf("&")+1, response.lastIndexOf("&")));
					txmarca.setText(response.substring(response.lastIndexOf("&")+1,response.length()));

					try {

					

					} catch (Exception e) {

					}

				}
			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
			toast("Error al intentar connectar");
		}

	}

	private void toast(String string) {
		// TODO Auto-generated method stub
		Toast.makeText(MostrarProducto.this, string, Toast.LENGTH_LONG).show();
	}
}
