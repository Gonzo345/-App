package apps.sine.appsinedolore;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.PluginState;
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
            @Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });
		
        mWebView.loadUrl("http://shop.sinedolore.org");
        setContentView(mWebView );
	}
}
//
//	private WebView browser;
//	@Override
//
//		protected void onCreate(Bundle savedInstanceState) {
//			super.onCreate(savedInstanceState);
//			setContentView(R.layout.wine);
//			browser = new WebView(this);
//
//			// habilitamos javascript y el zoom
//			browser.getSettings().setJavaScriptEnabled(true);
//			browser.getSettings().setBuiltInZoomControls(true);
//
//			// habilitamos los plugins (flash)
//			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
//				browser.getSettings().setPluginState(PluginState.ON);
//			} else {
//				setPluginsEnabled(true);
//			}
//
//			browser.loadUrl("https://www.shop.sinedolore.org");
//			Log.e("peta","despues de browser");
//			finish();
//
//	}
//	private void setPluginsEnabled(boolean b) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//}
