package com.qchu.feedarticle.feature.search.applogic.interactor;

/**
 * Created by quocdungchu on 27/10/15.
 */
public interface SearchInteractor {

	void search (String query, OnResultListener onResultListener);
}
