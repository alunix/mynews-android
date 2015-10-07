package com.qchu.feedarticle.feature.refreshlistarticle.ui.presenter;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticleUserInterface;

import java.util.List;

/**
 * Created by quocdungchu on 07/09/15.
 */
public interface RefreshListArticleUserInterface extends ListArticleUserInterface {
	/*
	void addArticleItemAtIndex(ListArticlePresenter listArticlePresenter,
	                           Article article, int itemIndex);
	void updateArticleItemAtIndex(ListArticlePresenter listArticlePresenter,
	                              Article article, int itemIndex);
	void removeArticleItemAtIndex(ListArticlePresenter listArticlePresenter,
	                              Article article, int itemIndex);
	                              */
	void beginSwipeRefreshingLayout(RefreshRefreshListArticlePresenter refreshListArticlePresenter);
	void endSwipeRefreshingLayout(RefreshRefreshListArticlePresenter refreshListArticlePresenter);
}
