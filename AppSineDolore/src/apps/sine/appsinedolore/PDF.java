package apps.sine.appsinedolore;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class PDF  extends Activity {
	
	private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.pdf);

//		WebView vista = (WebView)findViewById(R.id.page);
//		vista.loadUrl("http://www.painmeeting.org/pdfs/programa2014.pdf");
		
		mWebView = new WebView(this);
		mWebView.getSettings().setJavaScriptEnabled(true);
		final Activity activity = this;
		
		mWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });

        mWebView.loadUrl("https://docs.google.com/gview?embedded=true&url="+"http://www.painmeeting.org/pdfs/programa2014.pdf");
        setContentView(mWebView );
		
	}
	
}

