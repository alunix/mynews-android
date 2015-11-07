package com.qchu.once.shared.network.interactor;

import com.qchu.once.shared.connectivity.Connectivity;
import com.qchu.once.shared.network.entity.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.internal.matchers.Null;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by quocdungchu on 01/11/15.
 */
public class DefaultNetworkInteractorSpec {

	Connectivity connectivity;
	ErrorMessageProvider errorMessageProvider;
	NetworkAdapter networkAdapter;

	Parser<ParsedPerson> parsedPersonParser;
	NetworkInteractor.OnResponseListener<ParsedPerson> onResponseListener;

	DefaultNetworkInteractor defaultNetworkInteractor;

	@Before public void setup(){

		connectivity = mock(Connectivity.class);
		errorMessageProvider = mock(ErrorMessageProvider.class);
		parsedPersonParser = mock(Parser.class);
		onResponseListener = mock(NetworkInteractor.OnResponseListener.class);

		given(connectivity.connectedInternet())
			.willReturn(true);

		given(errorMessageProvider.message(anyInt(), anyString()))
			.willReturn("error");

		networkAdapter = new NetworkAdapter() {
			@Override
			public void send(com.qchu.once.shared.network.entity.Request request, NetworkAdapterOnResponseListener networkAdapterOnResponseListener) {
				networkAdapterOnResponseListener.onResponse(
					"any Json",
					200,
					null);
			}
		};

		defaultNetworkInteractor = new DefaultNetworkInteractor(
			connectivity,
			errorMessageProvider,
			networkAdapter
		);
	}

	@Test public void success(){

		willAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				return new ParsedPerson("Barrack", "Obama");
			}
		}).given(parsedPersonParser).parse(anyString());

		defaultNetworkInteractor.send(
			com.qchu.once.shared.network.entity.Request.builder()
				.method(com.qchu.once.shared.network.entity.Request.Method.GET)
				.url("https://anyHost.com/")
				.build(),
			parsedPersonParser,
			onResponseListener
		);

		ArgumentCaptor<String> jsonArgumentCaptor = ArgumentCaptor.forClass(String.class);
		verify(parsedPersonParser, times(1)).parse(jsonArgumentCaptor.capture());

		assertThat(jsonArgumentCaptor.getValue(), is("any Json"));

		ArgumentCaptor<com.qchu.once.shared.network.entity.Response> parsedPersonArgumentCaptor = ArgumentCaptor.forClass(Response.class);
		ArgumentCaptor<String> errorArgumentCaptor = ArgumentCaptor.forClass(String.class);

		verify(onResponseListener, times(1)).onResponse(
			parsedPersonArgumentCaptor.capture(),
			errorArgumentCaptor.capture());
		ParsedPerson parsedPerson = (ParsedPerson)parsedPersonArgumentCaptor.getValue().body();
		assertThat(parsedPerson.firstName, is("Barrack"));
		assertThat(parsedPerson.lastName, is("Obama"));

		assertThat(errorArgumentCaptor.getValue(), is(Null.NULL));
	}

	static class ParsedPerson {
		String lastName;
		String firstName;

		ParsedPerson(String firstName, String lastName){
			this.firstName = firstName;
			this.lastName = lastName;
		}
	}

}
