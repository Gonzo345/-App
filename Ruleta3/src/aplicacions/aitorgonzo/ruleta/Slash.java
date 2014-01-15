package aplicacions.aitorgonzo.ruleta;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import aplicacions.acj.ruleta.R;

public class Slash extends Activity {

	ImageView ima1, ima2;
	Drawable draw ;
	private long splashDelay = 2000;// tiempo en lanzar la siguiente actividad
	private long CambioImagen = 1000;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slash);

		ima1 = (ImageView) findViewById(R.id.ima1);
//		ima2 = (ImageView) findViewById(R.id.ima2);
		//
//		Animation invi= AnimationUtils.loadAnimation(this, R.anim.invi);
//		 ima1.startAnimation(invi);
//		 
//		 Animation vi= AnimationUtils.loadAnimation(this, R.anim.vi);
//		 ima2.startAnimation(vi);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		Resources res = getResources();
		draw= res.getDrawable(R.drawable.ruleta);

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Intent mainIntent = new Intent().setClass(Slash.this,
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
