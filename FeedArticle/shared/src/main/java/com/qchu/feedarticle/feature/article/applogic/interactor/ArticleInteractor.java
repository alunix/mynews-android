package com.qchu.feedarticle.feature.article.applogic.interactor;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.article.applogic.entity.Channel;
import com.qchu.feedarticle.feature.article.applogic.entity.ChannelConfig;

import java.util.List;

/**
 * Created by quocdungchu on 30/10/15.
 */
public interface ArticleInteractor {

	List<Article> getArticleListInRepositoryBySiteIds(List<String> siteIdList);
	List<Article> getArticleListInRepositoryByArticleIds(List<String> articleIdList);
	Article getArticleInRepositoryByArticleId(String articleId);

	void refreshArticles(List<ChannelConfig> channelConfigList,
	                     RefreshArticleListListener refreshArticleListListener);


	interface RefreshArticleListListener {
		void onBegin(ArticleInteractor articleInteractor);

		void onNextSite(ArticleInteractor articleInteractor, Channel channel,
		                List<Article> allArticleSortedList);

		void onComplete(ArticleInteractor articleInteractor, List<Article> articleList);
	}
}

