package com.qchu.feedarticle.feature.listarticle.ui.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qchu.feedarticle.R;
import com.qchu.feedarticle.feature.listarticle.ui.view.databinding.ListArticleFragmentDataBinding;

/**
 * Created by quocdungchu on 22/09/15.
 */
public class ListArticleFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                            Bundle savedInstanceState){
		ListArticleFragmentDataBinding listArticleFragmentDataBinding =
			DataBindingUtil.inflate(inflater, R.layout.list_article_fragment, null, false);

		return listArticleFragmentDataBinding.getRoot();
	}
}
