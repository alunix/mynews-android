package com.qchu.feedarticle.feature.article.applogic.entity;

import java.util.Date;
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
	String id;
	String title;
	String description;
	String content;
	String webUrl;
	Date publicationDate;
	Image mainImage;
	List<Image> imageList;
}
