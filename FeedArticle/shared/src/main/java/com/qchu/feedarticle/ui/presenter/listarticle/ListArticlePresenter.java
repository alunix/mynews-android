package com.qchu.feedarticle.ui.presenter.listarticle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quocdungchu on 07/10/15.
 */
public class ListArticlePresenter
	implements com.qchu.feedarticle.ui.presenter.common.Presenter, ListArticleUserEventHandler {

	protected List<String> articleIdList = new ArrayList<>();

	final ListArticleUserInterface mListArticleUserInterface;
	final ListArticleWireframeInterface mListArticleWireframeInterface;

	protected ListArticlePresenter(ListArticleUserInterface listArticleUserInterface,
	                            ListArticleWireframeInterface listArticleWireframeInterface){

		mListArticleUserInterface = listArticleUserInterface;
		mListArticleWireframeInterface = listArticleWireframeInterface;
	}

	@Override
	public void onCreate() {

	}

	@Override
	public void onDestroy() {

	}

	@Override
	public void onArticleItemClickEvent(
		ListArticleUserInterface listArticleUserInterface, int position) {

		if(0 <= position && position < articleIdList.size()) {
			mListArticleWireframeInterface.presentDetailArticleUserInterface(
				this, articleIdList, position);
		}
	}
}
