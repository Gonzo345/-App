package apps.sine.appsinedolore;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class Youtube extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.youtube);

		WebView page = (WebView) findViewById(R.id.page);
		page.loadUrl("https://www.youtube.com/user/SineDolore/feed");

		// Tenemos que hacer que la visita a YouTube sea por medio de la API
		
		//Esto que esta aqui es una simple prueba, lo que hace es cerrar la activity
		finish();
		
		Toast.makeText(Youtube.this, "Mirar si la app funciona correctamente", Toast.LENGTH_LONG).show();
		

	}

}
