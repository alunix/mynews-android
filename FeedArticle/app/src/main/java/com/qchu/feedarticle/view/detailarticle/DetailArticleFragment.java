package com.qchu.feedarticle.view.detailarticle;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.qchu.feedarticle.R;
import com.qchu.feedarticle.common.ParcelableArgsBundler;
import com.qchu.feedarticle.view.detailarticle.databinding.BindableArticle;
import com.qchu.feedarticle.feature.detailarticle.ui.view.databinding.DetailArticleFragmentDataBinding;

/**
 * Created by quocdungchu on 29/09/15.
 */
public class DetailArticleFragment extends Fragment {

	@Arg( bundler = ParcelableArgsBundler.class )
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
		mDetailArticleFragmentDataBinding.webview.setBackgroundColor(Color.TRANSPARENT);

		return mDetailArticleFragmentDataBinding.getRoot();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mDetailArticleFragmentDataBinding.setArticle(mBindableArticle);
	}
}
