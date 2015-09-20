package com.qchu.feedarticle.feature.listarticle.ui.presenter;

import com.qchu.feedarticle.common.Presenter;
import com.qchu.feedarticle.feature.listarticle.ui.wireframe.ListArticleWireframe;

/**
 * Created by quocdungchu on 07/09/15.
 */
public class ListArticlePresenter extends Presenter
	implements ListArticleUserInterfaceEventHandler {

	ListArticleUserInterface mListArticleUserInterface;
	ListArticleWireframe mListArticleWireframe;

	public static ListArticlePresenter create(ListArticleUserInterface listArticleUserInterface,
	                                          ListArticleWireframe listArticleWireframe) {

		ListArticlePresenter listArticlePresenter = new ListArticlePresenter();
		listArticlePresenter.mListArticleUserInterface = listArticleUserInterface;
		listArticlePresenter.mListArticleWireframe = listArticleWireframe;

		listArticlePresenter.onCreate();

		return listArticlePresenter;
	}

	ListArticlePresenter(){}

	@Override
	protected void onCreate() {

	}

	@Override
	protected void onDestroy() {

	}
}
