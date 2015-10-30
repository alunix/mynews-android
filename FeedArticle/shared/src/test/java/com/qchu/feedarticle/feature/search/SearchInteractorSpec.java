package com.qchu.feedarticle.feature.search;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by quocdungchu on 29/10/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchInteractorSpec {

	@Test public void helloTest(){
		List mockedList = mock(List.class);

		//using mock object
		mockedList.add("one");
		mockedList.clear();

		//verification
		verify(mockedList).add("one");
		verify(mockedList).clear();
	}
}
