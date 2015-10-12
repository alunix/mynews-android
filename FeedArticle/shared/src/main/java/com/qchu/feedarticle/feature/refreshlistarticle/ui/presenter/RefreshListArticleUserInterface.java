package com.qchu.feedarticle.feature.refreshlistarticle.ui.presenter;

import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticleUserInterface;

/**
 * Created by quocdungchu on 07/09/15.
 */
public interface RefreshListArticleUserInterface extends ListArticleUserInterface {

	void beginSwipeRefreshingLayout(RefreshListArticlePresenter refreshListArticlePresenter);
	void endSwipeRefreshingLayout(RefreshListArticlePresenter refreshListArticlePresenter);
}
