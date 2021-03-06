package apps.sine.appsinedolore;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class PDF extends Analytics {

	private WebView mWebView;
	private TextView tv_link;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


		// ANTERIOR VERSI�N
		// setContentView(R.layout.pdf);

		// WebView vista = (WebView)findViewById(R.id.page);
		// vista.loadUrl("http://www.painmeeting.org/pdfs/programa2014.pdf");
		// FIN DE ANTERIOR VERSI�N - No funciona. Descarga pero se queda en
		// blanco.

		// VERSI�N CON VISOR DE GOOGLE DRIVE
		// mWebView = new WebView(this);
		// mWebView.getSettings().setJavaScriptEnabled(true);
		// final Activity activity = this;
		//
		// mWebView.setWebViewClient(new WebViewClient() {
		// public void onReceivedError(WebView view, int errorCode, String
		// description, String failingUrl) {
		// Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
		// }
		// });
		//
		// mWebView.loadUrl("https://docs.google.com/gview?embedded=true&url="+"http://www.painmeeting.org/pdfs/programa2014.pdf");
		// setContentView(mWebView );
		// FIN DE VERSI�N CON GOOGLE DRIVE - Funciona pero no es c�modo de ver.

		// Versi�n nativa funcional
		setContentView(R.layout.pdf);

		tv_link = (TextView) findViewById(R.id.textView1);

		tv_link.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("https://play.google.com/store/apps/details?id=com.adobe.reader")));
			}
		});

		CopyReadAssets();

		// File file = new File("/storage/sdcard1/programa2014.pdf");
		//
		// Toast.makeText(PDF.this,
		// "Estoy dentro",
		// Toast.LENGTH_SHORT).show();
		//
		// if(file.exists()) {
		// Uri path = Uri.fromFile(file);
		// Intent intent = new Intent(Intent.ACTION_VIEW);
		// intent.setDataAndType(path, "application/pdf");
		// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		//
		// try {
		// startActivity(intent);
		// }
		// catch (ActivityNotFoundException e) {
		// Toast.makeText(PDF.this,
		// "No hay aplicaci�n lectora de PDFs",
		// Toast.LENGTH_LONG).show();
		// }
		// }
		// else
		// Toast.makeText(PDF.this,
		// "No existe el archivo",
		// Toast.LENGTH_SHORT).show();
		// finish();

	}

	private void CopyReadAssets() {
		AssetManager assetManager = getAssets();

		InputStream in = null;// pilla de assets
		OutputStream out = null;// donde lo soltar�
		File file = new File(getFilesDir(), "programa2014.pdf");
		try {
			in = assetManager.open("programa2014.pdf");
			out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

			copyFile(in, out);// copia
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {
			Log.e("tag", e.getMessage());
		}

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(
				Uri.parse("file://" + getFilesDir() + "/programa2014.pdf"),
				"application/pdf");
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		try {
			startActivity(intent);
			finish();
		} catch (ActivityNotFoundException e) {
			Toast.makeText(PDF.this, "No hay aplicaci�n lectora de PDFs",
					Toast.LENGTH_LONG).show();
		}

	}

	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}

}