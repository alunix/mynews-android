package com.qchu.feedarticle.domain.article.entity;

import com.google.auto.value.AutoValue;

/**
 * Created by quocdungchu on 19/09/15.
 */
@AutoValue
public abstract class ChannelConfig {

	public static Builder builder(){
		return new AutoValue_ChannelConfig.Builder();
	}

	public abstract String url();

	@AutoValue.Builder
	public abstract static class Builder{
		public abstract Builder url (String url);
		public abstract ChannelConfig build();
	}
}
