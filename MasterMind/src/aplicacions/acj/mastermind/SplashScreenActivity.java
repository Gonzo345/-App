package aplicacions.acj.mastermind;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

//import java.util.Timer;
//import java.util.TimerTask;
//
//
//import android.os.Bundle;
//import android.app.Activity;
//import android.content.Intent;
//import android.content.pm.ActivityInfo;
//import aplicacions.acj.mastermind.R;

public class SplashScreenActivity extends Activity {

  private long splashDelay = 3000; //3 segundos

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    setContentView(R.layout.activity_splash_screen);

    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        Intent mainIntent = new Intent().setClass(SplashScreenActivity.this, Segona.class);
        startActivity(mainIntent);
        finish();//Destruimos esta activity para prevenit que el usuario retorne aqui presionando el boton Atras.
      }
    };

    Timer timer = new Timer();
    timer.schedule(task, splashDelay);//Pasado los 3 segundos dispara la tarea
  }

}