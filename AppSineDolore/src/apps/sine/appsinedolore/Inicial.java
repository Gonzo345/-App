package apps.sine.appsinedolore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Inicial extends Activity {

	private Button btform, btyt, btpage, btpdf, btwine;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inicial);

		btform = (Button) findViewById(R.id.btform);
		btwine = (Button) findViewById(R.id.btwine);
		btpage = (Button) findViewById(R.id.btpage);
		btpdf = (Button) findViewById(R.id.btpdf);
		btyt = (Button) findViewById(R.id.btyt);

		//Formulario
		btform.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, Formulario.class);
				startActivity(i);
				// Toast.makeText(Inicial.this, "hola",
				// Toast.LENGTH_SHORT).show();
			}
		});
		//Youtube
		btyt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, Youtube.class);
				startActivity(i);
			}
		});
		//Wine
		btwine.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, Wine.class);
				startActivity(i);
			}
		});
		//page
		btpage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, Page.class);
				startActivity(i);
			}
		});
		//pdf
		btpdf.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, PDF.class);
				startActivity(i);
			}
		});
		//#################### FIN DE BOTONES  ########################

		
		
		
	}

}
