package aplicacions.aitorgonzo.rebootnow;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

public class ServicioComprobador extends Service {
	private Timer timer;
	private Notification myNotification;
	private NotificationManager notificationManager;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onCreate() {
		super.onCreate();
		
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				existeConexionInternet();
			}
		}, 0, 300000);
	}

	public boolean existeConexionInternet() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		Notificacion();
		return false;
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void Notificacion() {

		Context context = getApplicationContext();
		Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("aitor"));
		PendingIntent pendingIntent = PendingIntent.getActivity(
				ServicioComprobador.this, 0, myIntent,
				Intent.FLAG_ACTIVITY_NEW_TASK);

		myNotification = new Notification.Builder(context)
				.setContentTitle("No tens internet carinyu...")
				.setContentText("el tens que reiniciar").setTicker("Guapa")
				.setWhen(System.currentTimeMillis())
				.setContentIntent(pendingIntent)
				.setDefaults(Notification.DEFAULT_LIGHTS).setAutoCancel(true)
				.setDefaults(Notification.DEFAULT_VIBRATE).setAutoCancel(true)
				.setDefaults(Notification.DEFAULT_SOUND).setAutoCancel(true)
				.setSmallIcon(R.drawable.ic_launcher).build();

		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(1, myNotification);

	}
}
