package aplicacions.aitorgonzo.ruleta;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
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
import android.widget.TextView;
import android.widget.Toast;
import aplicacions.acj.ruleta.R;

public class Inicial extends Activity {

	SeekBar seek;
	Button btinsertar, btestadistica;
	String numsel;
	TextView num1;
	// Llamamos a la clase de base de datos
	DBHelper DBH = new DBHelper(Inicial.this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inicial);

		// seek = (SeekBar) findViewById(R.id.seek);
		btinsertar = (Button) findViewById(R.id.btinsetar);
		btestadistica = (Button) findViewById(R.id.btestadistica);
		num1 = (TextView) findViewById(R.id.num1);

		// creamos el combobox
		Spinner spin = (Spinner) findViewById(R.id.spi1);
		ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
				R.array.num, android.R.layout.simple_spinner_dropdown_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adapter);

		spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int pos, long id) {

				numsel = parentView.getItemAtPosition(pos).toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		findViewById(R.id.btinsetar).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(Inicial.this, "el num es: " + numsel,
						Toast.LENGTH_SHORT).show();

				DBH.insertar(1, numsel, "aa/aa");

			}
		});

		findViewById(R.id.btestadistica).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {

						Intent i = new Intent(Inicial.this, Estadistica.class);
						startActivity(i);

					}
				});

	}

	public void VerEstadistica() {

		Intent i = new Intent(Inicial.this, Estadistica.class);
		startActivity(i);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inicial, menu);
		return true;
	}

}
