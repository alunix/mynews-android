package com.qchu.feedarticle.feature.search.applogic.interactor;

import com.qchu.feedarticle.feature.article.applogic.entity.Channel;

import java.util.List;

/**
 * Created by quocdungchu on 30/10/15.
 */
public interface SearchService {
	void search (String query, OnResultListener onResultListener);

	interface OnResultListener {
		void onResult(List<Channel> channelList);
	}
}
