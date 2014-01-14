package aplicacions.aitorgonzo.ruleta;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import aplicacions.acj.ruleta.R;

public class MostrarNumerosMesa extends Activity{
	
	ListView listnumeros;
	Handler_sqlite DBH = new Handler_sqlite(MostrarNumerosMesa.this);

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mostrarnumsmesa);

		Bundle bundle = getIntent().getExtras();
		String num_id_concreto = bundle.getString("Mesa");

		String numtotal = DBH.BuscarExistentes(num_id_concreto);
		String x[] = DBH.leerArrayConIdConcreto(num_id_concreto, numtotal);
		
		listnumeros = (ListView) findViewById(R.id.listView1);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, x);
		
		listnumeros.setAdapter(adaptador);
		
		
		listnumeros.setOnItemClickListener(new OnItemClickListener(){
			 
		    @Override
		    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		        // TODO Auto-generated method stub
		        Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position, Toast.LENGTH_SHORT).show();
		 
		    }
		 
		}); 


		
	}

}
