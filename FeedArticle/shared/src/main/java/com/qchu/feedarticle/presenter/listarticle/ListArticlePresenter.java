package com.qchu.feedarticle.presenter.listarticle;

import com.qchu.feedarticle.common.Presenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quocdungchu on 07/10/15.
 */
public class ListArticlePresenter
	implements Presenter, ListArticleUserEventHandler {

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
