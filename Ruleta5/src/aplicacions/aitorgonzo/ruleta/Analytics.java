package aplicacions.aitorgonzo.ruleta;

import android.app.Activity;

import com.google.analytics.tracking.android.EasyTracker;

public class Analytics extends Activity{

	@Override
	public void onStart() {
	    super.onStart();
	    EasyTracker.getInstance(this).activityStart(this);
	}

	@Override
	public void onStop() {
	    super.onStop();
	    EasyTracker.getInstance(this).activityStop(this);
	}
}
