package com.qchu.feedarticle.common;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by quocdungchu on 22/09/15.
 */
public class FragmentWireframe extends Wireframe {

	int mFragmentContanterId;

	public FragmentWireframe(AppCompatActivity fromActivity, int fragmentContainterId) {
		super(fromActivity);
		mFragmentContanterId = fragmentContainterId;
	}

	protected void replaceFragment(Fragment fragment){
		AppCompatActivity fromActivity = (AppCompatActivity)getFromActivity();
		if(fromActivity != null) {
			fromActivity.getSupportFragmentManager()
				.beginTransaction()
				.replace(mFragmentContanterId, fragment)
				.commit();
		}
	}

	protected void addFragment(Fragment fragment){
		AppCompatActivity fromActivity = (AppCompatActivity)getFromActivity();
		if(fromActivity != null) {
			fromActivity.getSupportFragmentManager()
				.beginTransaction()
				.add(mFragmentContanterId, fragment)
				.commit();
		}
	}
}
