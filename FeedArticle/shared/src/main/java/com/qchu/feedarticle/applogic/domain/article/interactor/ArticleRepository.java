package com.qchu.feedarticle.applogic.domain.article.interactor;

import com.qchu.feedarticle.applogic.domain.article.entity.Article;
import com.qchu.feedarticle.applogic.domain.article.entity.Channel;

import java.util.List;

/**
 * Created by quocdungchu on 28/09/15.
 */
public interface ArticleRepository {
	Article getArticleById(String articleId);
	List<Article> getArticleBySiteIds(List<String> siteIdList);
	List<Article> getArticleByArticleIds(List<String> articleIdList);
	void updateSiteList(List<Channel> channelList);
	void updateSite(Channel channel);
}
