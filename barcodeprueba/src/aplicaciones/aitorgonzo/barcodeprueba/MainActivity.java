package aplicaciones.aitorgonzo.barcodeprueba;

import com.example.barcodeprueba.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button1=(Button)findViewById(R.id.button1);
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ejecutascan();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    if (requestCode == 0) {
	        if (resultCode == RESULT_OK) {
	            String result = intent.getStringExtra("resultat");
	            
	            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
	            
	            // Hacer algo con los datos obtenidos.
	        } else if (resultCode == RESULT_CANCELED) {
	            // Si se cancelo la captura.
	        }
	    }
	}
	
	public void ejecutascan(){
		Intent intent = new Intent("com.example.barcodeprueba.SCAN");
		startActivityForResult(intent, 0);

	}
	


}
