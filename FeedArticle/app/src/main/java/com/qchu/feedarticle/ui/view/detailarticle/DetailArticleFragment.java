package com.qchu.feedarticle.ui.view.detailarticle;

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
import com.qchu.feedarticle.feature.detailarticle.ui.view.databinding.DetailArticleFragmentDataBinding;
import com.qchu.feedarticle.ui.common.BaseFragment;
import com.qchu.feedarticle.ui.view.detailarticle.databinding.BindableArticle;

/**
 * Created by quocdungchu on 29/09/15.
 */
public class DetailArticleFragment extends BaseFragment {

	@Arg( bundler = ParcelableArgsBundler.class )
	BindableArticle bindableArticle;

	private DetailArticleFragmentDataBinding dataBinding;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		FragmentArgs.inject(this); // read @Arg fields
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState){
		dataBinding = DataBindingUtil.inflate(inflater, R.layout.detail_article_fragment, null, false);

		dataBinding.webview.getSettings().setLoadWithOverviewMode(true);
		dataBinding.webview.setBackgroundColor(Color.TRANSPARENT);

		return dataBinding.getRoot();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		dataBinding.setArticle(bindableArticle);
	}
}
