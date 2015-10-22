package com.qchu.feedarticle.feature.refreshlistarticle.ui.presenter;

import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticleUserEventHandler;

/**
 * Created by quocdungchu on 07/09/15.
 */
public interface RefreshListArticleUserEventHandler
	extends ListArticleUserEventHandler {

	void onSwipeRefreshEvent(RefreshListArticleUserInterface listArticleUserInterface);
}
