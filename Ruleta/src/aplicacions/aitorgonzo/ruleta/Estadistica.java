package aplicacions.aitorgonzo.ruleta;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import aplicacions.acj.ruleta.R;

public class Estadistica extends Activity {
	
	ImageView ima1;
	Button btgros, btpetit;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.estadistica);
		
		
		ima1 = (ImageView)findViewById(R.id.ima1);
		btgros= (Button)findViewById(R.id.btgros);
		btpetit= (Button)findViewById(R.id.btpetit);
		
		DBHelper dbh= new DBHelper(Estadistica.this);
		
		
		btgros.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Gros();

			}
		});
		
		btpetit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Petit();

			}
		});
		
		
	}
	
	public void Gros(){
		ima1.getLayoutParams().height += 60;
		ima1.getLayoutParams().width += 60;
		
	}	
	public void Petit(){
		ima1.getLayoutParams().height += 20;
		ima1.getLayoutParams().width += 20;
		
	}
}
