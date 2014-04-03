package apps.sine.appsinedolore;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class PDF extends Activity {
	
	private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//ANTERIOR VERSIÓN 
//		setContentView(R.layout.pdf);

//		WebView vista = (WebView)findViewById(R.id.page);
//		vista.loadUrl("http://www.painmeeting.org/pdfs/programa2014.pdf");
		//FIN DE ANTERIOR VERSIÓN	- No funciona. Descarga pero se queda en blanco.
		
		//VERSIÓN CON VISOR DE GOOGLE DRIVE 
//		mWebView = new WebView(this);
//		mWebView.getSettings().setJavaScriptEnabled(true);
//		final Activity activity = this;
//		
//		mWebView.setWebViewClient(new WebViewClient() {
//            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        mWebView.loadUrl("https://docs.google.com/gview?embedded=true&url="+"http://www.painmeeting.org/pdfs/programa2014.pdf");
//        setContentView(mWebView );
		//FIN DE VERSIÓN CON GOOGLE DRIVE - Funciona pero no es cómodo de ver.
		
		//Versión nativa funcional
		setContentView(R.layout.pdf);
		Button button = (Button) findViewById(R.id.OpenPdfButton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Context context = getApplicationContext();
				Resources res = context.getResources();
				
				String pdf="programa2014";
				int soundId = res.getIdentifier(pdf, "raw", context.getPackageName());
				
				Toast.makeText(PDF.this, 
                        soundId,
                        Toast.LENGTH_SHORT).show();
				
				Uri path = Uri.parse("android.resource://apps.sine.appsinedolore/raw/" + soundId);
                
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(path, "application/pdf");
              	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                
                try {
                    startActivity(intent);
                } 
                catch (ActivityNotFoundException e) {
                    Toast.makeText(PDF.this, 
                        "No Application Available to View PDF", 
                        Toast.LENGTH_SHORT).show();
                }
//                
//                if (file.exists()) {
//                    Uri path = Uri.fromFile(file);
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setDataAndType(path, "application/pdf");
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                    try {
//                        startActivity(intent);
//                    } 
//                    catch (ActivityNotFoundException e) {
//                        Toast.makeText(PDF.this, 
//                            "No Application Available to View PDF", 
//                            Toast.LENGTH_SHORT).show();
//                    }
//                }
            }
		});
	}	
}