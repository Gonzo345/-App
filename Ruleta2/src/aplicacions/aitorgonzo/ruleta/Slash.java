package aplicacions.aitorgonzo.ruleta;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import aplicacions.acj.ruleta.R;


public class Slash extends Activity {
	
	private long splashDelay = 4000;//tiempo en lanzar la siguiente actividad
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slash);
		
		ImageView ima1, ima2;
		ima1= (ImageView)findViewById(R.id.ima1);
//
//		Animation rotard= AnimationUtils.loadAnimation(this, R.anim.rotard);
		
//		ima1.startAnimation(rotard);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

	    TimerTask task = new TimerTask() {
	      @Override
	      public void run() {
	        Intent mainIntent = new Intent().setClass(Slash.this, Inicial.class);
	        startActivity(mainIntent);
	        finish();//Destruimos esta activity para prevenit que el usuario retorne aqui presionando el boton Atras.
	      }
	    };

	    Timer timer = new Timer();
	    timer.schedule(task, splashDelay);//Pasado los 3 segundos dispara la tarea

		
		
	}

}
