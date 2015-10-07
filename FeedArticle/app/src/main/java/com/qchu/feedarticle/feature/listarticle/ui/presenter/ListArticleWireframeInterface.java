package com.qchu.feedarticle.feature.listarticle.ui.presenter;

import com.qchu.feedarticle.feature.refreshlistarticle.ui.presenter.RefreshRefreshListArticlePresenter;

import java.util.List;

/**
 * Created by quocdungchu on 07/10/15.
 */
public interface ListArticleWireframeInterface {
	void presentDetailArticleUserInterface(RefreshRefreshListArticlePresenter refreshListArticlePresenter,
	                                       List<String> articleIdList, int currentIndex);
}
