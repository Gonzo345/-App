package apps.sine.appsinedolore;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Page extends Analytics {

//	private WebView browser;
//
//	@SuppressWarnings("deprecation")
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.page);
//
//		browser = new WebView(this);
//
//		// habilitamos javascript y el zoom
//		browser.getSettings().setJavaScriptEnabled(true);
//		browser.getSettings().setBuiltInZoomControls(true);
//
//		// habilitamos los plugins (flash)
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
//			browser.getSettings().setPluginState(PluginState.ON);
//		} else {
//			setPluginsEnabled(true);
//		}
//
//		browser.loadUrl("http://sinedolore.org");
//		finish();
//
//
//
//	}
//
//	private void setPluginsEnabled(boolean b) {
//		// TODO Auto-generated method stub
//		
//	}
	
	private WebView mWebView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.wine);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		mWebView = new WebView(this);
		mWebView.getSettings().setJavaScriptEnabled(true);
		final Activity activity = this;
		
		mWebView.setWebViewClient(new WebViewClient() {
            @Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });
		
        mWebView.loadUrl("http://sinedolore.org");
        setContentView(mWebView );
	}
}

