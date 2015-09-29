package com.qchu.feedarticle.feature.detailarticle.ui.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.qchu.feedarticle.FeedArticleConfiguration;
import com.qchu.feedarticle.R;
import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.detailarticle.ui.presenter.DetailArticlePresenter;
import com.qchu.feedarticle.feature.detailarticle.ui.presenter.DetailArticleUserInterface;
import com.qchu.feedarticle.feature.detailarticle.ui.view.databinding.DetailArticleFragmentDataBinding;
import com.qchu.feedarticle.feature.detailarticle.ui.wireframe.DetailArticleWireframe;

import java.util.List;

/**
 * Created by quocdungchu on 27/09/15.
 */
public class DetailArticlePagerActivity extends AppCompatActivity
	implements DetailArticleUserInterface {

	public static final String CURRENT_INDEX = "CURRENT_INDEX";
	public static final String ARTICLE_LIST = "ARTICLE_LIST";

	DetailArticlePresenter mDetailArticlePresenter;
	DetailArticleFragmentDataBinding mDetailArticleFragmentDataBinding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDetailArticleFragmentDataBinding =
			DataBindingUtil.setContentView(this, R.layout.detail_article_pager_activity);
		setSupportActionBar(mDetailArticleFragmentDataBinding.toolbar);

		final ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		mDetailArticlePresenter = DetailArticlePresenter.create(
			FeedArticleConfiguration.get().getArticleInteractor(),
			this,
			new DetailArticleWireframe(this),
			getIntent().getStringArrayListExtra(ARTICLE_LIST),
			getIntent().getIntExtra(CURRENT_INDEX,-1));
	}

	@Override
	protected void onDestroy(){
		super.onDestroy();
		mDetailArticlePresenter.finish();
	}

	@Override
	public void bindArticles(List<Article> articleList) {

	}

	@Override
	public void selectIndex(int selectedIndex, boolean animated) {

	}
}
