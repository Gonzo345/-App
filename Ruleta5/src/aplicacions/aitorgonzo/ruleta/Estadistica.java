package aplicacions.aitorgonzo.ruleta;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import aplicacions.acj.ruleta.R;

public class Estadistica extends Activity {

	TextView txlista;
	Handler_sqlite DBH = new Handler_sqlite(Estadistica.this);

	// LinearLayout lnrlyt= new LinearLayout(this);

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grafico);

		String cad = this.getString(R.string.stringEstadisticaLabel);

		txlista = (TextView) findViewById(R.id.txlista);

		Bundle bundle = getIntent().getExtras();
		String num_id_concreto = bundle.getString("Estadistica");

		String numtotal = DBH.BuscarExistentes(num_id_concreto);

		txlista.setText(cad + " " + num_id_concreto);

		String x[] = DBH.leerArrayConIdConcreto(num_id_concreto, numtotal);

		int[] cad0_36 = new int[37];

		for (int j = 0; j < cad0_36.length; j++) {// Inicializamos el array a 0
			cad0_36[j] = 0;
		}
		//
		for (int j = 0; j < cad0_36.length; j++) {
			for (int k = 0; k < x.length; k++) {
				if (x[k].equals(j + "")) {

					cad0_36[j]++;// incrementamos el valor de la posicion en el
									// caso que el numero coincida con la
									// posicion en la que estamos
					// txlista.setText(txlista.getText()+" "+j+" "+x[k]+"\n");

				}
			}
		}
		// cad0_36[j] = Integer.getInteger(GestionarPorcentages(numtotal,x[j]));
		String resultado;

		int first = 0, second = 0;
		for (int j = 0; j < cad0_36.length; j++) {

			if (cad0_36[j] > first)
				first = cad0_36[j];

		}

		for (int j = 0; j < cad0_36.length; j++) {

			if (cad0_36[j] > second && cad0_36[j] != first)
				second = cad0_36[j];

		}

		// Para mostrar los numeros recorremos el array anterior para
		// imprimirlos, para poder poner en rojo los numeros tendremos que
		// tratarlos antes de llegar a este bucle
		
		//Creamos los parametros que tiene que incorporar la vista
		android.widget.LinearLayout.LayoutParams parametres = new LinearLayout.LayoutParams(
				android.widget.LinearLayout.LayoutParams.FILL_PARENT,
				android.widget.LinearLayout.LayoutParams.MATCH_PARENT);

		LinearLayout lyt = new LinearLayout(this);//creamos un linarlayout
		lyt.setLayoutParams(parametres);
		lyt.setOrientation(LinearLayout.VERTICAL);
		lyt.setVerticalScrollBarEnabled(true);
		
		ScrollView scroll = new ScrollView(this);//creamos el scroll para poder ver toda la lista
        scroll.setLayoutParams(parametres);
		scroll.setVerticalScrollBarEnabled(true);
		
		scroll.addView(lyt);//asignamos el layout al scroll para permitir el movimiento
		

		for (int j = 0; j < cad0_36.length; j++) {
			if (cad0_36[j] != 0) {
				
				TextView txtvw = new TextView(this);

				if (cad0_36[j] == first || cad0_36[j] == second) {

					// txlista.setText(txlista.getText() + "\n " + j
					// + " -> vermell"
					// + GestionarPorcentages(numtotal, cad0_36[j]) + "%");

					txtvw.setText("\n " + j + " -> "
							+ GestionarPorcentages(numtotal, cad0_36[j]) + "%");
					txtvw.setTextColor(getResources().getColor(R.color.rojo));
					

				} else {

					// txlista.setText(txlista.getText() + "\n " + j + " -> "
					// + GestionarPorcentages(numtotal, cad0_36[j]) + "%");

					txtvw.setText("\n " + j + " -> "
							+ GestionarPorcentages(numtotal, cad0_36[j]) + "%");

				}
				txtvw.setTextSize(20);

				lyt.addView(txtvw);//añadimos cada elemento al layout
				
			}
			

			setContentView(scroll);//asignamos el scroll que lo contiene todo como una vista
		}

		DBH.close();

	}

	private String GestionarPorcentages(String numtotal, int cad0_36) {// hace
																		// los
																		// porcentages

		float total = Integer.parseInt(numtotal);
		float cad = cad0_36;

		float resultado = (cad / total) * 100;

		// Eliminar decimales
		String tofilter = resultado + ""; // Pasamos el resultado con decimales
											// a String para ser escaneado

		int pospunto = tofilter.indexOf(".", 0); // Encontramos la posición del
													// decimal
		String resultfiltrado = "";
		try {
			resultfiltrado = tofilter.substring(0, pospunto + 3); // Guardamos
																	// el
																	// decimal
																	// filtrado
		} catch (Exception e) {
			resultfiltrado = resultado + "";
		}

		return resultfiltrado;
	}

}
