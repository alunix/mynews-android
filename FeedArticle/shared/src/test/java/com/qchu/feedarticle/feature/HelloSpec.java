package com.qchu.feedarticle.feature;

import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by quocdungchu on 04/11/15.
 */
public class HelloSpec {
	@Test
	public void helloTest(){
		List mockedList = mock(List.class);

		//using mock object
		mockedList.add("one");
		mockedList.clear();

		//verification
		verify(mockedList).add("one");
		verify(mockedList).clear();
	}
}
