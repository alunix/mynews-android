package com.qchu.feedarticle.feature.refreshlistarticle.ui.presenter;

/**
 * Created by quocdungchu on 07/09/15.
 */
public interface RefreshListArticleUserInterfaceEventHandler {
	void onSwipeRefreshEvent(RefreshListArticleUserInterface listArticleUserInterface);
	void onArticleItemClickEvent(RefreshListArticleUserInterface listArticleUserInterface, int position);
}
