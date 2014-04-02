package aplicacions.aitorgonzo.vibrador;

import java.security.Timestamp;
import java.security.cert.CertPath;
import java.sql.Date;
import java.util.Timer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.view.MotionEventCompat;
import android.text.format.Time;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Inicial extends Activity {

	public String DEBUG_TAG = "GesturesActivity";
	private Button bt1, bt2,bt4;
	private TextView t3;
	private Vibrator vibra;
	private CertPath signerCertPath;
	private long[] patron;
	private long T1=0, T2=0;
	private java.util.Date time;

	// private Timestamp ts = new Timestamp(timestamp, signerCertPath);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inicial);

		bt1 = (Button) findViewById(R.id.bt1);
		bt2 = (Button) findViewById(R.id.bt2);
		bt4 = (Button) findViewById(R.id.bt4);
		t3 = (TextView) findViewById(R.id.bt3);

		vibra = (Vibrator) getSystemService(getApplicationContext().VIBRATOR_SERVICE);

		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				vibra.vibrate(2000);
			}
		});

		bt2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vibra.cancel();
			}
		});
		
		bt4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				vibra.vibrate(patron, 1);
			}
		});

		t3.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent e) {
				// TODO Auto-generated method stub

				int action = MotionEventCompat.getActionMasked(e);

				switch (action) {
				case (MotionEvent.ACTION_DOWN):
					Log.d(DEBUG_TAG, "La accion ha sido ABAJO");
					vibra.vibrate(1000);
//					T1 = time.getTime();
					
					Mostrar(T1);
					
//					GuardarPatron(T2,T1);
					T2=0;
					// Log.e("hola", ts.getTimestamp()+"");
					return true;
					// case (MotionEvent.ACTION_MOVE):
					// Log.d(DEBUG_TAG, "La acción ha sido MOVER");
					// return true;
				case (MotionEvent.ACTION_UP):
					Log.d(DEBUG_TAG, "La acción ha sido ARRIBA");
					vibra.cancel();
//					T2 = time.getTime();
					
					Mostrar(T2);
//					GuardarPatron(T1,T2);
					T1=0;
					
					return true;
					
				case (MotionEvent.ACTION_CANCEL):
					Log.d(DEBUG_TAG, "La accion ha sido CANCEL");
					return true;
					// case (MotionEvent.ACTION_OUTSIDE):
					// Log.d(DEBUG_TAG,
					// "La accion ha sido fuera del elemento de la pantalla");
					// return true;
				default:
					return true;
				}
			}


			private void GuardarPatron(long t1, long t2) {
				
				patron[patron.length]=t1-t2;
				
			}
		});

	}
	
	
	private void Mostrar(long t) {
		// TODO Auto-generated method stub
		Toast.makeText(this, t+"", Toast.LENGTH_LONG).show();
	}

}
