package com.qchu.rss.parsed.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by quocdungchu on 19/09/15.
 */
@Root(name = "item", strict = false)
public class ParsedItem {

	@Element(name = "title", required = true)
	private String title;//The title of the item.	Venice Film Festival Tries to Quit Sinking

	@Element(name = "link", required = true)
	private String link;//The URL of the item.	http://www.nytimes.com/2002/09/07/movies/07FEST.html

	@Element(name = "description", required = true)
	private String description;//The item synopsis.	Some of the most heated chatter at the Venice Film Festival this week was about the way that the arrival of the stars at the Palazzo del Cinema was being staged.

	@Namespace(prefix="content")
	@Element(name = "encoded", required = false)
	private String content;

	@Element(name = "author", required = false)
	private String author;//Email address of the author of the item. More.	oprah@oxygen.net

	@ElementList(entry = "category", required = false)
	private List<String> categoryList;//Includes the item in one or more categories. More.	Simpsons Characters

	@ElementList(entry = "comments", required = false)
	private List<String> commentList;//URL of a page for comments relating to the item. More.	http://www.myblog.org/cgi-local/mt/mt-comments.cgi?entry_id=290

	@Element(name = "enclosure", required = false)
	private String enclosure;//	Describes a media object that is attached to the item. More.	<enclosure url="http://live.curry.com/mp3/celebritySCms.mp3" length="1069871" type="audio/mpeg"/>

	@Element(name = "guid", required = false)
	private String guid;//A string that uniquely identifies the item. More.	<guid isPermaLink="true">http://inessential.com/2002/09/01.php#a2</guid>

	@Element(name = "pubDate", required = false)
	private String pubDate;//	Indicates when the item was published. More.	Sun, 19 May 2002 15:21:36 GMT

	@Element(name = "source", required = false)
	private String source;//	The RSS channel that the item came from. More.

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getDescription() {
		return description;
	}

	public String getContent() {
		return content;
	}

	public String getAuthor() {
		return author;
	}

	public List<String> getCategoryList() {
		return categoryList;
	}

	public List<String> getCommentList() {
		return commentList;
	}

	public String getEnclosure() {
		return enclosure;
	}

	public String getGuid() {
		return guid;
	}

	public String getPubDate() {
		return pubDate;
	}

	public String getSource() {
		return source;
	}
}
