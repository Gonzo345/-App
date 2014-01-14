package aplicacions.aitorgonzo.barcode;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class EnviarNuevoBarcode extends Activity {
	
	SQLiteDatabase db;
	Handler_sqlite dbh;
	TextView tx1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enviarnuevobarcode);
		
		dbh= new Handler_sqlite(this);
		db=dbh.getReadableDatabase();
		
		   Bundle bundle=getIntent().getExtras();
		   tx1.setText(bundle.getString("barcode"));
		
		
		
	}
	
	

}
