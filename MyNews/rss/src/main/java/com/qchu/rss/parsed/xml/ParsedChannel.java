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
	private List<ParsedLink> links;

	@ElementList(entry = "item", required = true, inline = true)
	private List<ParsedItem> items;

	@Element(name = "title", required = false)
	private String title;

	@Element(name = "language", required = false)
	private String language;

	@Element(name = "ttl", required = false)
	private int ttl;

	@Element(name = "lastBuildDate", required = false)
	private String lastBuildDate;

	public List<ParsedLink> getLinks() {
		return links;
	}

	public List<ParsedItem> getItems() {
		return items;
	}

	public String getTitle() {
		return title;
	}

	public String getLanguage() {
		return language;
	}

	public int getTtl() {
		return ttl;
	}

	public String getLastBuildDate() {
		return lastBuildDate;
	}
}
