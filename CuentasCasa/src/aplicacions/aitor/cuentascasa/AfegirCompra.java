package aplicacions.aitor.cuentascasa;

import java.io.BufferedReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class AfegirCompra extends Activity {

	private Button btguardar;
	private EditText tenda, preu;
	private DatePicker date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.afegircompra);

		btguardar = (Button) findViewById(R.id.btguardar);
		tenda = (EditText) findViewById(R.id.tenda);
		preu = (EditText) findViewById(R.id.preu);
		date = (DatePicker) findViewById(R.id.date);

		// boto que envia la informacio al servidor
		btguardar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (tenda.equals("") || preu.equals("")) {
					toast("Falta informació per insertar...");
				} else {

					try {
						EnviarCompra("http://www.menorcaapata.com/insertarcompra.php?super="
								+ tenda.getText().toString()
								+ "&preu="
								+ preu.getText().toString()
								+ "&dia="
								+ date.getDayOfMonth()
								+ "&mes="
								+ date.getMonth() + "&any=" + date.getYear());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});

	}

	public void EnviarCompra(String url) throws Exception {
		BufferedReader in = null;

		try {

			AsyncHttpClient client = new AsyncHttpClient();
			client.get(url, new AsyncHttpResponseHandler() {
				@Override
				public void onSuccess(String response) {
					System.out.println(response);
					try {
						// if (response.indexOf(c)(1)) {
						// finish();
						// } else {
						// toast("No tens accés aquest moment");
						// }
						finish();

					} catch (Exception e) {
						toast("Final del catch onsucces");
						Log.e("peta", e + "");
						// toast("peta por " + e);
					}
				}
			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
			// text.append(" ERROR ");
		}
	}

	public void toast(String cadena) {
		Toast.makeText(this, cadena, Toast.LENGTH_LONG).show();

	}

}
