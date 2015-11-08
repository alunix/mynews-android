package com.qchu.feedarticle.domain.article.interactor;

import com.qchu.feedarticle.domain.article.entity.Channel;

import java.util.List;

/**
 * Created by quocdungchu on 28/09/15.
 */
public interface SourceRepository {
	void getArticles(List<com.qchu.feedarticle.domain.article.entity.ChannelConfig> channelConfigList,
	                 GetArticleListListener getArticleListListener);
	interface GetArticleListListener {
		void onBegin(SourceRepository sourceRepository);
		void onNext(SourceRepository sourceRepository, com.qchu.feedarticle.domain.article.entity.ChannelConfig channelConfig, Channel channel);
		void onComplete(SourceRepository sourceRepository);
	}
}
