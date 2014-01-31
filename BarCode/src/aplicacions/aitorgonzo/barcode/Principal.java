package aplicacions.aitorgonzo.barcode;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Principal extends Activity {
	TextView txResultado, txLista;
	Handler_sqlite dbh;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);

		dbh = new Handler_sqlite(this);
		db = dbh.getReadableDatabase();

		Button bt1 = (Button) findViewById(R.id.bt1);
		Button btlistar= (Button)findViewById(R.id.btListar);
		txResultado = (TextView) findViewById(R.id.tv1);
		txLista = (TextView) findViewById(R.id.tvLista);
		

		

		

		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent("com.google.zxing.client.android.SCAN");
				i.putExtra("SCAN_MODE", "");
				startActivityForResult(i, 0);
			}
		});
		
		
		btlistar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				txLista.setText("");
				
				dbh.Insertar("544900011527", "fanta", 1, "Fanta naranja 33",
						"fanta.jpg");
				dbh.Insertar("BN39-00244H", "Monitor", 300, "Monitor encontrado",
								"Mon.jpg");
				dbh.Insertar("2555211001394", "Sebo", 2, "sebo Sas Tanques",
										"fanta.jpg");
				
				String x[] = dbh.leerArray();

				for (int j = 0; j < x.length; j++) {
					txLista.setText(txLista.getText() + "\n" + x[j]);
				}
			}
		});
		
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String contents = intent.getStringExtra("SCAN_RESULT");
				

				txResultado.setText(contents);
				
//				dbh.BuscarSiExiste(contents);
				
				
				//con esto comprovamos si el resultado es 0 no esta en la base de datos y si es 1 significa que si que existe
				if(txResultado.getText()=="0"){
					Intent i = new Intent(this, EnviarNuevoBarcode.class);
					i.putExtra("barcode", contents);
					startActivity(i);
					
					txLista.setText("paso de ti como de la mierda");
				}
				
				txLista.setText(dbh.BuscarSiExiste(contents));

			} else if (resultCode == RESULT_CANCELED) {

			}
		}
	}

}
