package com.qchu.feedarticle.feature.listarticle.ui.presenter;

import com.qchu.feedarticle.common.Presenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quocdungchu on 07/10/15.
 */
public class ListArticlePresenter extends Presenter
	implements ListArticleUserEventHandler {

	protected List<String> articleIdList = new ArrayList<>();

	final ListArticleUserInterface mListArticleUserInterface;
	final ListArticleWireframeInterface mListArticleWireframeInterface;

	protected ListArticlePresenter(ListArticleUserInterface listArticleUserInterface,
	                            ListArticleWireframeInterface listArticleWireframeInterface){

		mListArticleUserInterface = listArticleUserInterface;
		mListArticleWireframeInterface = listArticleWireframeInterface;
	}

	@Override
	protected void onCreate() {

	}

	@Override
	protected void onDestroy() {

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
