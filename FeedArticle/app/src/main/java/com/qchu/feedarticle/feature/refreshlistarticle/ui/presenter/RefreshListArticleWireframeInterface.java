package com.qchu.feedarticle.feature.refreshlistarticle.ui.presenter;

import java.util.List;

/**
 * Created by quocdungchu on 26/09/15.
 */
public interface RefreshListArticleWireframeInterface {
	void presentDetailArticleUserInterface(RefreshRefreshListArticlePresenter refreshListArticlePresenter,
	                                       List<String> articleIdList, int currentIndex);
}
