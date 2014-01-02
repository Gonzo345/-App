package aplicacions.acj.linterna;

import net.androidsensei.senseilight.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class Main extends Activity {

	ImageButton btnSwitch, btintensitat;

	private Camera camera;
	private boolean isFlashOn;
	private boolean hasFlash;
	Parameters params;
	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnSwitch = (ImageButton) findViewById(R.id.btnSwitch);
//		btintensitat = (ImageButton) findViewById(R.id.btintensitat);

		hasFlash = getApplicationContext().getPackageManager()
				.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

		if (!hasFlash) {
			AlertDialog alert = new AlertDialog.Builder(Main.this).create();
			alert.setTitle("Error");
			alert.setMessage("Sorry, your device doesn't support flash light!");
			alert.setButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// closing the application
					finish();
				}
			});
			alert.show();
			return;
		}

		getCamera();

		toggleButtonImage();

		btnSwitch.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (isFlashOn) {
					turnOffFlash();
				} else {
					turnOnFlash();
				}

			}
		});

//		btintensitat.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				toggleButtonImage1();
//				if (isFlashOn) {
//					if (params.getFlashMode() == Parameters.FLASH_MODE_TORCH) {
//						params = camera.getParameters();
//						params.setFlashMode(Parameters.FLASH_MODE_RED_EYE);
//						camera.setParameters(params);
//						camera.stopPreview();
//					} else {
//						params = camera.getParameters();
//						params.setFlashMode(Parameters.FLASH_MODE_TORCH);
//						camera.setParameters(params);
//						camera.stopPreview();
//					}
//				}
//			}
//		});

	}

	private void getCamera() {
		if (camera == null) {
			try {
				camera = Camera.open();
				params = camera.getParameters();
			} catch (RuntimeException e) {
				Log.e("Camera Error. Failed to Open. Error: ", e.getMessage());
			}
		}
	}

	private void turnOnFlash() {
		if (!isFlashOn) {
			if (camera == null || params == null) {
				return;
			}

			playSound();

			params = camera.getParameters();
			params.setFlashMode(Parameters.FLASH_MODE_TORCH);
			camera.setParameters(params);
			camera.startPreview();
			isFlashOn = true;

			toggleButtonImage();
		}

	}

	private void turnOffFlash() {
		if (isFlashOn) {
			if (camera == null || params == null) {
				return;
			}

			playSound();

			params = camera.getParameters();
			params.setFlashMode(Parameters.FLASH_MODE_OFF);
			camera.setParameters(params);
			camera.stopPreview();
			isFlashOn = false;

			toggleButtonImage();
		}
	}

	private void playSound() {
		if (isFlashOn) {
			mp = MediaPlayer.create(Main.this, R.raw.light_switch_off);
		} else {
			mp = MediaPlayer.create(Main.this, R.raw.light_switch_on);
		}
		mp.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {

				mp.release();
			}
		});
		mp.start();
	}

	private void toggleButtonImage() {
		if (isFlashOn) {
			btnSwitch.setImageResource(R.drawable.btn_switch_on);
		} else {
			btnSwitch.setImageResource(R.drawable.btn_switch_off);
		}
	}	
	private void toggleButtonImage1() {
		if (isFlashOn) {
			btintensitat.setImageResource(R.drawable.btn_switch_on);
		} else {
			btintensitat.setImageResource(R.drawable.btn_switch_off);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();

		turnOffFlash();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (hasFlash)
			turnOnFlash();
	}

	@Override
	protected void onStart() {
		super.onStart();

		getCamera();
	}

	@Override
	protected void onStop() {
		super.onStop();

		if (camera != null) {
			camera.release();
			camera = null;
		}
	}

}
