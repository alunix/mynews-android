package com.qchu.feedarticle.domain.search.interactor;

import com.qchu.feedarticle.domain.article.entity.Channel;

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