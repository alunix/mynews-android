package com.qchu.feedarticle.applogic.domain.article.interactor;

import com.qchu.feedarticle.applogic.domain.article.entity.Channel;
import com.qchu.feedarticle.applogic.domain.article.entity.ChannelConfig;

import java.util.List;

/**
 * Created by quocdungchu on 28/09/15.
 */
public interface SourceRepository {
	void getArticles(List<ChannelConfig> channelConfigList,
	                 GetArticleListListener getArticleListListener);
	interface GetArticleListListener {
		void onBegin(SourceRepository sourceRepository);
		void onNext(SourceRepository sourceRepository, ChannelConfig channelConfig, Channel channel);
		void onComplete(SourceRepository sourceRepository);
	}
}
