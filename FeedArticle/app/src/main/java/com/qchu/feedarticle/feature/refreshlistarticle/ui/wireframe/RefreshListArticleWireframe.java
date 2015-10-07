package com.qchu.feedarticle.feature.refreshlistarticle.ui.wireframe;

import android.app.Activity;
import android.content.Intent;

import com.qchu.feedarticle.common.Wireframe;
import com.qchu.feedarticle.feature.detailarticle.ui.view.DetailArticlePagerActivity;
import com.qchu.feedarticle.feature.refreshlistarticle.ui.presenter.RefreshRefreshListArticlePresenter;
import com.qchu.feedarticle.feature.refreshlistarticle.ui.presenter.RefreshListArticleWireframeInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quocdungchu on 07/09/15.
 */
public class RefreshListArticleWireframe extends Wireframe implements RefreshListArticleWireframeInterface {

	public RefreshListArticleWireframe(Activity fromActivity) {
		super(fromActivity);
	}

	@Override
	public void presentDetailArticleUserInterface(RefreshRefreshListArticlePresenter refreshListArticlePresenter,
	                                              List<String> articleIdList, int currentIndex) {
		getFromActivity().startActivity(
			new Intent(getFromActivity(), DetailArticlePagerActivity.class)
				.putExtra(DetailArticlePagerActivity.CURRENT_INDEX, currentIndex)
				.putStringArrayListExtra(DetailArticlePagerActivity.ARTICLE_LIST,
					(ArrayList<String>) articleIdList));
	}
}
