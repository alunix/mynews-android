package com.qchu.feedarticle.service.rss.parser.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by quocdungchu on 08/09/15.
 */
@Getter
@Root(name="rss", strict=false)
@ToString(includeFieldNames=true)
public class ParsedRSS {
	@Attribute
	String version;

	@Element
	ParsedChannel channel;
}
