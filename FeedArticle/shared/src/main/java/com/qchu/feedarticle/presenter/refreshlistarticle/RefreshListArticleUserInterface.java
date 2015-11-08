package com.qchu.feedarticle.presenter.refreshlistarticle;

import com.qchu.feedarticle.presenter.listarticle.ListArticleUserInterface;

/**
 * Created by quocdungchu on 07/09/15.
 */
public interface RefreshListArticleUserInterface extends ListArticleUserInterface {

	void beginSwipeRefreshingLayout(RefreshListArticlePresenter refreshListArticlePresenter);
	void endSwipeRefreshingLayout(RefreshListArticlePresenter refreshListArticlePresenter);
}
