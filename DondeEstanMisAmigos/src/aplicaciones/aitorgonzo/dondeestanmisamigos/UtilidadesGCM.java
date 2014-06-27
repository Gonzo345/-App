package aplicaciones.aitorgonzo.dondeestanmisamigos;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public final class UtilidadesGCM {
	static final String SERVER_URL = "http://www.menorcapp.net/dema/"; // Identificador
																					// del
																					// proyecto
																					// en
																					// Google
																					// Console
																					// que
																					// usa
																					// el
																					// servicio
																					// GCM.
	// [TIENES QUE SUSTITUIRLO POR EL TUYO] 
	static final String SENDER_ID ="714817077344";
	static final String DISPLAY_MESSAGE_ACTION = "aplicaciones.aitorgonzo.dondeestanmisamigos.DISPLAY_MESSAGE";
	private static Handler manejador = new Handler();

	void mostrarMensaje(final Context context, final String mensaje) {
		manejador.post(new Runnable() {
			public void run() {
				Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
			}
		});
	}
}