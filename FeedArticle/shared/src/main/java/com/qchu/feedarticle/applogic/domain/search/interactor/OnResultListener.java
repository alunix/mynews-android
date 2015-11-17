package com.qchu.feedarticle.applogic.domain.search.interactor;

import com.qchu.feedarticle.applogic.domain.article.entity.Channel;

import java.util.List;

/**
 * Created by quocdungchu on 07/11/15.
 */
public interface OnResultListener {
	void onResult (List<Channel> channelList,
	               SearchError searchError,
	               String errorMessage);
}
