package com.qchu.feedarticle.service.googlefeed;

import com.google.common.truth.Truth;
import com.qchu.feedarticle.dagger.InteractorModule;
import com.qchu.feedarticle.dagger.NetworkModule;
import com.qchu.feedarticle.dagger.ServiceModule;
import com.qchu.feedarticle.domain.article.entity.Channel;
import com.qchu.feedarticle.domain.search.interactor.SearchService;
import com.qchu.once.shared.connectivity.Connectivity;
import com.qchu.once.shared.network.entity.Request;
import com.qchu.once.shared.network.entity.Response;
import com.qchu.once.shared.network.interactor.DefaultNetworkInteractor;
import com.qchu.once.shared.network.interactor.ErrorMessageProvider;
import com.qchu.once.shared.network.interactor.NetworkAdapter;
import com.qchu.once.shared.network.interactor.NetworkAdapterOnResponseListener;
import com.qchu.once.shared.network.interactor.NetworkInteractor;
import com.qchu.once.shared.network.interactor.Parser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

import static com.google.common.truth.Truth.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by quocdungchu on 08/11/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class GoogleFeedSearchServiceSpec {

	Connectivity connectivity;
	ErrorMessageProvider errorMessageProvider;
	NetworkAdapter networkAdapter;
	NetworkInteractor.OnResponseListener onResponseListener;


	NetworkInteractor networkInteractor;
	GoogleFeedSearchService googleFeedSearchService;
	GoogleFeedSearchGsonParser googleFeedSearchGsonParser;
	SearchService.OnResultListener onResultListener;

	@Before public void setup(){

		this.connectivity = mock(Connectivity.class);
		this.errorMessageProvider = mock(ErrorMessageProvider.class);
		this.onResponseListener = mock(NetworkInteractor.OnResponseListener.class);
		this.networkAdapter = new NetworkAdapter() {
			@Override
			public void send(
				Request request,
				NetworkAdapterOnResponseListener networkAdapterOnResponseListener) {

				networkAdapterOnResponseListener.onResponse(
					"{\n" +
						"  \"responseData\": {\n" +
						"    \"query\": \"bongda\",\n" +
						"    \"entries\": [\n" +
						"      {\n" +
						"        \"url\": \"http://www.bongda365.com.vn/feed/\",\n" +
						"        \"title\": \"title\",\n" +
						"        \"contentSnippet\": \"content\",\n" +
						"        \"link\": \"http://www.bongda365.com.vn/\"\n" +
						"      }" +
						"      ]" +
						"    },\n" +
						"  \"responseDetails\": null,\n" +
						"  \"responseStatus\": 200" +
						"}",
					200,
					null);
			}
		};

		this.networkInteractor =
			new DefaultNetworkInteractor(
				this.connectivity,
				this.errorMessageProvider,
				this.networkAdapter
			);

		this.googleFeedSearchGsonParser = new GoogleFeedSearchGsonParser();
		this.googleFeedSearchService =
			new GoogleFeedSearchService(
				this.networkInteractor,
				this.googleFeedSearchGsonParser);

		this.onResultListener = mock(SearchService.OnResultListener.class);
	}

	@Test
	public void searchSuccess(){
		given(connectivity.connectedInternet())
			.willReturn(true);
		given(errorMessageProvider.message(anyInt(), anyString()))
			.willReturn("error");

		this.googleFeedSearchService.search("bonda", this.onResultListener);

		ArgumentCaptor<List> resultCaptor = ArgumentCaptor.forClass(List.class);

		verify(this.onResultListener, times(1)).onResult(resultCaptor.capture());

		List<Channel> channelList = resultCaptor.getValue();
    assertThat(channelList).isNotNull();
    assertThat(channelList.size()).isEqualTo(Integer.valueOf(1));
    assertThat(channelList.get(0).url()).isEqualTo("http://www.bongda365.com.vn/feed/");
    assertThat(channelList.get(0).title()).isEqualTo("title");
    assertThat(channelList.get(0).id()).isEqualTo("http://www.bongda365.com.vn/feed/");

  }
}
