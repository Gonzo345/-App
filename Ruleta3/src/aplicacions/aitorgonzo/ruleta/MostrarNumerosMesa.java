package aplicacions.aitorgonzo.ruleta;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import aplicacions.acj.ruleta.R;

public class MostrarNumerosMesa extends Activity{
	
	ListView listnumeros;
	Handler_sqlite DBH = new Handler_sqlite(MostrarNumerosMesa.this);

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mostratnumsmesa);
		
		listnumeros = (ListView) findViewById(R.id.listview1);

		Bundle bundle = getIntent().getExtras();
		String num_id_concreto = bundle.getString("Mesa");

		
		String numtotal = DBH.BuscarExistentes(num_id_concreto);
		
		
		String x[] = DBH.leerArrayConIdConcreto(num_id_concreto, numtotal);
		
		for(int a =0; a<x.length;a++){
			
		}
		
	}

}
