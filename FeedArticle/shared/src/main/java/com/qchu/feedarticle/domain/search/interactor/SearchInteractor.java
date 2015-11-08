package com.qchu.feedarticle.domain.search.interactor;

/**
 * Created by quocdungchu on 27/10/15.
 */
public interface SearchInteractor {

	void search (String query, OnResultListener onResultListener);
}
