package apps.sine.appsinedolore;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Wine extends Analytics {

	private WebView mWebView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.wine);
		
		mWebView = new WebView(this);
		mWebView.getSettings().setJavaScriptEnabled(true);
		final Activity activity = this;
		
		mWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });

        mWebView.loadUrl("shop.sinedolore.org");
        setContentView(mWebView );

	}
	
}
