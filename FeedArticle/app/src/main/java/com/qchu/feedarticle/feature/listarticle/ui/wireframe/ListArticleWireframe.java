package com.qchu.feedarticle.feature.listarticle.ui.wireframe;

import android.app.Activity;
import android.content.Intent;

import com.qchu.feedarticle.common.Wireframe;
import com.qchu.feedarticle.feature.detailarticle.ui.view.DetailArticlePagerActivity;
import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticlePresenter;
import com.qchu.feedarticle.feature.listarticle.ui.presenter.ListArticleWireframeInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quocdungchu on 07/09/15.
 */
public class ListArticleWireframe extends Wireframe implements ListArticleWireframeInterface{

	public ListArticleWireframe(Activity fromActivity) {
		super(fromActivity);
	}

	@Override
	public void presentDetailArticleUserInterface(ListArticlePresenter listArticlePresenter,
	                                              List<String> articleIdList, int currentIndex) {
		getFromActivity().startActivity(
			new Intent(getFromActivity(), DetailArticlePagerActivity.class)
				.putExtra(DetailArticlePagerActivity.CURRENT_INDEX, currentIndex)
				.putStringArrayListExtra(DetailArticlePagerActivity.ARTICLE_LIST,
					(ArrayList<String>) articleIdList));
	}
}
