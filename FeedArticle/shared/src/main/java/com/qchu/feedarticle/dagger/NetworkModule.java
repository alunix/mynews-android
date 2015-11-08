package com.qchu.feedarticle.dagger;

import com.qchu.once.shared.network.interactor.DefaultNetworkInteractor;
import com.qchu.once.shared.network.interactor.NetworkInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by quocdungchu on 08/11/15.
 */
@Module
public class NetworkModule {
	@Provides @Singleton NetworkInteractor provideNetworkInteractor(
		DefaultNetworkInteractor defaultNetworkInteractor){

		return defaultNetworkInteractor;
	}
}
