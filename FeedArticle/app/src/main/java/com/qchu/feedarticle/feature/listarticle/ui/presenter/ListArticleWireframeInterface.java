package com.qchu.feedarticle.feature.listarticle.ui.presenter;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;

/**
 * Created by quocdungchu on 26/09/15.
 */
public interface ListArticleWireframeInterface {
	void presentDetailArticleUserInterface(ListArticlePresenter listArticlePresenter,
	                                       Article article);
}
