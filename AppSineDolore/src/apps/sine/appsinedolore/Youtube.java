package apps.sine.appsinedolore;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class Youtube extends Activity {

	// private WebView mWebView;
	// private MediaPlayer mediaplayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.youtube);
		
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=SeXSNxObX2g&list=PLuqOO6gUWfdQph0KUc4jVcuDzJ6oL0VXz")));
		finish();
		
	}

	// @Override
	// public void onSaveInstanceState(Bundle guardar) {
	// super.onSaveInstanceState(guardar);
	//
	// mWebView.saveState(guardar);
	//
	// }
	//
	// @Override
	// public void onRestoreInstanceState(Bundle savedInstanceState) {
	// super.onRestoreInstanceState(savedInstanceState);
	//
	// }

}
