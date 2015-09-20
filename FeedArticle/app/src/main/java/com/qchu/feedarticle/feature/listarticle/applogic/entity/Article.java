package com.qchu.feedarticle.feature.listarticle.applogic.entity;

import org.simpleframework.xml.Root;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by quocdungchu on 07/09/15.
 */
@Builder
@Getter
@ToString(includeFieldNames=true)
public class Article {
	String title;
	String description;
	String content;
	Image mainImage;
	List<Image> imageList;
}
