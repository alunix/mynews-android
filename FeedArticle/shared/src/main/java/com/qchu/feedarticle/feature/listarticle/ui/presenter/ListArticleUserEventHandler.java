package com.qchu.feedarticle.feature.listarticle.ui.presenter;

import com.qchu.feedarticle.feature.refreshlistarticle.ui.presenter.RefreshListArticleUserInterface;

/**
 * Created by quocdungchu on 07/10/15.
 */
public interface ListArticleUserEventHandler {
	void onArticleItemClickEvent(ListArticleUserInterface listArticleUserInterface, int position);
}
