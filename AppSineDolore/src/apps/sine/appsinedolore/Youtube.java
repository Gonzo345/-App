package apps.sine.appsinedolore;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Youtube extends Activity {

	private WebView mWebView;
	private MediaPlayer mediaplayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.youtube);

		mWebView = new WebView(this);
		mWebView.getSettings().setJavaScriptEnabled(true);
		final Activity activity = this;

		mWebView.setWebViewClient(new WebViewClient() {
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				Toast.makeText(activity, description, Toast.LENGTH_SHORT)
						.show();
			}
		});

		setContentView(mWebView);

		if (savedInstanceState != null) {
			mWebView.restoreState(savedInstanceState);
		} else {
			mWebView.loadUrl("https://m.youtube.com/user/SineDolore/feed");
		}

		// Tenemos que pillar el seekto
	}

	@Override
	public void onSaveInstanceState(Bundle guardar) {
		super.onSaveInstanceState(guardar);

		mWebView.saveState(guardar);

	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

	}

}
