package aplicacions.acj.mastermind;

import java.util.Random;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//import java.util.Random;
//
//
//import android.R.layout;
//import android.os.Bundle;
//import android.app.Activity;
//import android.content.pm.ActivityInfo;
//import android.view.Menu;
//import android.view.View;
//import android.view.Window;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import aplicacions.acj.mastermind.R;

public class MainActivity extends Activity {
	
	SoAplicacio sound;
	int guanyar, perdrer;

	private static ImageView in1 = null;
	private static ImageView in2 = null;
	private static ImageView in3 = null;
	private static ImageView in4 = null;
	private static ImageView in5 = null;

	private int contpos = 0;

	master MM = new master();
	String random = MM.Mcrearnum() + "";
	String numin = "";
	boolean boolpersortir = true, guanyador = false, repetir = false,
			correcte = false, nivell = true, prova = false;
	int intents = 1, maxintents = 7;
	int contador1 = 0, contador2 = 0, contador0 = 0, resp = 0;
	String[] num_intro = new String[maxintents];
	String[] num_resul = new String[maxintents];

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		sound = new SoAplicacio(getApplicationContext());
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		guanyar= sound.carregar(R.raw.guanyar);
		perdrer= sound.carregar(R.raw.perdrer);

		requestWindowFeature(Window.FEATURE_NO_TITLE);// Evita el titol de la
														// activity

		setContentView(R.layout.activity_main);
		// Aquesta linea permet establir la orientacio de la app

		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		final EditText cnum = (EditText) findViewById(R.id.cnum);
		cnum.setEnabled(false);
		final EditText area = (EditText) findViewById(R.id.area);
		final TextView debug = (TextView) findViewById(R.id.debug);
		final TextView cintents = (TextView) findViewById(R.id.cintents);
		final LinearLayout intent1 = (LinearLayout) findViewById(R.id.intent1);
		final LinearLayout intent2 = (LinearLayout) findViewById(R.id.intent2);
		final LinearLayout intent3 = (LinearLayout) findViewById(R.id.intent3);
		final LinearLayout intent4 = (LinearLayout) findViewById(R.id.intent4);
		final LinearLayout intent5 = (LinearLayout) findViewById(R.id.intent5);
		final LinearLayout intent6 = (LinearLayout) findViewById(R.id.intent6);
		final LinearLayout intent7 = (LinearLayout) findViewById(R.id.intent7);
		area.setVisibility(View.GONE);

		final ImageView ganado = (ImageView) findViewById(R.id.ganado);
		final ImageView perdido = (ImageView) findViewById(R.id.perdido);

		final Button btn1 = (Button) findViewById(R.id.btn1);

		final ImageView in1 = (ImageView) findViewById(R.id.in1);
		final ImageView in2 = (ImageView) findViewById(R.id.in2);
		final ImageView in3 = (ImageView) findViewById(R.id.in3);
		final ImageView in4 = (ImageView) findViewById(R.id.in4);
		final ImageView in5 = (ImageView) findViewById(R.id.in5);

		final ImageView im1 = (ImageView) findViewById(R.id.im1);
		final ImageView im2 = (ImageView) findViewById(R.id.im2);
		final ImageView im3 = (ImageView) findViewById(R.id.im3);
		final ImageView im4 = (ImageView) findViewById(R.id.im4);
		final ImageView im5 = (ImageView) findViewById(R.id.im5);
		final ImageView im6 = (ImageView) findViewById(R.id.im6);
		final ImageView im7 = (ImageView) findViewById(R.id.im7);
		final ImageView im8 = (ImageView) findViewById(R.id.im8);
		final ImageView im9 = (ImageView) findViewById(R.id.im9);
		final ImageView im10 = (ImageView) findViewById(R.id.im10);
		final ImageView im12 = (ImageView) findViewById(R.id.im12);
		final ImageView im22 = (ImageView) findViewById(R.id.im22);
		final ImageView im32 = (ImageView) findViewById(R.id.im32);
		final ImageView im42 = (ImageView) findViewById(R.id.im42);
		final ImageView im52 = (ImageView) findViewById(R.id.im52);
		final ImageView im62 = (ImageView) findViewById(R.id.im62);
		final ImageView im72 = (ImageView) findViewById(R.id.im72);
		final ImageView im82 = (ImageView) findViewById(R.id.im82);
		final ImageView im92 = (ImageView) findViewById(R.id.im92);
		final ImageView im102 = (ImageView) findViewById(R.id.im102);
		final ImageView im13 = (ImageView) findViewById(R.id.im13);
		final ImageView im23 = (ImageView) findViewById(R.id.im23);
		final ImageView im33 = (ImageView) findViewById(R.id.im33);
		final ImageView im43 = (ImageView) findViewById(R.id.im43);
		final ImageView im53 = (ImageView) findViewById(R.id.im53);
		final ImageView im63 = (ImageView) findViewById(R.id.im63);
		final ImageView im73 = (ImageView) findViewById(R.id.im73);
		final ImageView im83 = (ImageView) findViewById(R.id.im83);
		final ImageView im93 = (ImageView) findViewById(R.id.im93);
		final ImageView im103 = (ImageView) findViewById(R.id.im103);
		final ImageView im14 = (ImageView) findViewById(R.id.im14);
		final ImageView im24 = (ImageView) findViewById(R.id.im24);
		final ImageView im34 = (ImageView) findViewById(R.id.im34);
		final ImageView im44 = (ImageView) findViewById(R.id.im44);
		final ImageView im54 = (ImageView) findViewById(R.id.im54);
		final ImageView im64 = (ImageView) findViewById(R.id.im64);
		final ImageView im74 = (ImageView) findViewById(R.id.im74);
		final ImageView im84 = (ImageView) findViewById(R.id.im84);
		final ImageView im94 = (ImageView) findViewById(R.id.im94);
		final ImageView im104 = (ImageView) findViewById(R.id.im104);
		final ImageView im15 = (ImageView) findViewById(R.id.im15);
		final ImageView im25 = (ImageView) findViewById(R.id.im25);
		final ImageView im35 = (ImageView) findViewById(R.id.im35);
		final ImageView im45 = (ImageView) findViewById(R.id.im45);
		final ImageView im55 = (ImageView) findViewById(R.id.im55);
		final ImageView im65 = (ImageView) findViewById(R.id.im65);
		final ImageView im75 = (ImageView) findViewById(R.id.im75);
		final ImageView im85 = (ImageView) findViewById(R.id.im85);
		final ImageView im95 = (ImageView) findViewById(R.id.im95);
		final ImageView im105 = (ImageView) findViewById(R.id.im105);
		final ImageView im16 = (ImageView) findViewById(R.id.im16);
		final ImageView im26 = (ImageView) findViewById(R.id.im26);
		final ImageView im36 = (ImageView) findViewById(R.id.im36);
		final ImageView im46 = (ImageView) findViewById(R.id.im46);
		final ImageView im56 = (ImageView) findViewById(R.id.im56);
		final ImageView im66 = (ImageView) findViewById(R.id.im66);
		final ImageView im76 = (ImageView) findViewById(R.id.im76);
		final ImageView im86 = (ImageView) findViewById(R.id.im86);
		final ImageView im96 = (ImageView) findViewById(R.id.im96);
		final ImageView im106 = (ImageView) findViewById(R.id.im106);
		final ImageView im17 = (ImageView) findViewById(R.id.im17);
		final ImageView im27 = (ImageView) findViewById(R.id.im27);
		final ImageView im37 = (ImageView) findViewById(R.id.im37);
		final ImageView im47 = (ImageView) findViewById(R.id.im47);
		final ImageView im57 = (ImageView) findViewById(R.id.im57);
		final ImageView im67 = (ImageView) findViewById(R.id.im67);
		final ImageView im77 = (ImageView) findViewById(R.id.im77);
		final ImageView im87 = (ImageView) findViewById(R.id.im87);
		final ImageView im97 = (ImageView) findViewById(R.id.im97);
		final ImageView im107 = (ImageView) findViewById(R.id.im107);

		final ImageView[] tabla = new ImageView[70];
		int cont = 0;
		tabla[cont] = im1;
		cont++;
		tabla[cont] = im2;
		cont++;
		tabla[cont] = im3;
		cont++;
		tabla[cont] = im4;
		cont++;
		tabla[cont] = im5;
		cont++;
		tabla[cont] = im6;
		cont++;
		tabla[cont] = im7;
		cont++;
		tabla[cont] = im8;
		cont++;
		tabla[cont] = im9;
		cont++;
		tabla[cont] = im10;
		cont++;
		tabla[cont] = im12;
		cont++;
		tabla[cont] = im22;
		cont++;
		tabla[cont] = im32;
		cont++;
		tabla[cont] = im42;
		cont++;
		tabla[cont] = im52;
		cont++;
		tabla[cont] = im62;
		cont++;
		tabla[cont] = im72;
		cont++;
		tabla[cont] = im82;
		cont++;
		tabla[cont] = im92;
		cont++;
		tabla[cont] = im102;
		cont++;
		tabla[cont] = im13;
		cont++;
		tabla[cont] = im23;
		cont++;
		tabla[cont] = im33;
		cont++;
		tabla[cont] = im43;
		cont++;
		tabla[cont] = im53;
		cont++;
		tabla[cont] = im63;
		cont++;
		tabla[cont] = im73;
		cont++;
		tabla[cont] = im83;
		cont++;
		tabla[cont] = im93;
		cont++;
		tabla[cont] = im103;
		cont++;
		tabla[cont] = im14;
		cont++;
		tabla[cont] = im24;
		cont++;
		tabla[cont] = im34;
		cont++;
		tabla[cont] = im44;
		cont++;
		tabla[cont] = im54;
		cont++;
		tabla[cont] = im64;
		cont++;
		tabla[cont] = im74;
		cont++;
		tabla[cont] = im84;
		cont++;
		tabla[cont] = im94;
		cont++;
		tabla[cont] = im104;
		cont++;
		tabla[cont] = im15;
		cont++;
		tabla[cont] = im25;
		cont++;
		tabla[cont] = im35;
		cont++;
		tabla[cont] = im45;
		cont++;
		tabla[cont] = im55;
		cont++;
		tabla[cont] = im65;
		cont++;
		tabla[cont] = im75;
		cont++;
		tabla[cont] = im85;
		cont++;
		tabla[cont] = im95;
		cont++;
		tabla[cont] = im105;
		cont++;
		tabla[cont] = im16;
		cont++;
		tabla[cont] = im26;
		cont++;
		tabla[cont] = im36;
		cont++;
		tabla[cont] = im46;
		cont++;
		tabla[cont] = im56;
		cont++;
		tabla[cont] = im66;
		cont++;
		tabla[cont] = im76;
		cont++;
		tabla[cont] = im86;
		cont++;
		tabla[cont] = im96;
		cont++;
		tabla[cont] = im106;
		cont++;
		tabla[cont] = im17;
		cont++;
		tabla[cont] = im27;
		cont++;
		tabla[cont] = im37;
		cont++;
		tabla[cont] = im47;
		cont++;
		tabla[cont] = im57;
		cont++;
		tabla[cont] = im67;
		cont++;
		tabla[cont] = im77;
		cont++;
		tabla[cont] = im87;
		cont++;
		tabla[cont] = im97;
		cont++;
		tabla[cont] = im107;
		cont++;

		final int col0 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt10", null, MainActivity.this.getPackageName());
		final int col1 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt1", null, MainActivity.this.getPackageName());
		final int col2 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt2", null, MainActivity.this.getPackageName());
		final int col3 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt3", null, MainActivity.this.getPackageName());
		final int col4 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt4", null, MainActivity.this.getPackageName());
		final int col5 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt5", null, MainActivity.this.getPackageName());
		final int col6 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt6", null, MainActivity.this.getPackageName());
		final int col7 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt7", null, MainActivity.this.getPackageName());
		final int col8 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt8", null, MainActivity.this.getPackageName());
		final int col9 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt9", null, MainActivity.this.getPackageName());

		debug.setText(random + "");
		btn1.setEnabled(false);

		findViewById(R.id.btn1).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				contpos = 0;
				NetejarIn();
				numin = cnum.getText() + "";
				cnum.setText("");
				btn1.setText("Comprobar");

				if (MM.CompNumUse(numin)) {
					MM.fceroun(numin, random, intents, num_intro, num_resul);
				} else {
					contpos = 0;
					cnum.setText("");
					NetejarIn();

				}
				// ***************************************************************
				if (repetir == false) {
					// Dins del Boto
					String numusuari = numin;
					cnum.setText("");

					// ***********************Variables del
					// programa*****************************************************
					boolean boolpersortir = true, correcte = false, nivell = true, comprovar = false;
					String numrandom = "";

					numrandom = String.valueOf(random);
					// *****************************************************************************

					// ***************Dins el codi del boto****************
					// if (nivell.isSelected()) {
					// nivell = false;
					// } else {
					// nivell = true;
					// }

					// ***********************************************************************************************
					// do {
					// System.out.println("Antes del if");

					if (MM.CompNumUse(numusuari)) {
						// System.out.println("dins del if");

						if (numrandom.equals(numusuari)) {
							boolpersortir = false;
							guanyador = true;
							comprovar = true;
							// break;
						} else {
							// System.out.println("dins del else");

							MM.fceroun(numusuari, numrandom, intents,
									num_intro, num_resul);

							ActivarLayouts(intents, numusuari);
							resultats(num_resul, intents);
							intents++;

							if (intents >= maxintents) {
								boolpersortir = false;
								comprovar = true;
							}
						}
						// System.out
						// .println("Sortim de dins dels if else anidats, el nombre de intents es: "+
						// intents);

						// ******************* IMPRIMIM
						// ***********************************
						area.setText(area.getText()
								+ "\n"
								+ MM.Imprimir(numrandom, intents, contador1,
										contador2, contador0, num_resul,
										num_intro, nivell));
						cintents.setText("Intentos: " + intents + "/7");

					} else {
						// Condicio per el nombre no es correcte
						// no has introduit un nombre correcte
						// System.out.println("dins del else final");
						// cnum.setText("\n" + "No es un número correcto");
					}

					// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
					// } while (boolpersortir = true);

					if (comprovar) {
						// Condicions de guanyar o perdre
						if (guanyador == true) {
							// System.out.println("Enhorabona has guanyat");
							area.setText("Enhorabuena has ganado");
							ganado.setVisibility(View.VISIBLE);
							repetir = true;
							comprovar = false;
							guanyador = false;
							btn1.setText("Volver a jugar");
							sound.play(guanyar);
						} else {
							// System.out.println("Has perdut, el nombre era: "+
							// numrandom);
							perdido.setVisibility(View.VISIBLE);
							area.setText(area.getText() + "\n\n"
									+ "Has perdido, el número era: "
									+ numrandom);
							repetir = true;

							btn1.setText("Volver a jugar");
							sound.play(perdrer);
						}
					}
				} else {
					repetir = false;
					random = MM.Mcrearnum() + "";
					debug.setText(random + "");
					intents = 1;
					contador1 = 0;
					contador2 = 0;
					contador0 = 0;
					perdido.setVisibility(View.INVISIBLE);
					ganado.setVisibility(View.INVISIBLE);
					area.setText("");
					cintents.setText("Intentos: " + intents + "/7");
					int no = MainActivity.this.getResources().getIdentifier(
							"@Colors/#000000", null,
							MainActivity.this.getPackageName());

					for (int i = 0; i < 70; i++) {
						tabla[i].setImageResource(no);
					}
					// System.out.println("Entram per reiniciar tot");

				}

				// ***************************************************************

			}

			private void resultats(String[] num_resul, int intents) {
				int valor = 0;

				switch (intents) {
				case 1:
					valor = 5;
					Colorsresul(num_resul, valor, intents);

					break;

				case 2:
					valor = 15;
					Colorsresul(num_resul, valor, intents);
					break;

				case 3:
					valor = 25;
					Colorsresul(num_resul, valor, intents);
					break;

				case 4:
					valor = 35;
					Colorsresul(num_resul, valor, intents);
					break;

				case 5:
					valor = 45;
					Colorsresul(num_resul, valor, intents);
					break;

				case 6:
					valor = 55;
					Colorsresul(num_resul, valor, intents);
					break;
				case 7:
					valor = 65;
					Colorsresul(num_resul, valor, intents);
					break;
				}

			}

			private void Colorsresul(String[] num_resul, int valor, int intents) {
				// te que sortir de dins
				// ***********************************************************
				int si = MainActivity.this.getResources().getIdentifier(
						"@drawable/si", null,
						MainActivity.this.getPackageName());
				int no = MainActivity.this.getResources().getIdentifier(
						"@drawable/no", null,
						MainActivity.this.getPackageName());
				int sino = MainActivity.this.getResources().getIdentifier(
						"@drawable/sino", null,
						MainActivity.this.getPackageName());
				// ##################################################################################
				int num = 0;

				// amb aixo feim que pugui entrar a la posicio que toca de la
				// tabla num_resul
				switch (valor) {
				case 5:
					num = 0;
					break;
				case 15:
					num = 1;
					break;
				case 25:
					num = 2;
					break;
				case 35:
					num = 3;
					break;
				case 45:
					num = 4;
					break;
				case 55:
					num = 5;
					break;
				case 65:
					num = 6;
					break;
				}
				// ####################
				String a = num_resul[num];

				char b = ' ';
				for (int i = 0; i < 5; i++) {
					b = a.charAt(i);

					switch (b) {
					case '1':
						tabla[valor + i].setImageResource(si);
						break;
					case '2':
						tabla[valor + i].setImageResource(sino);
						break;
					case '0':
						tabla[valor + i].setImageResource(no);
						break;

					}
				}

			}

			private void ActivarLayouts(int intents, String numusuari) {
				int valor = 0;
				switch (intents) {
				case 1:
					valor = 0;
					Colors(numusuari, valor);

					break;

				case 2:
					valor = 10;
					Colors(numusuari, valor);
					break;

				case 3:
					valor = 20;
					Colors(numusuari, valor);
					break;

				case 4:
					valor = 30;
					Colors(numusuari, valor);
					break;

				case 5:
					valor = 40;
					Colors(numusuari, valor);
					break;

				case 6:
					valor = 50;
					Colors(numusuari, valor);
					break;
				case 7:
					valor = 60;
					Colors(numusuari, valor);
					break;
				}

			}

			private void Colors(String numusuari, int valor) {
				// te que sortir de dins
				// ***********************************************************
				// int col0 =
				// MainActivity.this.getResources().getIdentifier("@drawable/bt10",
				// null,MainActivity.this.getPackageName());
				// int col1 =
				// MainActivity.this.getResources().getIdentifier("@drawable/bt1",
				// null,MainActivity.this.getPackageName());
				// int col2 =
				// MainActivity.this.getResources().getIdentifier("@drawable/bt2",
				// null,MainActivity.this.getPackageName());
				// int col3 =
				// MainActivity.this.getResources().getIdentifier("@drawable/bt3",
				// null,MainActivity.this.getPackageName());
				// int col4 =
				// MainActivity.this.getResources().getIdentifier("@drawable/bt4",
				// null,MainActivity.this.getPackageName());
				// int col5 =
				// MainActivity.this.getResources().getIdentifier("@drawable/bt5",
				// null,MainActivity.this.getPackageName());
				// int col6 =
				// MainActivity.this.getResources().getIdentifier("@drawable/bt6",
				// null,MainActivity.this.getPackageName());
				// int col7 =
				// MainActivity.this.getResources().getIdentifier("@drawable/bt7",
				// null,MainActivity.this.getPackageName());
				// int col8 =
				// MainActivity.this.getResources().getIdentifier("@drawable/bt8",
				// null,MainActivity.this.getPackageName());
				// int col9 =
				// MainActivity.this.getResources().getIdentifier("@drawable/bt9",
				// null,MainActivity.this.getPackageName());
				// ##################################################################################

				char a = ' ';
				for (int i = 0; i < 5; i++) {
					a = numusuari.charAt(i);

					switch (a) {
					case '1':
						tabla[valor + i].setImageResource(col1);
						break;
					case '2':
						tabla[valor + i].setImageResource(col2);
						break;
					case '3':
						tabla[valor + i].setImageResource(col3);
						break;
					case '4':
						tabla[valor + i].setImageResource(col4);
						break;
					case '5':
						tabla[valor + i].setImageResource(col5);
						break;
					case '6':
						tabla[valor + i].setImageResource(col6);
						break;
					case '7':
						tabla[valor + i].setImageResource(col7);
						break;
					case '8':
						tabla[valor + i].setImageResource(col8);
						break;
					case '9':
						tabla[valor + i].setImageResource(col9);
						break;
					case '0':
						tabla[valor + i].setImageResource(col0);
						break;

					}
				}

			}
		});

		findViewById(R.id.borrar).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				// Button borrar = (Button) arg0;
				// contpos=0;
				// cnum.setText("");
				// NetejarIn();

				int negre = MainActivity.this.getResources().getIdentifier(
						"@drawable/no", null,
						MainActivity.this.getPackageName());
				String cad = "";
				Button borrar = (Button) arg0;

				if (contpos == 0) {

				} else {

					for (int i = 1; i <= contpos - 1; i++) {
						// cad=cnum.getText().charAt(i-1)+cad;
						cad = cad + cnum.getText().charAt(i - 1);
					}
					cnum.setText(cad);

					switch (contpos) {
					case 1:
						in1.setImageResource(negre);
						break;
					case 2:
						in2.setImageResource(negre);
						break;
					case 3:
						in3.setImageResource(negre);
						break;
					case 4:
						in4.setImageResource(negre);
						break;
					case 5:
						in5.setImageResource(negre);
						break;
					}

					contpos--;
					cad = "";
				}

			}

		});
		// private void NetejarIn() {
		// int negre =
		// MainActivity.this.getResources().getIdentifier("@drawable/no",
		// null,MainActivity.this.getPackageName());
		// //##################################################################################
		//
		// in1.setImageResource(negre);
		// in2.setImageResource(negre);
		// in3.setImageResource(negre);
		// in4.setImageResource(negre);
		// in5.setImageResource(negre);
		//
		// }
		// *****************************************************************
		findViewById(R.id.bt1).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				contpos++;
				if (contpos >= 0 && contpos <= 5) {
					String cadenaprova = "";
					ImageButton bt1 = (ImageButton) arg0;
					cadenaprova = cnum.getText() + "1";
					cnum.setText(cadenaprova);
					btn1.setEnabled(true);
					btn1.setEnabled(true);
					Insertar(contpos, 1);
				} else {
					contpos = 4;
				}

			}
		});
		// #################################################################

		// *****************************************************************
		findViewById(R.id.bt2).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				contpos++;
				if (contpos >= 0 && contpos <= 5) {
					String cadenaprova = "";
					ImageButton bt2 = (ImageButton) arg0;
					cadenaprova = cnum.getText() + "2";
					cnum.setText(cadenaprova);
					btn1.setEnabled(true);
					Insertar(contpos, 2);
				}
			}
		});
		// #################################################################

		// *****************************************************************
		findViewById(R.id.bt3).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				contpos++;
				if (contpos >= 0 && contpos <= 5) {
					String cadenaprova = "";
					ImageButton bt3 = (ImageButton) arg0;
					cadenaprova = cnum.getText() + "3";
					cnum.setText(cadenaprova);
					btn1.setEnabled(true);
					Insertar(contpos, 3);
				}
			}
		});
		// #################################################################

		// *****************************************************************
		findViewById(R.id.bt4).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				contpos++;
				if (contpos >= 0 && contpos <= 5) {
					String cadenaprova = "";
					ImageButton bt4 = (ImageButton) arg0;
					cadenaprova = cnum.getText() + "4";
					cnum.setText(cadenaprova);
					btn1.setEnabled(true);
					Insertar(contpos, 4);
				}
			}
		});
		// #################################################################

		// *****************************************************************
		findViewById(R.id.bt5).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				contpos++;
				if (contpos >= 0 && contpos <= 5) {
					String cadenaprova = "";
					ImageButton bt5 = (ImageButton) arg0;
					cadenaprova = cnum.getText() + "5";
					cnum.setText(cadenaprova);
					btn1.setEnabled(true);
					Insertar(contpos, 5);
				}
			}
		});
		// #################################################################

		// *****************************************************************
		findViewById(R.id.bt6).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				contpos++;
				if (contpos >= 0 && contpos <= 5) {
					String cadenaprova = "";
					ImageButton bt6 = (ImageButton) arg0;
					cadenaprova = cnum.getText() + "6";
					cnum.setText(cadenaprova);
					btn1.setEnabled(true);
					Insertar(contpos, 6);
				}
			}
		});
		// #################################################################

		// *****************************************************************
		findViewById(R.id.bt7).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				contpos++;
				if (contpos >= 0 && contpos <= 5) {
					String cadenaprova = "";
					ImageButton bt7 = (ImageButton) arg0;
					cadenaprova = cnum.getText() + "7";
					cnum.setText(cadenaprova);
					btn1.setEnabled(true);
					Insertar(contpos, 7);
				}
			}
		});
		// #################################################################
		// *****************************************************************
		findViewById(R.id.bt8).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				contpos++;
				if (contpos >= 0 && contpos <= 5) {
					String cadenaprova = "";
					ImageButton bt8 = (ImageButton) arg0;
					cadenaprova = cnum.getText() + "8";
					cnum.setText(cadenaprova);
					btn1.setEnabled(true);
					Insertar(contpos, 8);
				}
			}
		});
		// #################################################################
		// *****************************************************************
		findViewById(R.id.bt9).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				contpos++;
				if (contpos >= 0 && contpos <= 5) {
					String cadenaprova = "";
					ImageButton bt9 = (ImageButton) arg0;
					cadenaprova = cnum.getText() + "9";
					cnum.setText(cadenaprova);
					btn1.setEnabled(true);
					Insertar(contpos, 9);
				}
			}
		});
		// #################################################################
		// *****************************************************************
		findViewById(R.id.bt10).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				contpos++;
				if (contpos >= 0 && contpos <= 5) {
					String cadenaprova = "";
					ImageButton bt10 = (ImageButton) arg0;
					cadenaprova = cnum.getText() + "0";
					cnum.setText(cadenaprova);
					btn1.setEnabled(true);
					Insertar(contpos, 0);
				}
			}
		});
		// #################################################################

	}

	protected void NetejarIn() {

		int negre = MainActivity.this.getResources().getIdentifier(
				"@drawable/no", null, MainActivity.this.getPackageName());

		in1.setImageResource(negre);
		in2.setImageResource(negre);
		in3.setImageResource(negre);
		in4.setImageResource(negre);
		in5.setImageResource(negre);

	}

	protected void Insertar(int contpos, int i) {

		in1 = (ImageView) findViewById(R.id.in1);
		in2 = (ImageView) findViewById(R.id.in2);
		in3 = (ImageView) findViewById(R.id.in3);
		in4 = (ImageView) findViewById(R.id.in4);
		in5 = (ImageView) findViewById(R.id.in5);

		ImageView[] tablanuminser = { in1, in2, in3, in4, in5 };

		int col0 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt10", null, MainActivity.this.getPackageName());
		int col1 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt1", null, MainActivity.this.getPackageName());
		int col2 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt2", null, MainActivity.this.getPackageName());
		int col3 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt3", null, MainActivity.this.getPackageName());
		int col4 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt4", null, MainActivity.this.getPackageName());
		int col5 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt5", null, MainActivity.this.getPackageName());
		int col6 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt6", null, MainActivity.this.getPackageName());
		int col7 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt7", null, MainActivity.this.getPackageName());
		int col8 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt8", null, MainActivity.this.getPackageName());
		int col9 = MainActivity.this.getResources().getIdentifier(
				"@drawable/bt9", null, MainActivity.this.getPackageName());

		switch (i) {
		case 1:
			tablanuminser[contpos - 1].setImageResource(col1);
			break;
		case 2:
			tablanuminser[contpos - 1].setImageResource(col2);
			break;
		case 3:
			tablanuminser[contpos - 1].setImageResource(col3);
			break;
		case 4:
			tablanuminser[contpos - 1].setImageResource(col4);
			break;
		case 5:
			tablanuminser[contpos - 1].setImageResource(col5);
			break;
		case 6:
			tablanuminser[contpos - 1].setImageResource(col6);
			break;
		case 7:
			tablanuminser[contpos - 1].setImageResource(col7);
			break;
		case 8:
			tablanuminser[contpos - 1].setImageResource(col8);
			break;
		case 9:
			tablanuminser[contpos - 1].setImageResource(col9);
			break;
		case 0:
			tablanuminser[contpos - 1].setImageResource(col0);
			break;

		}
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.activity_main, menu);
	// return true;
	// }

}

class master {

	public static int Mcrearnum() { // Método que crea el número aleatorio
		int aleatori, ini = 10000, fi = 99999; // Necesitamos que el número
												// sea de 5 cifras (mín y
												// máx)

		Random r1 = new Random();
		aleatori = (r1.nextInt(fi - ini) + ini);

		return aleatori;
	}

	public static void fceroun(String numusuari, String Numrandom, int intents,
			String[] num_intro, String[] num_resul) {

		int[] tresult = new int[5];
		int[] cadcomp = new int[5];
		String cadenaresultat = "";

		// Dins aquest bucle anem possant els uns per indicar que el nombre
		// coincideix
		for (int i = 0; i < 5; i++) {

			if (Numrandom.charAt(i) == numusuari.charAt(i)) {

				tresult[i] = 1;
				cadcomp[i] = 1;

			} else {
				tresult[i] = 0;
				cadcomp[i] = 0;
			}
		}

		// ****************************************

		for (int j = 0; j < numusuari.length(); j++) {
			// condicio per no sobreescriure
			// if (tresult[j] == 1 || tresult[j] == 2) {
			if (tresult[j] == 1) {
				// //System.out.println("El valor es 1");

			} else {
				// amb aixo comprovam que no tracti el nombres que ja han sigut
				// tracatats
				for (int k = 0; k < Numrandom.length(); k++) {

					// if (tresult[k] == 1 || tresult[k] == 2) {
					if (cadcomp[k] == 1 || cadcomp[k] == 2) {
						// System.out
						// .println("el valor es 1 o 2, per tant no es tractara...");
						// comprovar que la taula sigui la correcte
					} else {

						if (numusuari.charAt(j) == Numrandom.charAt(k)) {

							if (cadcomp[k] == 2 || cadcomp[k] == 1) {
								// System.out
								// .println("El camp ja te un 1 o un 2 en la seva posicio");
							} else {
								// System.out
								// .println("No tenia ni un 1 ni un 2 i igualam a 2");
								cadcomp[k] = 2;
								tresult[j] = 2;
							}
						}

					}
				}
			}

		}
		// ****************************************

		for (int w = 0; w < 5; w++) {

			cadenaresultat = cadenaresultat + tresult[w];
		}
		// System.out.println("La comprovacio ha finalitzat");
		num_resul[intents - 1] = cadenaresultat;
		num_intro[intents - 1] = numusuari;

	}

	static boolean CompNumUse(String numusuari) {

		boolean correcte = false;

		if (numusuari.length() == 5) {

			for (int w = 0; w < numusuari.length(); w++) {

				int ascci = (int) numusuari.charAt(w);

				if (ascci < 48 || ascci > 57) {
					correcte = false;
					break;
				}
				correcte = true;
			}

		}

		return correcte;

	}

	static String Imprimir(String numrandom, int intents, int contador1,
			int contador2, int contador0, String[] num_resul,
			String[] num_intro, boolean nivell) {

		// //System.out.println(numrandom);
		// System.out.println("Introduits              Resultats");
		String retorn = "";

		for (int w = 0; w < intents - 1; w++) {

			// System.out.print(num_intro[w] + "   ");
			retorn = num_intro[w] + "                   ";
			if (nivell) {
				// System.out.println(num_resul[w]);
				retorn = retorn + num_resul[w];
			} else {

				for (int i = 0; i < 5; i++) {

					if ((int) num_resul[w].charAt(i) == 48) {
						contador0++;
					}
					if ((int) num_resul[w].charAt(i) == 49) {
						contador1++;
					}
					if ((int) num_resul[w].charAt(i) == 50) {
						contador2++;
					}

				}

				// System.out.println("Tens: " + contador2 + "dos " + contador1
				// + "uns " + contador0 + "ceros");
				retorn = retorn + "Tens: " + contador2 + "dos " + contador1
						+ "uns " + contador0 + "ceros";
				contador0 = 0;
				contador1 = 0;
				contador2 = 0;

			}
		}
		return retorn;
	}
}
