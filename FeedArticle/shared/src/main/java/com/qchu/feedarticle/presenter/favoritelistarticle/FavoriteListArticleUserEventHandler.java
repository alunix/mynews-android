package com.qchu.feedarticle.presenter.favoritelistarticle;

/**
 * Created by quocdungchu on 19/10/15.
 */
public interface FavoriteListArticleUserEventHandler {
	void onEditEventClick(FavoriteListArticleUserInterface favoriteListArticleUserInterface);
	void onRemoveArticleAtIndex(FavoriteListArticleUserInterface favoriteListArticleUserInterface,
	                            int removeAtIndex);
}
