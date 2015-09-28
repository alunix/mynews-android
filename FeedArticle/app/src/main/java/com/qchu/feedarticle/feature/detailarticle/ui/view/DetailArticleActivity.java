package com.qchu.feedarticle.feature.detailarticle.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.qchu.feedarticle.R;
import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.detailarticle.ui.presenter.DetailArticleUserInterface;
import com.qchu.feedarticle.feature.detailarticle.ui.view.databinding.DetailArticleFragmentDataBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quocdungchu on 27/09/15.
 */
public class DetailArticleActivity extends AppCompatActivity implements DetailArticleUserInterface {

	public static final String CURRENT_INDEX = "CURRENT_INDEX";
	public static final String ARTICLE_LIST = "ARTICLE_LIST";


	DetailArticleFragmentDataBinding mDetailArticleFragmentDataBinding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDetailArticleFragmentDataBinding =
			DataBindingUtil.setContentView(this, R.layout.detail_article_activity);
		setSupportActionBar(mDetailArticleFragmentDataBinding.toolbar);

		final ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public void bindArticles(List<Article> articleList) {

	}

	@Override
	public void selectIndex(int selectedIndex, boolean animated) {

	}
}
