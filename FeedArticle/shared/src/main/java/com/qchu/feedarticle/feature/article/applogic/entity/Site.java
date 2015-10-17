package com.qchu.feedarticle.feature.article.applogic.entity;

import com.google.auto.value.AutoValue;

import java.util.Date;
import java.util.List;

/**
 * Created by quocdungchu on 19/09/15.
 */
@AutoValue
public abstract class Site {

	public static Builder builder(){
		return new AutoValue_Site.Builder();
	}

	public abstract String id();
	public abstract String title();
	public abstract String url();
	//public abstract Date updateDate();

	public abstract List<Article> articleList();
	public abstract SiteConfig siteConfig();

	@AutoValue.Builder
	public abstract static class Builder {
		public abstract Builder id (String id);
		public abstract Builder title (String title);
		public abstract Builder url (String url);
		//public abstract Builder updateDate (Date updateDate);

		public abstract Builder articleList (List<Article> articleList);
		public abstract Builder siteConfig (SiteConfig siteConfig);

		public abstract Site build();
	}
}
