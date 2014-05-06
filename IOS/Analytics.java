package apps.sine.appsinedolore;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;

public class Analytics extends Activity {
    
	public Analytics() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onStart() {
	    super.onStart();
	    EasyTracker.getInstance(this).activityStart(this);
	}
    
	@Override
	protected void onStop() {
	    super.onStop();
	    EasyTracker.getInstance(this).activityStop(this);
	}
    
}