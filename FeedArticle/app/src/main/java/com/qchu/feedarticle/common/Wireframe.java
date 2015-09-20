package com.qchu.feedarticle.common;

import android.app.Activity;
import android.content.Intent;

import java.lang.ref.WeakReference;

/**
 * Created by quocdungchu on 05/09/15.
 */
public abstract class Wireframe {

	WeakReference<Activity> mFromActivityRef;

	protected Wireframe(Activity fromActivity) {
		mFromActivityRef = new WeakReference<>(fromActivity);
	}

	protected void startActivity(Class<Activity> destinationActivityClass) {
		Activity fromActivity = mFromActivityRef.get();
		if(fromActivity != null) {
			fromActivity.startActivity(new Intent(fromActivity, destinationActivityClass));
		}
	}
}
