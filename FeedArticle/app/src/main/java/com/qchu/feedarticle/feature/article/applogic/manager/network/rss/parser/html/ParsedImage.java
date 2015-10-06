package com.qchu.feedarticle.feature.article.applogic.manager.network.rss.parser.html;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by quocdungchu on 19/09/15.
 */
@Builder
@Getter
@ToString(includeFieldNames=true)
public class ParsedImage {
	String src;
	float width;
	float height;

	public boolean isValid(){
		return src != null && !src.equals("");
	}
}
