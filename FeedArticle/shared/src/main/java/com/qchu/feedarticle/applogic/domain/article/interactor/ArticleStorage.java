package com.qchu.feedarticle.applogic.domain.article.interactor;

import com.qchu.feedarticle.applogic.domain.article.entity.Article;
import com.qchu.feedarticle.applogic.domain.article.entity.Channel;

import java.util.List;

/**
 * Created by quocdungchu on 28/09/15.
 */
public interface ArticleStorage {

	Article articleByArticleId(String articleId);

	List<Article> articlesByChannelIds(List<String> channelIds);

	List<Article> articlesByArticleIds(List<String> articleIds);

	void updateChannels(List<Channel> channels);

	void updateChannel(Channel channel);
}
