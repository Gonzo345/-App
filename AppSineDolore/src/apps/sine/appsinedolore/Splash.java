package apps.sine.appsinedolore;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class Splash extends Activity {
	ImageView ima1, ima2;
	Drawable draw;
	private long splashDelay = 2000;// tiempo en lanzar la siguiente actividad
	private long CambioImagen = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		ima1 = (ImageView) findViewById(R.id.ima1);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		Resources res = getResources();
		draw = res.getDrawable(R.drawable.logo);

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Intent mainIntent = new Intent().setClass(Splash.this,
						Inicial.class);
				startActivity(mainIntent);
				finish();// Destruimos esta activity para prevenit que el
							// usuario retorne aqui presionando el boton Atras.
			}
		};

		Timer timer = new Timer();
		timer.schedule(task, splashDelay);// Pasado los 3 segundos dispara la
											// tarea

	}

}
