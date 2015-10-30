package com.qchu.feedarticle.feature.article.applogic.interactor;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.article.applogic.entity.Site;
import com.qchu.feedarticle.feature.article.applogic.entity.SiteConfig;

import java.util.List;

/**
 * Created by quocdungchu on 30/10/15.
 */
public interface ArticleInteractor {

	List<Article> getArticleListInRepositoryBySiteIds(List<String> siteIdList);
	List<Article> getArticleListInRepositoryByArticleIds(List<String> articleIdList);
	Article getArticleInRepositoryByArticleId(String articleId);

	void refreshArticles(List<SiteConfig> siteConfigList,
	                     RefreshArticleListListener refreshArticleListListener);


	interface RefreshArticleListListener {
		void onBegin(ArticleInteractor articleInteractor);

		void onNextSite(ArticleInteractor articleInteractor, Site site,
		                List<Article> allArticleSortedList);

		void onComplete(ArticleInteractor articleInteractor, List<Article> articleList);
	}
}

