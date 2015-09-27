package com.qchu.feedarticle.feature.listarticle.ui.presenter;

import com.qchu.feedarticle.common.Presenter;
import com.qchu.feedarticle.feature.listarticle.applogic.entity.Article;
import com.qchu.feedarticle.feature.listarticle.applogic.entity.Site;
import com.qchu.feedarticle.feature.listarticle.applogic.entity.SiteConfig;
import com.qchu.feedarticle.feature.listarticle.applogic.interactor.ArticleInteractor;
import com.qchu.feedarticle.feature.listarticle.ui.wireframe.ListArticleWireframe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;

/**
 * Created by quocdungchu on 07/09/15.
 */
public class ListArticlePresenter extends Presenter
	implements ListArticleUserInterfaceEventHandler {

	ArticleInteractor mArticleInteractor;
	ListArticleUserInterface mListArticleUserInterface;
	ListArticleWireframeInterface mListArticleWireframeInterface;

	public static ListArticlePresenter create(
		ArticleInteractor articleInteractor,
		ListArticleUserInterface listArticleUserInterface,
		ListArticleWireframeInterface listArticleWireframeInterface) {

		ListArticlePresenter listArticlePresenter = new ListArticlePresenter();
		listArticlePresenter.mArticleInteractor = articleInteractor;
		listArticlePresenter.mListArticleUserInterface = listArticleUserInterface;
		listArticlePresenter.mListArticleWireframeInterface = listArticleWireframeInterface;

		listArticlePresenter.onCreate();

		return listArticlePresenter;
	}

	ListArticlePresenter(){}

	@Override
	protected void onCreate() {
		List<SiteConfig> siteConfigList = new ArrayList<>();
		siteConfigList.add(SiteConfig.builder()
			.url("http://feeds.feedburner.com/RayWenderlich")
			.build());
		mArticleInteractor.getArticle(siteConfigList, new ArticleInteractor.GetArticleListListener() {
			@Override
			public void onBegin(ArticleInteractor articleInteractor) {

			}

			@Override
			public void onFinish(ArticleInteractor articleInteractor, List<Article> articleList) {
				mListArticleUserInterface.bindArticles(articleList);
			}
		});
	}

	@Override
	protected void onDestroy() {

	}
}
