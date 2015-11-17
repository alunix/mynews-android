package com.qchu.feedarticle.ui.presenter.refreshlistarticle;

import com.qchu.feedarticle.ui.presenter.listarticle.ListArticleUserEventHandler;

/**
 * Created by quocdungchu on 07/09/15.
 */
public interface RefreshListArticleUserEventHandler
	extends ListArticleUserEventHandler {

	void onSwipeRefreshEvent(RefreshListArticleUserInterface listArticleUserInterface);
}
