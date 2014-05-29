package aplicaciones.aitorgonzo.dondeestanmisamigos;

import java.io.BufferedReader;

import android.content.Intent;
import android.os.Bundle;
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

import aplicaciones.aitorgonzo.dondeestanmisamigos.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class Login extends ActionBarActivity {

	private EditText txpassword;	//Declarado para capturarlo posteriormente
	private EditText txuser;
	private String id="";
	
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
						//toast("1");
					} else {
					
					//Si va mal devuelve 0
						//toast("0");
					}
				}
			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
//			text.append(" ERROR ");
		}
	}
	
	public void toast(String msg){
		Toast.makeText(Login.this, msg, Toast.LENGTH_LONG).show();
	}

}
