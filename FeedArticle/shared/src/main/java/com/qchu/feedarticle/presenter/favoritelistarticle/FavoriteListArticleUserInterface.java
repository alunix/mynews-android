package com.qchu.feedarticle.presenter.favoritelistarticle;

import com.qchu.feedarticle.presenter.listarticle.ListArticleUserInterface;

/**
 * Created by quocdungchu on 19/10/15.
 */
public interface FavoriteListArticleUserInterface extends ListArticleUserInterface{
	void updateWithEditState(com.qchu.feedarticle.presenter.favoritelistarticle.FavoriteListArticlePresenter.EditState editState);
}
