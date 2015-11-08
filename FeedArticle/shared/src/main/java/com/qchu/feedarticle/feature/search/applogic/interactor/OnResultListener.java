package com.qchu.feedarticle.feature.search.applogic.interactor;

import com.qchu.feedarticle.feature.article.applogic.entity.Channel;

import java.util.List;

/**
 * Created by quocdungchu on 07/11/15.
 */
public interface OnResultListener {
	void onResult (List<Channel> channelList,
	               SearchError searchError,
	               String errorMessage);
}
