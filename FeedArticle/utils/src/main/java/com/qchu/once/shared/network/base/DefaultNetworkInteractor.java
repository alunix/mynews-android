package com.qchu.once.shared.network.base;

import com.qchu.once.shared.network.interactor.NetworkInteractor;
import com.qchu.once.shared.network.interactor.Parser;
import com.qchu.once.shared.network.interactor.Request;
import com.qchu.once.shared.network.interactor.Response;

/**
 * Created by quocdungchu on 31/10/15.
 */
public abstract class DefaultNetworkInteractor<T> implements NetworkInteractor {

	NetworkAdapter networkAdapter;

	public DefaultNetworkInteractor(NetworkAdapter networkAdapter){
		this.networkAdapter = networkAdapter;
	}

	@Override
	public <Parsed> void send(
		Request request,
		final Parser<Parsed> parser,
		final OnResponseListener<Parsed> onResponseListener) {

		this.networkAdapter.send(request, new NetworkAdapterOnResponseListener() {
			@Override
			public void onResponse(String responseBodyString, int statusCode) {

				Parsed parsed = parser.parse(responseBodyString);
				Response<Parsed> parsedResponse = new Response(parsed, statusCode);

				onResponseListener.onResponse(parsedResponse);
			}
		});
	}
}
