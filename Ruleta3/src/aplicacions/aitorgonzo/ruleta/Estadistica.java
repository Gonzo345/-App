package aplicacions.aitorgonzo.ruleta;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import aplicacions.acj.ruleta.R;

public class Estadistica extends Activity {

	TextView txlista;
	Handler_sqlite DBH = new Handler_sqlite(Estadistica.this);

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grafico);
		txlista = (TextView) findViewById(R.id.txlista);

		Bundle bundle = getIntent().getExtras();
		String num_id_concreto = bundle.getString("Estadistica");

		String numtotal = DBH.BuscarExistentes(num_id_concreto);

//		txlista.setText(num_id_concreto + " " + numtotal);
		txlista.setText("");
		String x[] = DBH.leerArrayConIdConcreto(num_id_concreto, numtotal);

		int[] cad0_36 = new int[37];

		for (int j = 0; j < cad0_36.length; j++) {
			cad0_36[j] = 0;
		}
		//
		for (int j = 0; j < cad0_36.length; j++) {
			for (int k = 0; k < x.length; k++) {
				if (x[k].equals(j + "")) {

					cad0_36[j]++;
					// txlista.setText(txlista.getText()+" "+j+" "+x[k]+"\n");

				}
			}
		}
		// cad0_36[j] = Integer.getInteger(GestionarPorcentages(numtotal,x[j]));
		String resultado;
		for (int j = 0; j < cad0_36.length; j++) {
			if (cad0_36[j] != 0) {
				txlista.setText(txlista.getText() + "\n " + j + " -> "
//						+ cad0_36[j] + " "
						+ GestionarPorcentages(numtotal, cad0_36[j]) + "%");
			}
		}
		
		DBH.close();

	}

	private String GestionarPorcentages(String numtotal, int cad0_36) {

		float total = Integer.parseInt(numtotal);
		float cad=cad0_36;
		
		float resultado = (cad / total )* 100;
		
		
		//Eliminar decimales
		String tofilter =resultado + "";	//Pasamos el resultado con decimales a String para ser escaneado
		
		int pospunto=tofilter.indexOf(".", 0);	//Encontramos la posición del decimal
		String resultfiltrado ="";
		try{
			resultfiltrado = tofilter.substring(0,pospunto+3);	//Guardamos el decimal filtrado	
		}catch (Exception e){
			resultfiltrado = resultado+"";
		}
		
		
		
		return resultfiltrado;
	}

}
