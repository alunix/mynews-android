package com.qchu.once.utils.network.adapter.retrofit;

import com.google.common.collect.ImmutableMap;
import com.qchu.once.shared.network.interactor.NetworkAdapterOnResponseListener;
import com.qchu.once.shared.network.entity.Request;
import com.squareup.okhttp.Cache;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by quocdungchu on 01/11/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class RetrofitNetworkAdapterSpec {

	@Captor ArgumentCaptor<String> responseCaptor;
	@Captor ArgumentCaptor<Integer> statusCodeCaptor;
	@Captor ArgumentCaptor<String> errorMessageCaptor;
	@Mock NetworkAdapterOnResponseListener networkAdapterOnResponseListener;
	RetrofitNetworkAdapter retrofitNetworkAdapter;
	Cache cache;

	@Before public void setup(){
		cache = new Cache(new File("build/cache"), 10 * 1024 * 1024);
		retrofitNetworkAdapter = new RetrofitNetworkAdapter(cache);
	}

	@After public void tearDown(){
		
	}
	@Test
	public void githubAPI() {

		retrofitNetworkAdapter.send(
			Request.builder()
				.url("https://api.github.com/")
				.method(Request.Method.GET)
				.build(),
			networkAdapterOnResponseListener);

		verify(networkAdapterOnResponseListener, timeout(10 * 1000).times(1))
			.onResponse(responseCaptor.capture(),
				statusCodeCaptor.capture(),
				errorMessageCaptor.capture());

		System.out.println("response : " + responseCaptor.getValue());
		System.out.println("status code : " + statusCodeCaptor.getValue());
		System.out.println("error message : " + errorMessageCaptor.getValue());

		assertThat(responseCaptor.getValue())
			.isNotNull();
		assertThat(statusCodeCaptor.getValue())
			.isEqualTo(Integer.valueOf(200));
		assertThat(errorMessageCaptor.getValue())
			.isNull();
	}

	@Test public void githubSearch(){

		retrofitNetworkAdapter.send(
			Request.builder()
				.url("https://api.github.com/search/repositories")
				.parameters(ImmutableMap.of("q", "j2objc"))
				.method(Request.Method.GET)
				.build(),
			networkAdapterOnResponseListener);

		verify(networkAdapterOnResponseListener, timeout(10 * 1000).times(1))
			.onResponse(responseCaptor.capture(),
				statusCodeCaptor.capture(),
				errorMessageCaptor.capture());

		System.out.println("response : " + responseCaptor.getValue());
		System.out.println("status code : " + statusCodeCaptor.getValue());
		System.out.println("error message : " + errorMessageCaptor.getValue());

		assertThat(responseCaptor.getValue())
			.isNotNull();
		assertThat(statusCodeCaptor.getValue())
			.isEqualTo(Integer.valueOf(200));
		assertThat(errorMessageCaptor.getValue())
			.isNull();
	}

	@Test public void googleFeedSearch(){

		retrofitNetworkAdapter.send(
			Request.builder()
				.url("https://ajax.googleapis.com/ajax/services/feed/find")
				.parameters(ImmutableMap.of(
					"v","1.0",
					"q", "football"))
				.method(Request.Method.GET)
				.build(),
			networkAdapterOnResponseListener);

		verify(networkAdapterOnResponseListener, timeout(10 * 1000).times(1))
			.onResponse(responseCaptor.capture(),
				statusCodeCaptor.capture(),
				errorMessageCaptor.capture());

		System.out.println("response : " + responseCaptor.getValue());
		System.out.println("status code : " + statusCodeCaptor.getValue());
		System.out.println("error message : " + errorMessageCaptor.getValue());

		assertThat(responseCaptor.getValue())
			.isNotNull();
		assertThat(statusCodeCaptor.getValue())
			.isEqualTo(Integer.valueOf(200));
		assertThat(errorMessageCaptor.getValue())
			.isNull();
	}

	@Test public void googleFeedLoad(){

		retrofitNetworkAdapter.send(
			Request.builder()
				.url("https://ajax.googleapis.com/ajax/services/feed/load")
				.parameters(ImmutableMap.of(
					"v","1.0",
					"q", "http://www.digg.com/rss/index.xml"))
				.method(Request.Method.GET)
				.build(),
			networkAdapterOnResponseListener);

		verify(networkAdapterOnResponseListener, timeout(10 * 1000).times(1))
			.onResponse(responseCaptor.capture(),
				statusCodeCaptor.capture(),
				errorMessageCaptor.capture());

		System.out.println("response : " + responseCaptor.getValue());
		System.out.println("status code : " + statusCodeCaptor.getValue());
		System.out.println("error message : " + errorMessageCaptor.getValue());

		assertThat(responseCaptor.getValue())
			.isNotNull();
		assertThat(statusCodeCaptor.getValue())
			.isEqualTo(Integer.valueOf(200));
		assertThat(errorMessageCaptor.getValue())
			.isNull();
	}
}
