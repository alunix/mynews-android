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
	public String href;

	@Attribute(required = false)
	public String rel;

	@Attribute(name = "type", required = false)
	public String contentType;

	@Text(required = false)
	public String link;
}
