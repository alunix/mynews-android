package com.qchu.feedarticle.feature.detailarticle.ui.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qchu.feedarticle.R;
import com.qchu.feedarticle.feature.detailarticle.ui.view.databinding.DetailArticleFragmentDataBinding;

/**
 * Created by quocdungchu on 29/09/15.
 */
public class DetailArticleFragment extends Fragment {

	DetailArticleFragmentDataBinding mDetailArticleFragmentDataBinding;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState){
		mDetailArticleFragmentDataBinding =
			DataBindingUtil.inflate(inflater, R.layout.detail_article_fragment, null, false);

		return mDetailArticleFragmentDataBinding.getRoot();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

	}
}
