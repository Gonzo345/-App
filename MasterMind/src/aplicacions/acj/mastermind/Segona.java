package aplicacions.acj.mastermind;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
//
//import android.os.Bundle;
//import android.app.Activity;
//import android.content.Intent;
//import android.content.pm.ActivityInfo;
//import android.view.Menu;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.Window;
//import aplicacions.acj.mastermind.R;

public class Segona extends Activity {
	private AdView adView;
	private LinearLayout lytMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_segona);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		lytMain = (LinearLayout) findViewById(R.id.lytMain);
		adView = new AdView(this, AdSize.BANNER,
				"ca-app-pub-1825821127744760/8258350138");
		lytMain.addView(adView);
		adView.bringToFront();
		adView.loadAd(new AdRequest());

		findViewById(R.id.jugar).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				Intent mainIntent = new Intent().setClass(Segona.this,
						MainActivity.class);
				startActivity(mainIntent);
				// finish();// Destruimos esta activity para prevenit que el
				// usuario retorne aqui presionando el boton Atras.
			}
		});

		findViewById(R.id.instruccions).setOnClickListener(
				new OnClickListener() {
					public void onClick(View arg0) {

						Intent mainIntent = new Intent().setClass(Segona.this,
								Instruccions.class);
						startActivity(mainIntent);
						finish();// Destruimos esta activity para prevenit que
									// el
									// usuario retorne aqui presionando el boton
									// Atras.
					}
				});

		findViewById(R.id.sortir).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();
			}
		});

	}

	@Override
	public void onDestroy() {
		if (adView != null)
			adView.destroy();
		super.onDestroy();
	}

}
