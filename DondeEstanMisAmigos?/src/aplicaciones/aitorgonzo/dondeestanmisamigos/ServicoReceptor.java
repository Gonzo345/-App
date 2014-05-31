package aplicaciones.aitorgonzo.dondeestanmisamigos;

import android.content.Context;
import android.content.Intent;

import com.google.android.gcm.GCMBaseIntentService;

public class ServicoReceptor extends GCMBaseIntentService {

	@Override
	protected void onError(Context context, String errorId) {
		// Error en el registro: tratamiento del error
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		// Notificaci—n recibida: informo al usuario u otra acci—n
	}

	@Override
	protected void onRegistered(Context context, String regId) {
		// Registro correcto: env’o el regId a mi servidor
	}

	@Override
	protected void onUnregistered(Context context, String regId) {
		// Borrado correcto: informo a mi servidor
	}
}