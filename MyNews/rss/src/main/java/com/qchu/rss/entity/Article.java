package com.qchu.rss.entity;

import com.google.auto.value.AutoValue;

import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;


/**
 * Created by Quoc Dung Chu on 07/09/15.
 */
@AutoValue
public abstract class Article {

	public static Builder builder(){
		return new AutoValue_Article.Builder();
	}
	@Nullable public abstract String title();
	@Nullable public abstract String summary();
	@Nullable public abstract String content();
	@Nullable public abstract String link();
	@Nullable public abstract Date publicationDate();
	@Nullable public abstract Image mainImage();
	public abstract List<Image> images();

	@AutoValue.Builder
	public abstract static class Builder {
		public abstract Builder title (String title);
		public abstract Builder summary (String summary);
		public abstract Builder content (String content);
		public abstract Builder link (String link);
		public abstract Builder publicationDate (Date publicationDate);
		public abstract Builder mainImage (Image mainImage);
		public abstract Builder images (List<Image> images);
		public abstract Article build();
	}
}
