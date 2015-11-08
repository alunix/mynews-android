package com.qchu.feedarticle.wireframe.refreshlistarticle;

import android.app.Activity;
import android.content.Intent;

import com.qchu.feedarticle.common.Wireframe;
import com.qchu.feedarticle.view.detailarticle.DetailArticlePagerActivity;
import com.qchu.feedarticle.presenter.listarticle.ListArticlePresenter;
import com.qchu.feedarticle.presenter.refreshlistarticle.RefreshListArticleWireframeInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quocdungchu on 07/09/15.
 */
public class RefreshListArticleWireframe extends Wireframe
	implements RefreshListArticleWireframeInterface {

	public RefreshListArticleWireframe(Activity fromActivity) {
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
