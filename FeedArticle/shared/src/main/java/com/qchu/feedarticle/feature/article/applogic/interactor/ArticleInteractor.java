package com.qchu.feedarticle.feature.article.applogic.interactor;

import com.google.auto.value.AutoValue;
import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.article.applogic.entity.Site;
import com.qchu.feedarticle.feature.article.applogic.entity.SiteConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by quocdungchu on 07/09/15.
 */
@AutoValue
public abstract class ArticleInteractor {

	public enum UpdateFavoriteAction {
		ADD,
		REMOVE
	}

	public enum UpdateFavoriteActionResult {
		ADD_SUCCESSFUL,
		ADD_FAILED_REASON_EXIST_ALREADY,
		ADD_FAILED_REASON_OTHER,
		REMOVE_SUCCESSFUL,
		REMOVE_FAILED_REASON_NOT_EXIST,
		REMOVE_FAILED_REASON_OTHER
	}

	public static ArticleInteractor create (NetworkAdapter networkAdapter,
	                                 RepositoryAdapter repositoryAdapter){
		return new AutoValue_ArticleInteractor(networkAdapter, repositoryAdapter);
	}

	abstract NetworkAdapter networkAdapter();
	abstract RepositoryAdapter repositoryAdapter();

	public List<Article> getArticleListInRepositoryBySiteIds(List<String> siteIdList){
		List<Article> sortedArticleList = repositoryAdapter().getArticleBySiteIds(siteIdList);
		Collections.sort(sortedArticleList, new DescentDateSortArticleComparator());
		return sortedArticleList;
	}

	public List<Article> getArticleListInRepositoryByArticleIds(List<String> articleIdList){
		List<Article> sortedArticleList = repositoryAdapter().getArticleByArticleIds(articleIdList);
		Collections.sort(sortedArticleList, new DescentDateSortArticleComparator());
		return sortedArticleList;
	}

	public Article getArticleInRepositoryByArticleId(String articleId) {
		return repositoryAdapter().getArticleById(articleId);
	}

	public List<Article> getFavoriteArticlesInRepository() {
		return repositoryAdapter().getFavoriteArticles();
	}

	public boolean isFavoriteArticleInRepository(String articleId) {
		return repositoryAdapter().isFavoriteArticle(articleId);
	}

	public UpdateFavoriteActionResult updateArticleInFavoriteRepository(
		UpdateFavoriteAction updateFavoriteAction, String articleId) {
		return repositoryAdapter().updateArticleInFavorite(updateFavoriteAction, articleId);
	}

	public void refreshArticles(List<SiteConfig> siteConfigList,
	                            final RefreshArticleListListener refreshArticleListListener){

		final List<Article> allArticleSortedList = new ArrayList<>();
		networkAdapter().getArticles(siteConfigList, new NetworkAdapter.GetArticleListListener() {
			@Override
			public void onBegin(NetworkAdapter networkAdapter) {
				refreshArticleListListener.onBegin(ArticleInteractor.this);
			}

			@Override
			public void onNext(NetworkAdapter networkAdapter, SiteConfig siteConfig, Site site) {
				//update repository
				repositoryAdapter().updateSite(site);

				allArticleSortedList.addAll(site.articleList());
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
