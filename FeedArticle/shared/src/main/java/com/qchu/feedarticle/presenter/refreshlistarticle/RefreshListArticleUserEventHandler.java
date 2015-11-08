package com.qchu.feedarticle.presenter.refreshlistarticle;

import com.qchu.feedarticle.presenter.listarticle.ListArticleUserEventHandler;

/**
 * Created by quocdungchu on 07/09/15.
 */
public interface RefreshListArticleUserEventHandler
	extends ListArticleUserEventHandler {

	void onSwipeRefreshEvent(RefreshListArticleUserInterface listArticleUserInterface);
}
