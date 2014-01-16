package aplicacions.aitorgonzo.ruleta;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import aplicacions.acj.ruleta.R;

public class Borrar extends Activity {
	
	Handler_sqlite DBH = new Handler_sqlite(this);
	

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.borrar);

		Button btsi= (Button)findViewById(R.id.btsi);
		Button btno= (Button)findViewById(R.id.btno);
		
		btsi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				DBH.Borrar();
				Borrar.this.finish();
			}
		});
		
	btno.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Borrar.this.finish();
			}
		});
		
	}
}
