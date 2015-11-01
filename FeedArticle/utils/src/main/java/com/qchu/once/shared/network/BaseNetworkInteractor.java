package com.qchu.once.shared.network;

/**
 * Created by quocdungchu on 31/10/15.
 */
public abstract class BaseNetworkInteractor<T> implements NetworkInteractor {
	@Override
	public final <Parsed> void send(
		Request request,
		final Parser<Parsed> parser,
		final OnResponseListener<Parsed> onResponseListener) {

		send(request, new BaseNetworkInteractorOnResponseListener() {
			@Override
			public void onResponse(String responseBodyString, int statusCode) {

				Parsed parsed = parser.parse(responseBodyString);
				Response<Parsed> parsedResponse = new Response(parsed, statusCode);

				onResponseListener.onResponse(parsedResponse);
			}
		});
	}

	protected abstract void send(
		Request request,
		BaseNetworkInteractorOnResponseListener onResponseListener);

	public interface BaseNetworkInteractorOnResponseListener {
		void onResponse(String responseBodyString, int statusCode);
	}
}
