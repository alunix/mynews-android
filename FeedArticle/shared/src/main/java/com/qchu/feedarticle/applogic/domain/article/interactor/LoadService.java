package com.qchu.feedarticle.applogic.domain.article.interactor;

import com.qchu.feedarticle.applogic.domain.article.entity.Channel;
import com.qchu.feedarticle.applogic.domain.article.entity.ChannelConfig;

import java.util.List;

/**
 * Created by quocdungchu on 28/09/15.
 */
public interface LoadService {
	void loadArticles(List<ChannelConfig> channelConfigs, OnLoadListener onLoadListener);

	interface OnLoadListener {
		void onBegin(LoadService loadService);
		void onNext(LoadService loadService, ChannelConfig channelConfig, Channel channel);
		void onComplete(LoadService loadService);
	}
}
