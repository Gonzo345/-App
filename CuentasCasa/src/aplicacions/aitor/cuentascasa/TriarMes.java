package aplicacions.aitor.cuentascasa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class TriarMes extends Activity {
	private DatePicker fecha;
	private String mes = "", any = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.triarmes);

		fecha = (DatePicker) findViewById(R.id.date);

		Button bttriat = (Button) findViewById(R.id.bttriat);
		

		bttriat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
//				Toast.makeText(TriarMes.this, fecha.getYear()+"    "+fecha.getMonth(), Toast.LENGTH_LONG).show();
				Intent i = new Intent(TriarMes.this, MesElegido.class);
				i.putExtra("mes", fecha.getMonth()+"");
				i.putExtra("any", fecha.getYear()+"");
				startActivity(i);
				finish();
			}
		});

	}
}
