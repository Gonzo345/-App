package aplicaciones.aitorgonzo.dondeestanmisamigos;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import aplicaciones.aitorgonzo.dondeestanmisamigos.R;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class Invitar extends Activity {

	private EditText txamigo;
	private Button btenviar;
	private String id;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invitar);

		// Recuperamos el putextra con id de emisor
		if (savedInstanceState == null) {
			savedInstanceState = getIntent().getExtras();
			if (savedInstanceState == null) {
				id = null;
			} else {
				id = savedInstanceState.getString("id");
			}
		} else {
			id = (String) savedInstanceState.getSerializable("id");
		}

		//toast(id);

		btenviar = (Button) findViewById(R.id.btenviar);

		btenviar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				invitar();
			}
		});
	}

	public void CogerResultadoPHP(String url) throws Exception {
		BufferedReader in = null;
		//toast(url);
		try {
			AsyncHttpClient client = new AsyncHttpClient();
			client.get(url, new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(String response) {
					System.out.println(response);

					
					try {
						int ini = response.indexOf("1");
						response = response.substring(ini, ini + 1);
						

					} catch (Exception e) {
						
					}

					// Si va bien devuelve 1
					if (response.equals("1")) {

						// Como ha ido todo bien, lanza activity ListarAmigos
						Intent i = new Intent(Invitar.this,
								EstadoInvitaciones.class);
						i.putExtra("id", id);
						startActivity(i);
						toast("Invitaci�n registrada con �xito");
						//Finalizamos la actividad porque queremos que nos lleve �nicamente a estadoinvitaciones 
						finish();
					} else {
						// Si va mal devuelve 0
						toast("E-mail de amigo incorrecto o no se encuentra registrado");
					}
				}
			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
			// text.append(" ERROR ");
			toast("primer try");
		}
	}

	public void toast(String msg) {
		Toast.makeText(Invitar.this, msg, Toast.LENGTH_LONG).show();
	}

	public void invitar() {
		try {

			// Registro de invitaci�n en servidor remoto
			txamigo = (EditText) findViewById(R.id.txamigo);
			String amigo = txamigo.getText().toString();

			// URL ejemplo
			// http://www.menorcapp.net/dema/crearinvitacion.php?emisor=andorid&receptor=gonzo
			String URLt = "http://www.menorcapp.net/dema/registro.php?emisor="
					+ id + "&receptor=" + amigo;

			CogerResultadoPHP("http://www.menorcapp.net/dema/crearinvitacion.php?emisor="
					+ id + "&receptor=" + amigo);
			// toast(URLt);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private boolean ComprobarEmail() {

		//M�todo para comprobar e-mail con formato correcto
		String cadena= txamigo.getText().toString();
		String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	        
	        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
	 
	        Matcher matcher = pattern.matcher(cadena);
	        return matcher.matches();
		
	}
	
	public void btnEnviar() {

		if (txamigo.getText().toString().equals("")) {

			//toast(R.string.formtoastError);
		} else {

			if (ComprobarEmail()) {
				//POR IMPLEMENTAR BT ENVIAR
			}
		}
	}

}
