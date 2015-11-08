package com.qchu.feedarticle.domain.article.interactor;

import java.util.List;

/**
 * Created by quocdungchu on 30/10/15.
 */
public interface ArticleInteractor {

	List<com.qchu.feedarticle.domain.article.entity.Article> getArticleListInRepositoryBySiteIds(List<String> siteIdList);
	List<com.qchu.feedarticle.domain.article.entity.Article> getArticleListInRepositoryByArticleIds(List<String> articleIdList);
	com.qchu.feedarticle.domain.article.entity.Article getArticleInRepositoryByArticleId(String articleId);

	void refreshArticles(List<com.qchu.feedarticle.domain.article.entity.ChannelConfig> channelConfigList,
	                     RefreshArticleListListener refreshArticleListListener);


	interface RefreshArticleListListener {
		void onBegin(ArticleInteractor articleInteractor);

		void onNextSite(ArticleInteractor articleInteractor, com.qchu.feedarticle.domain.article.entity.Channel channel,
		                List<com.qchu.feedarticle.domain.article.entity.Article> allArticleSortedList);

		void onComplete(ArticleInteractor articleInteractor, List<com.qchu.feedarticle.domain.article.entity.Article> articleList);
	}
}

