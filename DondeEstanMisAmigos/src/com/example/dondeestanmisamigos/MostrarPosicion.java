package com.example.dondeestanmisamigos;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class MostrarPosicion extends Activity {
	
	private String id = "";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mostrarposicion);


		// _______ Recuperamos el putextra_____________
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
		
	}

}
