package com.qchu.feedarticle.feature.favoritelistarticle.ui.presenter;

import com.qchu.feedarticle.common.Presenter;
import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticlePresenter;
import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticleUserInterface;
import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticleUserInterfaceEventHandler;
import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticleWireframeInterface;
import com.qchu.feedarticle.feature.refreshlistarticle.ui.presenter.RefreshListArticleUserInterface;

/**
 * Created by quocdungchu on 07/10/15.
 */
public class FavoriteListArticlePresenter extends ListArticlePresenter {

	protected FavoriteListArticlePresenter(
		ListArticleUserInterface listArticleUserInterface,
		ListArticleWireframeInterface listArticleWireframeInterface) {

		super(listArticleUserInterface, listArticleWireframeInterface);
	}

	@Override
	protected void onCreate() {

	}

	@Override
	protected void onDestroy() {

	}
}
