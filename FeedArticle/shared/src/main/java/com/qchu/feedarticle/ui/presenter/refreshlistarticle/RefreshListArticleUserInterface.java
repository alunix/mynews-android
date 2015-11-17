package com.qchu.feedarticle.ui.presenter.refreshlistarticle;

/**
 * Created by quocdungchu on 07/09/15.
 */
public interface RefreshListArticleUserInterface extends com.qchu.feedarticle.ui.presenter.listarticle.ListArticleUserInterface {

	void beginSwipeRefreshingLayout(RefreshListArticlePresenter refreshListArticlePresenter);
	void endSwipeRefreshingLayout(RefreshListArticlePresenter refreshListArticlePresenter);
}
