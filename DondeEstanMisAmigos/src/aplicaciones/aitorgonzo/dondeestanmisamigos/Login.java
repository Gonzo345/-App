package aplicaciones.aitorgonzo.dondeestanmisamigos;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class Login extends ActionBarActivity {

	private EditText txpassword;	//Declarado para capturarlo posteriormente
	private EditText txuser;
	private String id="", pass="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		Button btregistro = (Button)findViewById(R.id.btregistro);
		Button btlogin = (Button)findViewById(R.id.btlogin);
		txuser = (EditText)findViewById(R.id.txuser);
		txpassword = (EditText)findViewById(R.id.txpassword);
		
		
		//Comprovamos si el Shared Preferences tiene el login y el pass para hacer el login autom‡tico
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		id = prefs.getString("id", "");
		pass = prefs.getString("pass", "");
		
		toast(id+pass);
		//________________________
		//Al hacer click en Login, si todo est‡ ok nos lleva a ListarAmigos
		btlogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				//Procesado de login en remoto
				String username = txuser.getText().toString();
				String password = txpassword.getText().toString();
				id=username;
				
				try {
					// URL de ejemplo: http://www.menorcapp.net/DEMA/%20login.php?id=gonzo&pass=1234567890
					CogerResultadoPHP("http://www.menorcapp.net/DEMA/login.php?id="+ username + "&pass=" + password);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				// finish();
			}
		});
		
		
		//Al hacer click en Registro, nos lleva a la activity de registrarse como usuario
		btregistro.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Login.this, Registro.class);
				startActivity(i);
				// finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_login,
					container, false);
			return rootView;
		}
	}
	
	//MŽtodo para procesado en remoto
	public void CogerResultadoPHP(String url) throws Exception {
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

					//Si va bien devuelve 1
					if (response.equals("1")) {
						
						//Como ha ido todo bien, lanza activity ListarAmigos
						Intent i = new Intent(Login.this, ListarAmigos.class);
						i.putExtra("id", id);
						startActivity(i);
						
						// __________GUARDAMOS LAS VARIABLES EN SHAREDPREFERENCES PARA
						// UTILIZARLAS EN EL SERVICIO______________
						SharedPreferences prefes = getSharedPreferences("pref_variables",
								MODE_PRIVATE);
						SharedPreferences.Editor editor = prefes.edit();
						editor.putString("id", id);
						editor.putString("pass", txpassword.getText().toString());
						editor.commit();
						
						toast("guardamos las preferencias");
						// ________________________
						
						
						//toast("1");
					} else {
					
					//Si va mal devuelve 0
						toast("Nombre de usuario o contrase–a incorrectos");
					}
				}
			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
//			text.append(" ERROR ");
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
				|| txpassword.getText().toString().equals("")) {

			//toast(R.string.formtoastError);
		} else {

			if (ComprobarEmail()) {
				//POR IMPLEMENTAR BT ENVIAR
			}
		}
	}
	
	public void toast(String msg){
		Toast.makeText(Login.this, msg, Toast.LENGTH_LONG).show();
	}

}
