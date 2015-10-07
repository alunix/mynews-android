package com.qchu.feedarticle.feature.refreshlistarticle.ui.presenter;

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
public class RefreshListArticlePresenter extends Presenter
	implements RefreshListArticleUserInterfaceEventHandler {

	List<String> mArticleIdList = new ArrayList<>();

	ArticleInteractor mArticleInteractor;
	RefreshListArticleUserInterface mRefreshListArticleUserInterface;
	RefreshListArticleWireframeInterface mRefreshListArticleWireframeInterface;

	public static RefreshListArticlePresenter create(
		ArticleInteractor articleInteractor,
		RefreshListArticleUserInterface refreshListArticleUserInterface,
		RefreshListArticleWireframeInterface refreshListArticleWireframeInterface) {

		RefreshListArticlePresenter refreshListArticlePresenter = new RefreshListArticlePresenter();
		refreshListArticlePresenter.mArticleInteractor = articleInteractor;
		refreshListArticlePresenter.mRefreshListArticleUserInterface = refreshListArticleUserInterface;
		refreshListArticlePresenter.mRefreshListArticleWireframeInterface = refreshListArticleWireframeInterface;

		refreshListArticlePresenter.onCreate();

		return refreshListArticlePresenter;
	}

	RefreshListArticlePresenter(){}

	@Override
	protected void onCreate() {
		refreshContent();
	}

	@Override
	protected void onDestroy() {

	}

	@Override
	public void onSwipeRefreshEvent(RefreshListArticleUserInterface listArticleUserInterface) {
		refreshContent();
	}

	@Override
	public void onArticleItemClickEvent(RefreshListArticleUserInterface listArticleUserInterface,
	                                    int position) {

		mRefreshListArticleWireframeInterface.presentDetailArticleUserInterface(
			this, mArticleIdList, position);
	}


	private void refreshContent(){
		List<SiteConfig> siteConfigList = new ArrayList<>();
		
		siteConfigList.add(SiteConfig.builder()
			.url("http://feeds.feedburner.com/RayWenderlich")
			.build());

		siteConfigList.add(SiteConfig.builder()
			.url("http://thethaovanhoa.vn/trang-chu.rss")
			.build());

		mArticleInteractor.refreshArticles(siteConfigList, new ArticleInteractor.RefreshArticleListListener() {
			@Override
			public void onBegin(ArticleInteractor articleInteractor) {
				mArticleIdList = new ArrayList<>();
				mRefreshListArticleUserInterface.beginSwipeRefreshingLayout(RefreshListArticlePresenter.this);
			}

			@Override
			public void onNextSite(ArticleInteractor articleInteractor,
			                       Site site, List<Article> allArticleSortedList) {
				mArticleIdList = articleIdsFromArticles(allArticleSortedList);
				mRefreshListArticleUserInterface.bindArticles(allArticleSortedList);
			}

			@Override
			public void onComplete(ArticleInteractor articleInteractor,
			                       List<Article> allArticleSortedList) {
				mArticleIdList = articleIdsFromArticles(allArticleSortedList);
				mRefreshListArticleUserInterface.endSwipeRefreshingLayout(RefreshListArticlePresenter.this);
			}
		});
	}

	List<String> articleIdsFromArticles(List<Article> articleList) {
		List<String> articleIdList = new ArrayList<>();
		for(Article article: articleList) {
			articleIdList.add(article.getId());
		}
		return articleIdList;
	}
}