package aplicaciones.aitorgonzo.dondeestanmisamigos;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class Registro extends Activity {

	private EditText txpassword, txuser, txalias;
	private Button btregistro;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registro);

		btregistro = (Button) findViewById(R.id.btregistro);
		txuser = (EditText) findViewById(R.id.txuser);
		txpassword = (EditText) findViewById(R.id.txpassword);
		txalias = (EditText) findViewById(R.id.txalias);

		// btregistro.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// try {
		//
		// // Registro de usuario en servidor remoto
		// String username = txuser.getText().toString();
		// String password = txpassword.getText().toString();
		// String alias = txalias.getText().toString();
		//
		// // URL ejemplo
		// http://www.menorcapp.net/dema/registro.php?email=userprueba&pass=prueba&alias=prueba
		// CogerResultadoPHP("http://www.menorcapp.net/dema/registro.php?email="
		// + username + "&pass=" + password + "&alias=" + alias);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// });
		
		btregistro.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				registrar();
			}
		});


	}

	// MŽtodo para procesado en remoto
	public void CogerResultadoPHP(String url) throws Exception {
		BufferedReader in = null;
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
					
					switch(Integer.parseInt(response))
					{
						case 0:
							toast("ERROR - No se ha podido insertar");
							break;
							
						case 1:
//							Intent i = new Intent(Registro.this, Login.class);
//							startActivity(i);
							finish();	//Finalizamos para que back quede limpio
							
							toast("Se ha registrado correctamente");
							break;
							
						case 3:
							toast("Este usuario ya existe. Elige otro");
							break;
							
						default:
							toast("WTF?");
							break;
					}
				

//					// Si va bien devuelve 1
//					if (response.equals("1")) {
//
//						// Como ha ido todo bien, lanza activity ListarAmigos
//						Intent i = new Intent(Registro.this, Login.class);
//						startActivity(i);
//						toast("Registrado con Žxito");
//					} else {
//						// Si va mal devuelve 0
//						toast("El usuario ya existe. Elige otro");
//					}

				}
			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
			// text.append(" ERROR ");
		}
	}

	public void toast(String msg) {
		Toast.makeText(Registro.this, msg, Toast.LENGTH_LONG).show();
	}

	public void registrar() {
		try {

			// Registro de usuario en servidor remoto
			String username = txuser.getText().toString();
			String password = txpassword.getText().toString();
			String alias = txalias.getText().toString();

			// URL ejemplo
			// http://www.menorcapp.net/dema/registro.php?email=userprueba&pass=prueba&alias=prueba
			CogerResultadoPHP("http://www.menorcapp.net/dema/registro.php?email="
					+ username + "&pass=" + password + "&alias=" + alias);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean ComprobarEmail() {

		//MŽtodo para comprobar e-mail con formato correcto
		String cadena= txuser.getText().toString();
		String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	        
	        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
	 
	        Matcher matcher = pattern.matcher(cadena);
	        return matcher.matches();
		
	}
	
	public void btnEnviar() {

		if (txuser.getText().toString().equals("")
				|| txpassword.getText().toString().equals("")
				|| txalias.getText().toString().equals("")) {

			//toast(R.string.formtoastError);
		} else {

			if (ComprobarEmail()) {
				//POR IMPLEMENTAR BT ENVIAR
			}
		}
	}

}
