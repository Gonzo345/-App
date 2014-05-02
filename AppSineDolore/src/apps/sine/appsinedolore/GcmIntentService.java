package apps.sine.appsinedolore;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gcm.GCMBaseIntentService;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GcmIntentService extends GCMBaseIntentService{

	@Override
	protected void onError(Context arg0, String arg1) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String msg = intent.getExtras().getString("msg");
		
		mostrarNotificaciones(context, msg);
	}

	private void mostrarNotificaciones(Context context, String msg) {
		// TODO Auto-generated method stub
		String ms = Context.NOTIFICATION_SERVICE;
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(ms);
		
		//Configuramos la notificaci—n
		int icono = android.R.drawable.star_on;
		CharSequence textoEstado="Buenas notificaci—n Push!";
		long hora = System.currentTimeMillis();
		
		Notification notificacion = new Notification(icono, textoEstado, hora);
		
		//Confihuramos el intent 
		Context contexto =context.getApplicationContext();
		CharSequence titulo="Nuevo mensaje";
		CharSequence descripcion= msg;
		
		Intent notintent = new Intent(contexto, GcmIntentService.class);
		
		PendingIntent contIntent = PendingIntent.getActivity(contexto, 0, notintent, 0);
		
		//Al pulsar en la notificacion esta desaparecer‡
		notificacion.flags |= Notification.FLAG_AUTO_CANCEL;
		
	 //Enviar Notificacion
		notificationManager.notify(1, notificacion);
	}

	@Override
	protected void onRegistered(Context arg0, String regId) {
		// TODO Auto-generated method stub
		registroServidor(regId);
	}

	private void registroServidor(String regId) {
		// Encargado de la conexi—n con el servidor
		
	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

}
//	public static final int NOTIFICATION_ID = 1;
//    private NotificationManager mNotificationManager;
//    NotificationCompat.Builder builder;
//    
//    private String TAG="Etiqueta";
//
//    public GcmIntentService() {
//        super("GcmIntentService");
//    }
//
//    @Override
//    protected void onHandleIntent(Intent intent) {
//        Bundle extras = intent.getExtras();
//        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
//        // The getMessageType() intent parameter must be the intent you received
//        // in your BroadcastReceiver.
//        String messageType = gcm.getMessageType(intent);
//
//        if (!extras.isEmpty()) {  // has effect of unparcelling Bundle
//            /*
//             * Filter messages based on message type. Since it is likely that GCM
//             * will be extended in the future with new message types, just ignore
//             * any message types you're not interested in, or that you don't
//             * recognize.
//             */
//            if (GoogleCloudMessaging.
//                    MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
//                sendNotification("Send error: " + extras.toString());
//            } else if (GoogleCloudMessaging.
//                    MESSAGE_TYPE_DELETED.equals(messageType)) {
//                sendNotification("Deleted messages on server: " +
//                        extras.toString());
//            // If it's a regular GCM message, do some work.
//            } else if (GoogleCloudMessaging.
//                    MESSAGE_TYPE_MESSAGE.equals(messageType)) {
//                // This loop represents the service doing some work.
//                for (int i=0; i<5; i++) {
//                    Log.i(TAG, "Working... " + (i+1)
//                            + "/5 @ " + SystemClock.elapsedRealtime());
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                    }
//                }
//                Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());
//                // Post notification of received message.
//                sendNotification("Received: " + extras.toString());
//                Log.i(TAG, "Received: " + extras.toString());
//            }
//        }
//        // Release the wake lock provided by the WakefulBroadcastReceiver.
//        GcmBroadcastReceiver.completeWakefulIntent(intent);
//    }
//
//    // Put the message into a notification and post it.
//    // This is just one simple example of what you might choose to do with
//    // a GCM message.
//    private void sendNotification(String msg) {
//        mNotificationManager = (NotificationManager)
//                this.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
//                new Intent(this, Inicial.class), 0);
//
//        NotificationCompat.Builder mBuilder =
//                new NotificationCompat.Builder(this)
//        .setSmallIcon(R.drawable.video)
//        .setContentTitle("GCM Notification")
//        .setStyle(new NotificationCompat.BigTextStyle()
//        .bigText(msg))
//        .setContentText(msg);
//
//        mBuilder.setContentIntent(contentIntent);
//        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
//    }
//
//}