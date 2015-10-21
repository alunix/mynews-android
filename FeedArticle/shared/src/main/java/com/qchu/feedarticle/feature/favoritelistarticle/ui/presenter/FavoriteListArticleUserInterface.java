package com.qchu.feedarticle.feature.favoritelistarticle.ui.presenter;

import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticleUserInterface;

/**
 * Created by quocdungchu on 19/10/15.
 */
public interface FavoriteListArticleUserInterface extends ListArticleUserInterface{
	void updateWithEditState(FavoriteListArticlePresenter.EditState editState);
}
