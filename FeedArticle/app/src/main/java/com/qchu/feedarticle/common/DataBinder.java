package com.qchu.feedarticle.common;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by quocdungchu on 27/09/15.
 */
public class DataBinder {
	@BindingAdapter({"app:imageUrl","app:error"})
	public static void loadImage(ImageView view, String url, Drawable error) {
		Glide.with(view.getContext()).load(url).error(error).into(view);
	}
}
