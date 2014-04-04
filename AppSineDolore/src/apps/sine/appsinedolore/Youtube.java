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
//		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCVt9B-idq0NR-EDaUFMN-wg")));

		finish();

		// Intent lVideoIntent = new Intent(null,
		// Uri.parse("ytpl://SeXSNxObX2g&list=PLuqOO6gUWfdQph0KUc4jVcuDzJ6oL0VXz"),
		// this, OpenYouTubePlayerActivity.class);

		// mWebView = new WebView(this);
		// mWebView.getSettings().setJavaScriptEnabled(true);
		//
		// mWebView.setWebChromeClient(new WebChromeClient());
		//
		// // WebSettings w = mWebView.getSettings();
		// w.setPluginState(PluginState.ON);
		//
		//
		// final Activity activity = this;
		//
		// mWebView.setWebViewClient(new WebViewClient() {
		// public void onReceivedError(WebView view, int errorCode,
		// String description, String failingUrl) {
		// Toast.makeText(activity, description, Toast.LENGTH_SHORT)
		// .show();
		// }
		// });
		//
		// setContentView(mWebView);
		//
		// if (savedInstanceState != null) {
		// mWebView.restoreState(savedInstanceState);
		// } else {
		// mWebView.loadUrl("https://m.youtube.com/user/SineDolore/feed");
		// }
		//
		// // Tenemos que pillar el seekto
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
