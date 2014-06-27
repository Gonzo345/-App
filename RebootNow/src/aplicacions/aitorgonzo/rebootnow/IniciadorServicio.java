package aplicacions.aitorgonzo.rebootnow;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class IniciadorServicio extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent servicio = new Intent();
        servicio.setAction("MiServicio");
        context.startService(servicio);
	}


}
