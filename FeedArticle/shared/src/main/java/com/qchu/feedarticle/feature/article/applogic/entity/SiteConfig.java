package com.qchu.feedarticle.feature.article.applogic.entity;

import com.google.auto.value.AutoValue;

/**
 * Created by quocdungchu on 19/09/15.
 */
@AutoValue
public abstract class SiteConfig {

	public static Builder builder(){
		return new AutoValue_SiteConfig.Builder();
	}

	public abstract String url();

	@AutoValue.Builder
	public abstract static class Builder{
		public abstract Builder url (String url);
		public abstract SiteConfig build();
	}
}
