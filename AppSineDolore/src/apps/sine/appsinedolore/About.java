package apps.sine.appsinedolore;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class About extends Analytics {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

	}

}
