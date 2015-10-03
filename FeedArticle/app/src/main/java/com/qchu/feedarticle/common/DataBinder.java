package com.qchu.feedarticle.common;

import android.app.Activity;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qchu.feedarticle.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by quocdungchu on 27/09/15.
 */
public class DataBinder {
	@BindingAdapter({"app:imageUrl","app:error"})
	public static void loadImage(ImageView view, String url, Drawable error) {
		Glide.with(view.getContext()).load(url).error(error).into(view);
	}

	@BindingAdapter({"app:html"})
	public static void loadHtmlString(WebView webView, String htmlString){

		webView.loadDataWithBaseURL(null,
			String.format(AssetTool.stringFromAssetFilePath(
					webView.getContext(),"article_html_template"), htmlString)
			, "text/html", "UTF-8", null);
	}
	
}
