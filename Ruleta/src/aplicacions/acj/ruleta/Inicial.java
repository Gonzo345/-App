package aplicacions.acj.ruleta;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class Inicial extends Activity {

	SeekBar seek;
	Button btinsertar;
	String numsel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inicial);
		
		//seek = (SeekBar) findViewById(R.id.seek);
		btinsertar= (Button)findViewById(R.id.btinsetar);
		
		Spinner spin = (Spinner)findViewById(R.id.spi1);
		ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.num, android.R.layout.simple_spinner_dropdown_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adapter);
		
		spin.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
					int pos, long id) {
				
				//Toast.makeText(parentView.getContext(), "Seleccio de " + parentView.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
				// TODO Auto-generated method stub
				numsel=parentView.getItemAtPosition(pos).toString();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}});
		
		findViewById(R.id.btinsetar).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(Inicial.this, "en num es: "+ numsel, Toast.LENGTH_SHORT).show();
				
			}
		});

		
//		findViewById(R.id.seek).setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				
//				btinsertar.setText(seek.getProgress());
//				btinsertar.setText("hioal");
//				
//			}
//		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inicial, menu);
		return true;
	}

}
