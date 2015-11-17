package com.qchu.feedarticle.ui.presenter.favoritelistarticle;

/**
 * Created by quocdungchu on 19/10/15.
 */
public interface FavoriteListArticleUserInterface extends com.qchu.feedarticle.ui.presenter.listarticle.ListArticleUserInterface {
	void updateWithEditState(FavoriteListArticlePresenter.EditState editState);
}
