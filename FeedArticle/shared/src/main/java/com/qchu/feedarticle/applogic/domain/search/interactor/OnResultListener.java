package com.qchu.feedarticle.applogic.domain.search.interactor;

import com.qchu.feedarticle.applogic.domain.article.entity.Channel;
import com.qchu.feedarticle.applogic.domain.search.entity.Entry;

import java.util.List;

/**
 * Created by quocdungchu on 07/11/15.
 */
public interface OnResultListener {
	void onResult (List<Entry> entries, SearchError searchError, String errorMessage);
}
