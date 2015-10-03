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
	RepositoryAdapter mRepositoryAdapter;

	public ArticleInteractor(NetworkAdapter networkAdapter, RepositoryAdapter repositoryAdapter) {
		mNetworkAdapter = networkAdapter;
		mRepositoryAdapter = repositoryAdapter;
	}

	public List<Article> getArticleListInRepositoryBySiteIds(List<String> siteIdList){
		List<Article> sortedArticleList = mRepositoryAdapter.getArticleBySiteIds(siteIdList);
		Collections.sort(sortedArticleList, new DescentDateSortArticleComparator());
		return sortedArticleList;
	}

	public List<Article> getArticleListInRepositoryByArticleIds(List<String> articleIdList){
		List<Article> sortedArticleList = mRepositoryAdapter.getArticleByArticleIds(articleIdList);
		Collections.sort(sortedArticleList, new DescentDateSortArticleComparator());
		return sortedArticleList;
	}

	public Article getArticleInRepositoryByArticleId(String articleId) {
		return mRepositoryAdapter.getArticleById(articleId);
	}

	public void refreshArticles(List<SiteConfig> siteConfigList,
	                            final RefreshArticleListListener refreshArticleListListener){

		final List<Article> allArticleSortedList = new ArrayList<>();
		mNetworkAdapter.getArticles(siteConfigList, new NetworkAdapter.GetArticleListListener() {
			@Override
			public void onBegin(NetworkAdapter networkAdapter) {
				refreshArticleListListener.onBegin(ArticleInteractor.this);
			}

			@Override
			public void onNext(NetworkAdapter networkAdapter,SiteConfig siteConfig, Site site) {
				//update repository
				mRepositoryAdapter.updateSite(site);

				allArticleSortedList.addAll(site.getArticleList());
				Collections.sort(allArticleSortedList, new DescentDateSortArticleComparator());
				refreshArticleListListener.onNextSite(ArticleInteractor.this, site, allArticleSortedList);
			}

			@Override
			public void onComplete(NetworkAdapter networkAdapter) {
				Collections.sort(allArticleSortedList, new DescentDateSortArticleComparator());
				refreshArticleListListener.onComplete(ArticleInteractor.this, allArticleSortedList);
			}
		});
	}

	public interface RefreshArticleListListener {
		void onBegin(ArticleInteractor articleInteractor);
		void onNextSite(ArticleInteractor articleInteractor, Site site,
		                List<Article> allArticleSortedList);
		void onComplete(ArticleInteractor articleInteractor, List<Article> articleList);
	}

}
