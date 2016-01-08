package com.qchu.rss.entity;

import com.google.auto.value.AutoValue;

/**
 * Created by Quoc Dung Chu on 19/09/15.
 */

@AutoValue
public abstract class Image {

	public static Builder builder(){
		return new AutoValue_Image.Builder();
	}
	public abstract String url();
	public abstract float width();
	public abstract float height();

	@AutoValue.Builder
	public abstract static class Builder{
		public abstract Builder url (String url);
		public abstract Builder width (float width);
		public abstract Builder height (float height);
		public abstract Image build();
	}
}
