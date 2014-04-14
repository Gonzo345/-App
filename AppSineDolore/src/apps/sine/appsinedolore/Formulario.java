package apps.sine.appsinedolore;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class Formulario extends Analytics {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		Toast.makeText(Formulario.this, "hola", Toast.LENGTH_SHORT).show();

	}

}
