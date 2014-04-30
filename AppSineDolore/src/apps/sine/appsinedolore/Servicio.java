package apps.sine.appsinedolore;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Servicio extends Service {
    private static final String TAG = "MyService";
 
    @Override
    public IBinder onBind(Intent i) {
        // TODO Auto-generated method stub
        return null;
    }
 
    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
 
        new Thread(new Runnable() {
 
            @Override
            public void run() {
                Log.d(TAG, "FirstService started");
                // El servicio se finaliza a s’ mismo cuando finaliza su
                // trabajo.
                try {
                    // Simulamos trabajo de 10 segundos.
                    Thread.sleep(10000);
                     
                    // Instanciamos e inicializamos nuestro manager.
                    NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
 
                    Log.d(TAG, "sleep finished");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
 
            }
        }).start();
 
        this.stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }
}