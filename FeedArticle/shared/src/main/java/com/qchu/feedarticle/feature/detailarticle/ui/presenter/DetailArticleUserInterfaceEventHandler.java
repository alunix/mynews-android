package com.qchu.feedarticle.feature.detailarticle.ui.presenter;

/**
 * Created by quocdungchu on 27/09/15.
 */
public interface DetailArticleUserInterfaceEventHandler {
	void onPageSelectedEvent(DetailArticleUserInterface detailArticleUserInterface,
	                         int pageIndex);
	void onAddOrRemoveCurrentArticleInFavoriteEvent(
		DetailArticleUserInterface detailArticleUserInterface);
	void onShareCurrentArticleEvent(DetailArticlePresenter detailArticlePresenter);
}
