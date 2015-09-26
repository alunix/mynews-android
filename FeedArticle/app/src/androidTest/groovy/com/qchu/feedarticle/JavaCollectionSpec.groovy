package com.qchu.feedarticle

import spock.lang.Specification;

public class JavaCollectionSpec extends Specification {

	def "Collection binary search binarySearch #e expect position at #i" (){
		given:
		List<Integer> array = new ArrayList<>();
		array.add(2);
		array.add(1);
		array.add(10);
		array.add(11);
		array.add(7);
		Collections.sort(array);

		expect:
		Collections.binarySearch(array, e) == i

		where:
		e || i
		7 || 2
		4 || -3
		8 || -4
		0 || -1
	}

}
