package com.qchu.feedarticle.domain.article.interactor;

import com.qchu.feedarticle.domain.article.entity.Channel;

import java.util.List;

/**
 * Created by quocdungchu on 28/09/15.
 */
public interface ArticleRepository {
	com.qchu.feedarticle.domain.article.entity.Article getArticleById(String articleId);
	List<com.qchu.feedarticle.domain.article.entity.Article> getArticleBySiteIds(List<String> siteIdList);
	List<com.qchu.feedarticle.domain.article.entity.Article> getArticleByArticleIds(List<String> articleIdList);
	void updateSiteList(List<Channel> channelList);
	void updateSite(Channel channel);
}
