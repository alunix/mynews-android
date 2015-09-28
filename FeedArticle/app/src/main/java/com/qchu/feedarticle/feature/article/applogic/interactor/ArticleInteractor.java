package com.qchu.feedarticle.feature.article.applogic.interactor;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.article.applogic.entity.Site;
import com.qchu.feedarticle.feature.article.applogic.entity.SiteConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by quocdungchu on 07/09/15.
 */
public class ArticleInteractor {

	NetworkAdapter mNetworkAdapter;

	public ArticleInteractor(NetworkAdapter networkAdapter) {
		mNetworkAdapter = networkAdapter;
	}

	public void getArticle(List<SiteConfig> siteConfigList,
	                       final GetArticleListListener getArticleListListener){

		final List<Article> allArticleSortedList = new ArrayList<>();
		mNetworkAdapter.getArticles(siteConfigList, new NetworkAdapter.GetArticleListListener() {
			@Override
			public void onBegin(NetworkAdapter networkAdapter) {
				getArticleListListener.onBegin(ArticleInteractor.this);
			}

			@Override
			public void onNext(NetworkAdapter networkAdapter,SiteConfig siteConfig, Site site) {
				allArticleSortedList.addAll(site.getArticleList());
				Collections.sort(allArticleSortedList, new DescentDateSortArticleComparator());
				getArticleListListener.onNextSite(ArticleInteractor.this, site, allArticleSortedList);
			}

			@Override
			public void onComplete(NetworkAdapter networkAdapter) {
				Collections.sort(allArticleSortedList, new DescentDateSortArticleComparator());
				getArticleListListener.onComplete(ArticleInteractor.this, allArticleSortedList);
			}
		});
	}

	public interface GetArticleListListener {
		void onBegin(ArticleInteractor articleInteractor);
		void onNextSite(ArticleInteractor articleInteractor, Site site,
		                List<Article> allArticleSortedList);
		void onComplete(ArticleInteractor articleInteractor, List<Article> articleList);
	}

}
