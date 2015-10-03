package com.qchu.feedarticle.feature.detailarticle.ui.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.bundler.ParcelerArgsBundler;
import com.qchu.feedarticle.R;
import com.qchu.feedarticle.feature.detailarticle.ui.view.databinding.BindableArticle;
import com.qchu.feedarticle.feature.detailarticle.ui.view.databinding.DetailArticleFragmentDataBinding;

import org.parceler.Parcels;

/**
 * Created by quocdungchu on 29/09/15.
 */
public class DetailArticleFragment extends Fragment {

	@Arg( bundler = ParcelerArgsBundler.class )
	BindableArticle mBindableArticle;

	DetailArticleFragmentDataBinding mDetailArticleFragmentDataBinding;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		FragmentArgs.inject(this); // read @Arg fields
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState){
		mDetailArticleFragmentDataBinding =
			DataBindingUtil.inflate(inflater, R.layout.detail_article_fragment, null, false);

		mDetailArticleFragmentDataBinding.webview.getSettings().setLoadWithOverviewMode(true);
		return mDetailArticleFragmentDataBinding.getRoot();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mDetailArticleFragmentDataBinding.setArticle(mBindableArticle);
	}
}
