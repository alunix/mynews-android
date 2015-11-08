package com.qchu.feedarticle.service.googlefeed;

import com.qchu.feedarticle.dagger.InteractorModule;
import com.qchu.feedarticle.dagger.NetworkModule;
import com.qchu.feedarticle.dagger.ServiceModule;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by quocdungchu on 08/11/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class GoogleFeedSearchServiceSpec {



	GoogleFeedSearchComponent googleFeedSearchComponent;
	@Inject GoogleFeedSearchService googleFeedSearchService;

	@Before public void setup(){
		//this.googleFeedSearchComponent = Dag$


	}

	@Test public void searchSuccess(){

	}
}
