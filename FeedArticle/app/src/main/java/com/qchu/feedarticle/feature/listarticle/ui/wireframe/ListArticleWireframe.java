package com.qchu.feedarticle.feature.listarticle.ui.wireframe;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.qchu.feedarticle.common.Wireframe;
import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.detailarticle.ui.view.DetailArticleActivity;
import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticlePresenter;
import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticleWireframeInterface;

/**
 * Created by quocdungchu on 07/09/15.
 */
public class ListArticleWireframe extends Wireframe implements ListArticleWireframeInterface{

	public ListArticleWireframe(Activity fromActivity) {
		super(fromActivity);
	}

	@Override
	public void presentDetailArticleUserInterface(
		ListArticlePresenter listArticlePresenter, Article article) {

		startActivity(DetailArticleActivity.class);
	}
}
