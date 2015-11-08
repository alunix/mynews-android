package com.qchu.feedarticle.service.googlefeed;


import com.qchu.once.shared.connectivity.Connectivity;
import com.qchu.once.shared.network.interactor.ErrorMessageProvider;
import com.qchu.once.shared.network.interactor.NetworkAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by quocdungchu on 08/11/15.
 */
@Module
public class MockModule {
	@Provides @Singleton
	ErrorMessageProvider provideNetworkErrorMessageProvider(){
		return mock(ErrorMessageProvider.class);
	}

	@Provides @Singleton
	com.qchu.feedarticle.domain.search.interactor.ErrorMessageProvider
	provideSearchErrorMessageProvider(){
		return mock(com.qchu.feedarticle.domain.search.interactor.ErrorMessageProvider.class);
	}

	@Provides @Singleton Connectivity provideConnectivity(){
		return mock(Connectivity.class);
	}

	@Provides @Singleton NetworkAdapter provideNetworkAdapter(){
		return mock(NetworkAdapter.class);
	}
}
