package com.qchu.once.shared.network.interactor;

import com.google.common.truth.Truth;
import com.qchu.once.shared.connectivity.Connectivity;

import org.hamcrest.core.AnyOf;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
			public void send(Request request, NetworkAdapterOnResponseListener networkAdapterOnResponseListener) {
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
			Request.builder()
				.method(Request.Method.GET)
				.url("https://anyHost.com/")
				.build(),
			parsedPersonParser,
			onResponseListener
		);

		ArgumentCaptor<String> jsonArgumentCaptor = ArgumentCaptor.forClass(String.class);
		verify(parsedPersonParser, times(1)).parse(jsonArgumentCaptor.capture());

		Truth.assertThat(jsonArgumentCaptor.getValue()).isEqualTo("any Json");

		ArgumentCaptor<Response> parsedPersonArgumentCaptor = ArgumentCaptor.forClass(Response.class);
		ArgumentCaptor<String> errorArgumentCaptor = ArgumentCaptor.forClass(String.class);

		verify(onResponseListener, times(1)).onResponse(
			parsedPersonArgumentCaptor.capture(),
			errorArgumentCaptor.capture());
		ParsedPerson parsedPerson = (ParsedPerson)parsedPersonArgumentCaptor.getValue().body();
		Truth.assertThat(parsedPerson.firstName)
			.isEqualTo("Barrack");
		Truth.assertThat(parsedPerson.lastName)
			.isEqualTo("Obama");

		Truth.assertThat(errorArgumentCaptor.getValue())
			.isNull();
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
