package com.qchu.feedarticle.feature.listarticle.applogic.entity;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by quocdungchu on 19/09/15.
 */
@Builder
@Getter
@ToString(includeFieldNames=true)
public class Site {
	String id;
	String title;
	String url;

	List<Article> articleList;
	SiteConfig siteConfig;
}
