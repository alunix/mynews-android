package com.qchu.feedarticle.domain.search.interactor;

/**
 * Created by quocdungchu on 08/11/15.
 */
public interface ErrorMessageProvider {
	String message(SearchError searchError);
}
