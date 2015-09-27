package com.qchu.feedarticle.feature.detailarticle.ui.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.qchu.feedarticle.R;
import com.qchu.feedarticle.feature.detailarticle.ui.view.databinding.DetailArticleFragmentDataBinding;

/**
 * Created by quocdungchu on 27/09/15.
 */
public class DetailArticleActivity extends AppCompatActivity {

	DetailArticleFragmentDataBinding mDetailArticleFragmentDataBinding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDetailArticleFragmentDataBinding =
			DataBindingUtil.setContentView(this, R.layout.detail_article_activity);
		setSupportActionBar(mDetailArticleFragmentDataBinding.toolbar);

		final ActionBar actionBar = getSupportActionBar();
		actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
}
