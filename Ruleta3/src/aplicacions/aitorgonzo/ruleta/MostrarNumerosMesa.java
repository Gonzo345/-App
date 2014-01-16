package aplicacions.aitorgonzo.ruleta;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import aplicacions.acj.ruleta.R;

public class MostrarNumerosMesa extends Activity{
	
	Button btBorrarSeleccionados;
	ListView listnumeros;
	Handler_sqlite DBH = new Handler_sqlite(MostrarNumerosMesa.this);
	String IdRuletaDeterminada;
	String []x={};
	

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mostrarnumsmesa);

		Bundle bundle = getIntent().getExtras();
		IdRuletaDeterminada = bundle.getString("Mesa");

		String numtotal = DBH.BuscarExistentes(IdRuletaDeterminada);//buscar numeros que pertenecen a una mesa en concreto
		x = DBH.leerArrayConIdConcreto(IdRuletaDeterminada, numtotal);//recuperar los valores de una mesa en concreto
		
		btBorrarSeleccionados= (Button)findViewById(R.id.btborrarseleccionados);
		listnumeros = (ListView) findViewById(R.id.listView1);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, x);
		
		listnumeros.setAdapter(adaptador);
		listnumeros.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
		
		
		listnumeros.setOnItemClickListener(new OnItemClickListener(){
			 
		    @Override
		    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		        // TODO Auto-generated method stub
//		        Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position, Toast.LENGTH_SHORT).show();
		        
		 
		    }
		 
		}); 
		
		btBorrarSeleccionados.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SparseBooleanArray seleccionados = listnumeros.getCheckedItemPositions();
				
				EliminarSeleccionados(seleccionados, IdRuletaDeterminada);
				
				
			}

		});


		
	}

	public void EliminarSeleccionados(SparseBooleanArray seleccionados, String num_id_concreto) {
		
		SQLiteDatabase db = DBH.getWritableDatabase();
		
		if(seleccionados==null || seleccionados.size()==0){
            
            Toast.makeText(this,"No hay elementos seleccionados",Toast.LENGTH_SHORT).show();
            
        }else{
            //si los había, miro sus valores
 
            //Esto es para ir creando un mensaje largo que mostraré al final
            StringBuilder resultado=new StringBuilder();
            resultado.append("Se eliminarán los siguientes elementos:\n VALOR         Posicion \n");
 
            //Recorro my "array" de elementos seleccionados
            final int size=seleccionados.size();
            for (int i=0; i<size; i++) {
                //Si valueAt(i) es true, es que estaba seleccionado
                if (seleccionados.valueAt(i)) {
                    //en keyAt(i) obtengo su posición
//                	Toast.makeText(this, "Vuelta empezada", Toast.LENGTH_SHORT).show();	//Valor de la fila
                    resultado.append(listnumeros.getItemAtPosition(seleccionados.keyAt(i)).toString()+"   ----------->  "+seleccionados.keyAt(i)+"\n");
                    
                    
                    //Toast.makeText(this,  "El numero de la fila seleccionada es el "+seleccionados.keyAt(i)+"", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this, "Valor "+listnumeros.getItemAtPosition(seleccionados.keyAt(i)).toString() +" Posición "+seleccionados.keyAt(i)+ "\n", Toast.LENGTH_SHORT).show();	//Valor de la fila
                    
//                    //Eliminamos de la BBDD el valor de la ListView marcados.
                    DBH.Buscar_Eliminar(num_id_concreto, listnumeros.getItemAtPosition(seleccionados.keyAt(i)).toString());
//                    DBH.Buscar_Eliminar(num_id_concreto, seleccionados.keyAt(i)+"");
                }
            }
            Toast.makeText(this,resultado.toString(),Toast.LENGTH_LONG).show();
            
            this.finish();
        }
		
	}
}
