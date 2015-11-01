package com.qchu.once.shared.network.interactor;

/**
 * Created by quocdungchu on 31/10/15.
 */
public interface NetworkInteractor {

	<Parsed> void send(
		Request request,
		Parser<Parsed> parser,
		OnResponseListener<Parsed> onResponseListener);

	interface OnResponseListener<Parsed> {
		void onResponse(Response<Parsed> response, String errorMessage);
	}
}
