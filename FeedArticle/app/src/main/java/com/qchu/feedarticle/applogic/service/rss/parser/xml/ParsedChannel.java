package com.qchu.feedarticle.applogic.service.rss.parser.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

import lombok.Getter;
import lombok.ToString;


/**
 * Created by quocdungchu on 08/09/15.
 */
@Getter
@ToString(includeFieldNames=true)
@Root(strict = false)
public class ParsedChannel {
	// Tricky part in Simple XML because the link is named twice
	@ElementList(entry = "link", inline = true, required = false)
	List<ParsedLink> links;

	@ElementList(entry = "item", required = true, inline = true)
	List<ParsedItem> items;

	@Element(name = "title", required = false)
	String title;

	@Element(name = "language", required = false)
	String language;

	@Element(name = "ttl", required = false)
	int ttl;

	@Element(name = "lastBuildDate", required = false)
	String lastBuildDate;
}
