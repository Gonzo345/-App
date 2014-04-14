package apps.sine.appsinedolore;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Inicial extends Analytics {

	private Button btform, btyt, btpage, btpdf, btwine, btgmaps, btabout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inicial);

		btform = (Button) findViewById(R.id.btform);
		btwine = (Button) findViewById(R.id.btwine);
		btpage = (Button) findViewById(R.id.btpage);
		btpdf = (Button) findViewById(R.id.btpdf);
		btyt = (Button) findViewById(R.id.btyt);
		btgmaps = (Button) findViewById(R.id.btgmaps);
		btabout = (Button) findViewById(R.id.btabout);

		//Formulario
		btform.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, Formulario.class);
				startActivity(i);
			}
		});
		//YouTube
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
		//WebPage
		btpage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, Page.class);
				startActivity(i);
			}
		});
		//PDF
		btpdf.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, PDF.class);
				startActivity(i);
			}
		});
		//Google Maps
		btgmaps.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, Maps.class);
				startActivity(i);
			}
		});
		
		btabout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Inicial.this, About.class);
				startActivity(i);
			}
			
		});
		//#################### FIN DE BOTONES  ########################
		
	}
	
	@Override
	protected void onStart() {
	    super.onStart();
	    EasyTracker.getInstance(this).activityStart(this);
	}

	@Override
	protected void onStop() {
	    super.onStop();
	    EasyTracker.getInstance(this).activityStop(this);
	}

}
