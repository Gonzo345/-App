package com.example.dondeestanmisamigos;

import java.io.BufferedReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class ListarAmigos extends Activity {
	
	private Button btsolicitudes, btanadir;
	private ListView listaamigos;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listaramigos);
		
		btsolicitudes= (Button)findViewById(R.id.btsolicitudes);
		btanadir= (Button)findViewById(R.id.btanadir);
		
		
		
		btsolicitudes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					CogerResultadoPHP("http://www.menorcapp.net/dema/comprovarinvitaciones.php?email=aitorcosta@gmail.com");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}
	
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

					if (response.equals("1")) {
						toast("1");
					} else {
						toast("0");
					}
				}
			});

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
//			text.append(" ERROR ");
		}
	}
	
	public void toast(String msg){
		Toast.makeText(ListarAmigos.this, msg, Toast.LENGTH_LONG).show();
	}

}
