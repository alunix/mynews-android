package com.qchu.rss.parsed.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by quocdungchu on 08/09/15.
 */
@Root(name="rss", strict=false)
public class ParsedRSS {
	@Attribute
  public String version;

	@Element
  public ParsedChannel channel;
}
