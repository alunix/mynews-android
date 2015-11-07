package com.qchu.feedarticle.feature.article.applogic.interactor;

import com.qchu.feedarticle.feature.article.applogic.entity.Channel;
import com.qchu.feedarticle.feature.article.applogic.entity.ChannelConfig;

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
