package apps.sine.appsinedolore;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class Youtube extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.youtube);

		WebView page = (WebView) findViewById(R.id.page);
		page.loadUrl("https://www.youtube.com/user/SineDolore/feed");

		// Tenemos que hacer que la visita a YouTube sea por medio de la API
		
		

	}

}
