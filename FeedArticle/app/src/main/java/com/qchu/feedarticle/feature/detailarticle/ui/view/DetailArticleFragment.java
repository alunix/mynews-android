package com.qchu.feedarticle.feature.detailarticle.ui.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qchu.feedarticle.R;
import com.qchu.feedarticle.databinding.DetailArticleFragmentBinding;
import com.qchu.feedarticle.feature.detailarticle.ui.presenter.DetailArticlePresenter;

/**
 * Created by quocdungchu on 29/09/15.
 */
public class DetailArticleFragment extends Fragment {

	DetailArticleFragmentBinding mDetailArticleFragmentBinding;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState){
		mDetailArticleFragmentBinding =
			DataBindingUtil.inflate(inflater, R.layout.detail_article_fragment, null, false);

		return mDetailArticleFragmentBinding.getRoot();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

	}
}
