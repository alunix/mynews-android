package com.qchu.feedarticle.feature.listarticle.ui.presenter;

import com.qchu.feedarticle.common.Presenter;
import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.article.applogic.entity.Site;
import com.qchu.feedarticle.feature.article.applogic.entity.SiteConfig;
import com.qchu.feedarticle.feature.article.applogic.interactor.ArticleInteractor;

import java.util.ArrayList;
import java.util.List;

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
		refreshContent();
	}

	@Override
	protected void onDestroy() {

	}

	@Override
	public void onSwipeRefreshEvent(ListArticleUserInterface listArticleUserInterface) {
		refreshContent();
	}

	@Override
	public void onArticleItemClickEvent(ListArticleUserInterface listArticleUserInterface,
	                                    int position) {
	//TODO
		mListArticleWireframeInterface.presentDetailArticleUserInterface(
			this, null);
	}


	private void refreshContent(){
		List<SiteConfig> siteConfigList = new ArrayList<>();
		siteConfigList.add(SiteConfig.builder()
			.url("http://feeds.feedburner.com/RayWenderlich")
			.build());

		siteConfigList.add(SiteConfig.builder()
			.url("http://feeds.feedburner.com/CocoaDevBlog")
			.build());

		mArticleInteractor.getArticle(siteConfigList, new ArticleInteractor.GetArticleListListener() {
			@Override
			public void onBegin(ArticleInteractor articleInteractor) {
				mArticleList = new ArrayList<Article>();
				mListArticleUserInterface.beginSwipeRefreshingLayout(ListArticlePresenter.this);
			}

			@Override
			public void onNextSite(ArticleInteractor articleInteractor,
			                       Site site, List<Article> allArticleSortedList) {
				mArticleList = allArticleSortedList;
				mListArticleUserInterface.bindArticles(allArticleSortedList);
			}

			@Override
			public void onComplete(ArticleInteractor articleInteractor,
			                       List<Article> allArticleSortedList) {
				mListArticleUserInterface.endSwipeRefreshingLayout(ListArticlePresenter.this);
			}
		});
	}
}
