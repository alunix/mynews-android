package com.qchu.feedarticle.feature.refreshlistarticle.ui.presenter;

import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticleUserInterfaceEventHandler;

/**
 * Created by quocdungchu on 07/09/15.
 */
public interface RefreshListArticleUserInterfaceEventHandler
	extends ListArticleUserInterfaceEventHandler {

	void onSwipeRefreshEvent(RefreshListArticleUserInterface listArticleUserInterface);
}
