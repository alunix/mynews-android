package com.qchu.feedarticle.ui.presenter.favoritelistarticle;

import com.qchu.feedarticle.ui.presenter.listarticle.ListArticleUserInterface;

/**
 * Created by quocdungchu on 19/10/15.
 */
public interface FavoriteListArticleUserInterface extends ListArticleUserInterface {
	void updateWithEditState(FavoriteListArticlePresenter.EditState editState);
}
