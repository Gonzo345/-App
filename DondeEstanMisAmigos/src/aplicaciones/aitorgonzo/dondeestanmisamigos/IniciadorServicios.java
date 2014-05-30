package aplicaciones.aitorgonzo.dondeestanmisamigos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class IniciadorServicios extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) 
    {
        Intent servicio = new Intent();
        servicio.setAction("ServicioLocalizacion");
        context.startService(servicio);
    }
}