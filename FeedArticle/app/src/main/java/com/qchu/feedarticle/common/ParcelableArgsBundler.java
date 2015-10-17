package com.qchu.feedarticle.common;

import android.os.Bundle;
import android.os.Parcelable;

import com.hannesdorfmann.fragmentargs.bundler.ArgsBundler;

import org.parceler.Parcels;

import java.util.Objects;

/**
 * Created by quocdungchu on 17/10/15.
 */
public class ParcelableArgsBundler implements ArgsBundler<Object> {
	@Override
	public void put(String key, Object value, Bundle bundle) {
		bundle.putParcelable(key, (Parcelable) value);
	}

	@Override
	public <V> V  get(String key, Bundle bundle) {
		return bundle.getParcelable(key);
	}

}
