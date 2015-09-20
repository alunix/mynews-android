package com.qchu.feedarticle.common;

/**
 * Created by quocdungchu on 22/08/15.
 */
public abstract class Presenter {

	public final void finish() {
		onDestroy();
	}

	protected abstract void onCreate();
	protected abstract void onDestroy();
}
