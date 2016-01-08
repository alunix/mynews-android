package com.qchu.rss.parsed.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;


/**
 * Created by quocdungchu on 08/09/15.
 */
@Root(strict = false)
public class ParsedChannel {
	// Tricky part in Simple XML because the link is named twice
	@ElementList(entry = "link", inline = true, required = false)
	public List<ParsedLink> links;

	@ElementList(entry = "item", required = true, inline = true)
	public List<ParsedItem> items;

	@Element(name = "title", required = false)
	public String title;

	@Element(name = "language", required = false)
	public String language;

	@Element(name = "ttl", required = false)
	public int ttl;

	@Element(name = "lastBuildDate", required = false)
	public String lastBuildDate;
}
