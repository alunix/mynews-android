package com.qchu.feedarticle.domain.search.interactor;

import com.qchu.feedarticle.domain.article.entity.Channel;
import com.qchu.once.shared.connectivity.Connectivity;

import java.util.List;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by quocdungchu on 30/10/15.
 */
public class DefaultSearchInteractor implements SearchInteractor {

	private SearchService searchService;
	private Connectivity connectivity;
	private ErrorMessageProvider errorMessageProvider;

	public DefaultSearchInteractor (
		SearchService searchService,
	  Connectivity connectivity,
	  ErrorMessageProvider errorMessageProvider) {

		this.searchService = searchService;
		this.connectivity = connectivity;
		this.errorMessageProvider = errorMessageProvider;
	}

	@Override
	public void search(String query, @Nonnull final OnResultListener onResultListener) {
		checkNotNull(onResultListener);

		if(this.connectivity.connectedInternet()){
			this.searchService.search(
				query,
				new SearchService.OnResultListener() {
					@Override
					public void onResult(List<Channel> channelList) {
						if(channelList == null || channelList.isEmpty()){
							onResultListener.onResult(
								null,
								com.qchu.feedarticle.domain.search.interactor.SearchError.NO_RESULT,
								DefaultSearchInteractor.this
									.errorMessageProvider.message(com.qchu.feedarticle.domain.search.interactor.SearchError.NO_RESULT));
						} else {
							onResultListener.onResult(channelList, null, null);
						}
					}
				});
		} else {
			onResultListener.onResult(
				null,
				com.qchu.feedarticle.domain.search.interactor.SearchError.NO_INTERNET,
				this.errorMessageProvider.message(com.qchu.feedarticle.domain.search.interactor.SearchError.NO_RESULT));
		}
	}
}