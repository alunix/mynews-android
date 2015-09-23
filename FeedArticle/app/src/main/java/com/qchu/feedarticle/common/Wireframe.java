package com.qchu.feedarticle.common;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

/**
 * Created by quocdungchu on 05/09/15.
 */
public abstract class Wireframe {

	WeakReference<AppCompatActivity> mFromActivityRef;

	protected Wireframe(AppCompatActivity fromActivity) {
		mFromActivityRef = new WeakReference<>(fromActivity);
	}

	protected void startActivity(Class<Activity> destinationActivityClass) {
		Activity fromActivity = mFromActivityRef.get();
		if(fromActivity != null) {
			fromActivity.startActivity(new Intent(fromActivity, destinationActivityClass));
		}
	}

	protected AppCompatActivity getFromActivity(){
		return mFromActivityRef.get();
	}
}
