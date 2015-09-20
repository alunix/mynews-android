package com.qchu.feedarticle.feature.listarticle.applogic.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by quocdungchu on 19/09/15.
 */
@Builder
@Getter
@ToString(includeFieldNames=true)
public class SiteConfig {
	String url;
}
