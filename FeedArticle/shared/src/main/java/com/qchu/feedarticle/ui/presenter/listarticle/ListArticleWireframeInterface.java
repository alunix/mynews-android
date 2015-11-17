package com.qchu.feedarticle.ui.presenter.listarticle;

import java.util.List;

/**
 * Created by quocdungchu on 07/10/15.
 */
public interface ListArticleWireframeInterface {
	void presentDetailArticleUserInterface(ListArticlePresenter listArticlePresenter,
	                                       List<String> articleIdList, int currentIndex);
}
