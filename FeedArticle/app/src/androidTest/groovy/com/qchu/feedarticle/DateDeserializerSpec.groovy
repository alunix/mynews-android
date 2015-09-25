package com.qchu.feedarticle

import com.qchu.feedarticle.common.DateDeserializer
import spock.lang.Specification

import java.text.SimpleDateFormat;

public class DateDeserializerSpec extends Specification{

	def "Deserialize date from string #input,expect #output"() {
		given:
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		expect:
		simpleDateFormat.format(DateDeserializer.get().deserialize(input)) == output
		where:
		input                             ||  output
		"Fri, 04 Sep 2015 13:07:58 +0000" ||  "04/09/2015 13:07:58"
		"Fri, 28 Aug 2015 22:31:03 +0000"	||  "28/08/2015 22:31:03"
		//"Fri, 28 Aug 2015 22:31:03 +0000"	||  "28/08/2015 22:31:02"
	}
}
