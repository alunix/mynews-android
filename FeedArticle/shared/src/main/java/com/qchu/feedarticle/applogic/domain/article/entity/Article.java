package com.qchu.feedarticle.applogic.domain.article.entity;

import com.google.auto.value.AutoValue;

import javax.annotation.Nullable;


import java.util.Date;
import java.util.List;


/**
 * Created by quocdungchu on 07/09/15.
 */
@AutoValue
public abstract class Article {

	public static Builder builder(){
		return new AutoValue_Article.Builder();
	}

	public abstract String identifier();
	public abstract String title();
	public abstract String summary();
	public abstract String content();
	public abstract String webUrl();
	public abstract Date publicationDate();
	@Nullable
	public abstract Image mainImage();
	public abstract List<Image> imageList();

	@AutoValue.Builder
	public abstract static class Builder {
		public abstract Builder identifier (String identifier);
		public abstract Builder title (String title);
		public abstract Builder summary (String summary);
		public abstract Builder content (String content);
		public abstract Builder webUrl (String webUrl);
		public abstract Builder publicationDate (Date publicationDate);
		public abstract Builder mainImage (@Nullable Image mainImage);
		public abstract Builder imageList (List<Image> imageList);

		public abstract Article build();
	}
}