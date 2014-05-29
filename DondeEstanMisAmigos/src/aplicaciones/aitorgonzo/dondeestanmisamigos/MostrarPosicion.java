package aplicaciones.aitorgonzo.dondeestanmisamigos;
<<<<<<< HEAD:DondeEstanMisAmigos/src/aplicaciones/aitorgonzo/dondeestanmisamigos/MostrarPosicion.java

import aplicaciones.aitorgonzo.dondeestanmisamigos.R;
=======
>>>>>>> origin/Aitor:DondeEstanMisAmigos/src/aplicaciones/aitorgonzo/dondeestanmisamigos/MostrarPosicion.java

import android.app.Activity;
import android.os.Bundle;

public class MostrarPosicion extends Activity {
	
	private String id = "";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mostrarposicion);


		// _______ Recuperamos el putextra id_____________
		if (savedInstanceState == null) {
			savedInstanceState = getIntent().getExtras();
			if (savedInstanceState == null) {
				id = null;
			} else {
				id = savedInstanceState.getString("id");
			}
		} else {
			id = (String) savedInstanceState.getSerializable("id");
		}
		
		// _______ Recuperamos el putextra con nombre del amigo a cargar_____________
		if (savedInstanceState == null) {
			savedInstanceState = getIntent().getExtras();
			if (savedInstanceState == null) {
				id = null;
			} else {
				id = savedInstanceState.getString("id");
			}
		} else {
			id = (String) savedInstanceState.getSerializable("id");
		}
		
	}
}
