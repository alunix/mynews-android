package com.qchu.once.utils.network.adapter.retrofit;


import com.qchu.once.shared.network.interactor.NetworkAdapter;
import com.qchu.once.shared.network.interactor.NetworkAdapterOnResponseListener;
import com.qchu.once.shared.network.interactor.Request;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.sql.rowset.CachedRowSet;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.QueryMap;

public class RetrofitNetworkAdapter implements NetworkAdapter {

	Cache cache;

	public RetrofitNetworkAdapter (@Nonnull Cache cache){
		this.cache = cache;
	}

	@Override
	public void send(
		final Request request,
		final NetworkAdapterOnResponseListener networkAdapterOnResponseListener) {

		OkHttpClient httpClient = new OkHttpClient();
		httpClient.setCache(this.cache);

		if(request.headers() != null && request.headers().size() > 0){
			httpClient.networkInterceptors().add(new Interceptor() {

				@Override
				public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
					com.squareup.okhttp.Request httpRequest = chain.request().newBuilder()
						.headers(Headers.of(request.headers()))
						.build();

					return chain.proceed(httpRequest);
				}

			});
		}

		Retrofit retrofit = new Retrofit.Builder()
			.baseUrl(request.baseUrl())
			.client(httpClient)
			.build();

		Service service = retrofit.create(Service.class);

		Callback<ResponseBody> callback = new Callback<ResponseBody>() {
			@Override
			public void onResponse(Response<ResponseBody> response) {
				if(networkAdapterOnResponseListener != null){

					String responseString = null;
					try {
						responseString = response.body().string();
					} catch (IOException e) {
						e.printStackTrace();
					}

					networkAdapterOnResponseListener.onResponse(
						responseString,
						response.code(),
						response.isSuccess() ? null: response.message());
				}
			}

			@Override
			public void onFailure(Throwable t) {
				if(networkAdapterOnResponseListener != null){
					networkAdapterOnResponseListener.onResponse(
						null,
						-1,
						t.getLocalizedMessage());
				}
			}
		};

		switch (request.method()) {
			case GET:
				service.responseForGET(request.parameters())
					.enqueue(callback);
				break;

			case POST:
				service.responseForPOST(request.parameters())
					.enqueue(callback);
				break;

			default:
				break;
		}
	}

	interface Service {

		@GET("/")
		Call<ResponseBody> responseForGET(@QueryMap Map<String, String> queries);

		@POST("/")
		Call<ResponseBody> responseForPOST(@Body Map<String, String> bodies);
	}
}
