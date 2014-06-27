package aplicacions.aitorgonzo.rebootnow;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Inicial extends ActionBarActivity {

	private Button btreiniciar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inicial);

//		btreiniciar = (Button) findViewById(R.id.Reiniciar);
//
//		btreiniciar.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//
//				existeConexionInternet();
//
//			}
//		});
		arrancarservicio();

	}

	private void arrancarservicio() {
		startService(new Intent(Inicial.this, ServicioComprobador.class));
		
	}

	public boolean existeConexionInternet() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			mostrar("si");
			return true;
		}
		mostrar("no");
		return false;
	}

	public void mostrar(String cadena) {

		Toast.makeText(this, cadena, Toast.LENGTH_LONG).show();
	}

}
