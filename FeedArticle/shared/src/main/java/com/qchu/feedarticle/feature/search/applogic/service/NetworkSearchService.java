package com.qchu.feedarticle.feature.search.applogic.service;

import com.google.common.collect.ImmutableMap;
import com.qchu.feedarticle.feature.article.applogic.entity.Channel;
import com.qchu.feedarticle.feature.search.applogic.interactor.SearchService;
import com.qchu.once.shared.network.entity.Request;
import com.qchu.once.shared.network.entity.Response;
import com.qchu.once.shared.network.interactor.NetworkInteractor;
import com.qchu.once.shared.network.interactor.Parser;

import java.util.List;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by quocdungchu on 08/11/15.
 */
public class NetworkSearchService implements SearchService {


	private NetworkInteractor networkInteractor;
	private Parser<List<Channel>> parser;
	private Request request;

	public NetworkSearchService(
		@Nonnull NetworkInteractor networkInteractor,
		@Nonnull Request request,
		@Nonnull Parser<List<Channel>> parser){

		this.networkInteractor = networkInteractor;
		this.request = request;
		this.parser = parser;
	}

	@Override
	public void search(
		@Nonnull String query,
		@Nonnull final OnResultListener onResultListener) {

		this.networkInteractor.send(
			Request.builder()
				.url("https://ajax.googleapis.com/ajax/services/feed/find")
				.parameters(ImmutableMap.of("v", "1.0", "q", query))
				.method(Request.Method.GET)
				.build(),
			this.parser,
			new NetworkInteractor.OnResponseListener<List<Channel>>() {
				@Override
				public void onResponse(Response<List<Channel>> response, String errorMessage) {
					onResultListener.onResult(
						response != null ? response.body(): null);
				}
			}
		);
	}
}
