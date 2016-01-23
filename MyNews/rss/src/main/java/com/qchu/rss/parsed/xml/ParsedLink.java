package com.qchu.rss.parsed.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * Created by quocdungchu on 19/09/15.
 */
@Root(name = "link", strict = false)
public class ParsedLink {
	@Attribute(required = false)
	private String href;

	@Attribute(required = false)
	private String rel;

	@Attribute(name = "type", required = false)
	private String contentType;

	@Text(required = false)
	private String link;

	public String getHref() {
		return href;
	}

	public String getRel() {
		return rel;
	}

	public String getContentType() {
		return contentType;
	}

	public String getLink() {
		return link;
	}
}
