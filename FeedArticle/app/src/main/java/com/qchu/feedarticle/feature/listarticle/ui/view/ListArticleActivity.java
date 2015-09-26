package com.qchu.feedarticle.feature.listarticle.ui.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qchu.feedarticle.FeedArticleConfiguration;
import com.qchu.feedarticle.R;
import com.qchu.feedarticle.feature.listarticle.applogic.entity.Article;
import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticlePresenter;
import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticleUserInterface;
import com.qchu.feedarticle.feature.listarticle.ui.wireframe.ListArticleWireframe;

import java.util.List;

/**
 * Created by quocdungchu on 07/09/15.
 */
public class ListArticleActivity extends AppCompatActivity implements ListArticleUserInterface {


	ListArticlePresenter mListArticlePresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_article_activity);

		mListArticlePresenter = ListArticlePresenter.create(
			FeedArticleConfiguration.get().getArticleInteractor(), this, new ListArticleWireframe(this));
	}

	@Override
	protected void onDestroy(){
		super.onDestroy();
		mListArticlePresenter.finish();
	}

	@Override
	public void bindArticles(List<Article> articleList) {

	}
}
