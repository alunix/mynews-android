package com.qchu.feedarticle.service.rss.parser.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by quocdungchu on 19/09/15.
 */
@Getter
@Root(name = "link", strict = false)
@ToString(includeFieldNames=true)
public class ParsedLink {
	@Attribute(required = false)
	String href;

	@Attribute(required = false)
	String rel;

	@Attribute(name = "type", required = false)
	String contentType;

	@Text(required = false)
	String link;
}
