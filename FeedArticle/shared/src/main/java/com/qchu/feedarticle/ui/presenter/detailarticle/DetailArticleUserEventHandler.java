package com.qchu.feedarticle.ui.presenter.detailarticle;

/**
 * Created by quocdungchu on 27/09/15.
 */
public interface DetailArticleUserEventHandler {
	void onPageSelectedEvent(DetailArticleUserInterface detailArticleUserInterface,
	                         int pageIndex);
	void onAddOrRemoveCurrentArticleInFavoriteEvent(
		DetailArticleUserInterface detailArticleUserInterface);
	void onShareCurrentArticleEvent(DetailArticlePresenter detailArticlePresenter);
}
