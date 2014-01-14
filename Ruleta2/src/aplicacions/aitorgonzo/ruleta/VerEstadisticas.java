package aplicacions.aitorgonzo.ruleta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import aplicacions.acj.ruleta.R;

public class VerEstadisticas extends Activity {

	Button bt1, bt2, bt3, bt4;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.estadistica);

		bt1 = (Button) findViewById(R.id.bt1);
		bt2 = (Button) findViewById(R.id.bt2);
		bt3 = (Button) findViewById(R.id.bt3);
		bt4 = (Button) findViewById(R.id.bt4);
		
		
//Llamamos al método que nos mostrara la estadistica
		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MostrarEstadistica("1");
			}
		});

		bt2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MostrarEstadistica("2");
			}
		});

		bt3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MostrarEstadistica("3");
			}
		});

		bt4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MostrarEstadistica("4");
			}
		});
	}

	public void MostrarEstadistica(String num) {
		Intent i = new Intent(VerEstadisticas.this, Estadistica.class);
		i.putExtra("Estadistica", num);
		startActivity(i);
		this.finish();
	}

}
