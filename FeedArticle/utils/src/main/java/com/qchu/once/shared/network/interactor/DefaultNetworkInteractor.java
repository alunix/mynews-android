package com.qchu.once.shared.network.interactor;

import com.qchu.once.shared.connectivity.Connectivity;
import com.qchu.once.shared.network.entity.Request;
import com.qchu.once.shared.network.entity.Response;

import javax.inject.Inject;

/**
 * Created by quocdungchu on 31/10/15.
 */
public class DefaultNetworkInteractor implements NetworkInteractor {

	Connectivity connectivity;
	ErrorMessageProvider errorMessageProvider;
	NetworkAdapter networkAdapter;

	@Inject public DefaultNetworkInteractor(
		Connectivity connectivity,
		ErrorMessageProvider errorMessageProvider,
		NetworkAdapter networkAdapter){

		this.connectivity = connectivity;
		this.errorMessageProvider = errorMessageProvider;
		this.networkAdapter = networkAdapter;
	}

	@Override
	public <Parsed> void send(
		Request request,
		final Parser<Parsed> parser,
		final OnResponseListener<Parsed> onResponseListener) {

		if(onResponseListener == null) return;

		if(this.connectivity.connectedInternet()){
			this.networkAdapter.send(request, new NetworkAdapterOnResponseListener() {
				@Override
				public void onResponse(String responseBodyString, int statusCode, String errorMessage) {

					Parsed parsed = responseBodyString != null?
						parser.parse(responseBodyString): null;

					onResponseListener.onResponse(
						new Response(parsed, statusCode),
						DefaultNetworkInteractor.this.errorMessageProvider
							.message(statusCode, errorMessage));
				}

			});
		} else {
			onResponseListener.onResponse(
				null, this.errorMessageProvider.messageForNoInternetConnection());
		}
	}
}
