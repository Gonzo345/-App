package aplicaciones.aitorgonzo.dondeestanmisamigos;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Followers extends Activity{
	
	private Button bteliminar;
	private ListView listafollowers;
	private String[] lista = {};
	private String iduser = "";
	
	protected void onCreate(Bundle savedInstanceState) {
		
		//Bot—n y ListView
		listafollowers = (ListView) findViewById(R.id.listfollowers);
		bteliminar = (Button) findViewById(R.id.bteliminar);
		
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.followers);
		
	}
	
	
	
	
	
	public void CargarSolicitudes() {

		// listaamigos = (ListView) findViewById(R.id.listView1);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, lista);

		listafollowers.setAdapter(adaptador);
		listafollowers.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

	}

}
