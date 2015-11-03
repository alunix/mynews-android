package com.qchu.once.utils.network.adapter.retrofit;

import com.qchu.once.shared.network.interactor.NetworkAdapterOnResponseListener;
import com.qchu.once.shared.network.interactor.NetworkInteractor;
import com.qchu.once.shared.network.interactor.Parser;
import com.qchu.once.shared.network.interactor.Request;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import static org.junit.Assert.*;

/**
 * Created by quocdungchu on 01/11/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class RetrofitNetworkAdapterSpec {


	@Captor ArgumentCaptor<String> argumentCaptorResponse;
	@Captor ArgumentCaptor<Integer> argumentCaptorStatusCode;
	@Captor ArgumentCaptor<String> argumentCaptorErrorMessage;
	@Mock NetworkAdapterOnResponseListener networkAdapterOnResponseListener;
	RetrofitNetworkAdapter retrofitNetworkAdapter;
	Cache cache;

	@Before public void setup(){
		cache = new Cache(new File("build/cache"), 10 * 1024 * 1024);
		retrofitNetworkAdapter = new RetrofitNetworkAdapter(cache);
	}

	@After public void tearDown(){
		
	}
	@Test public void githubAPI(){

		retrofitNetworkAdapter.send(
			Request.builder()
				.url("https://api.github.com/")
				.method(Request.Method.GET)
				.build(),
			networkAdapterOnResponseListener);

		verify(networkAdapterOnResponseListener, timeout(10 * 1000).times(1))
			.onResponse(argumentCaptorResponse.capture(),
				argumentCaptorStatusCode.capture(),
				argumentCaptorErrorMessage.capture());

		System.out.println("response : " + argumentCaptorResponse.getValue());
		System.out.println("status code : " + argumentCaptorStatusCode.getValue());
		System.out.println("error message : " + argumentCaptorErrorMessage.getValue());

		assertNotNull(argumentCaptorResponse.getValue());
		assertEquals(argumentCaptorStatusCode.getValue(), Integer.valueOf(200));
		assertNull(argumentCaptorErrorMessage.getValue());
	}
	@Test public void githubSearch(){

		Map<String, String> parameters = new HashMap<>();
		parameters.put("q","j2objc");

		retrofitNetworkAdapter.send(
			Request.builder()
				.url("https://api.github.com/search/repositories")
				.parameters(parameters)
				.method(Request.Method.GET)
				.build(),
			networkAdapterOnResponseListener);

		verify(networkAdapterOnResponseListener, timeout(10 * 1000).times(1))
			.onResponse(argumentCaptorResponse.capture(),
				argumentCaptorStatusCode.capture(),
				argumentCaptorErrorMessage.capture());

		System.out.println("response : " + argumentCaptorResponse.getValue());
		System.out.println("status code : " + argumentCaptorStatusCode.getValue());
		System.out.println("error message : " + argumentCaptorErrorMessage.getValue());

		assertNotNull(argumentCaptorResponse.getValue());
		assertEquals(argumentCaptorStatusCode.getValue(), Integer.valueOf(200));
		assertNull(argumentCaptorErrorMessage.getValue());
	}
}
