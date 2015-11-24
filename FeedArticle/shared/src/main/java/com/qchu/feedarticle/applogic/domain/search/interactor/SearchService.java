package com.qchu.feedarticle.applogic.domain.search.interactor;

import com.qchu.feedarticle.applogic.domain.article.entity.Channel;
import com.qchu.feedarticle.applogic.domain.search.entity.Entry;

import java.util.List;

/**
 * Created by quocdungchu on 30/10/15.
 */
public interface SearchService {
	void search (String query, OnResultListener onResultListener);

	interface OnResultListener {
		void onResult(List<Entry> entries);
	}
}
