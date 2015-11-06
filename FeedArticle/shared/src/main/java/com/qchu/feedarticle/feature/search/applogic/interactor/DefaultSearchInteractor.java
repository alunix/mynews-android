package com.qchu.feedarticle.feature.search.applogic.interactor;

import com.qchu.feedarticle.feature.search.applogic.entity.Search;
import com.qchu.once.shared.connectivity.Connectivity;

/**
 * Created by quocdungchu on 30/10/15.
 */
public class DefaultSearchInteractor<T> implements SearchInteractor<T> {

	private SearchService searchService;
	private Connectivity connectivity;

	public DefaultSearchInteractor (
		SearchService searchService,
		Connectivity connectivity){

		this.searchService = searchService;
		this.connectivity = connectivity;
	}


	@Override
	public void launch(Search search, OnResultListener<T> onResultListener) {
		
	}
}
