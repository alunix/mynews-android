package com.qchu.feedarticle.view.detailarticle.databinding;

import android.os.Parcelable;

import javax.annotation.Nullable;

import auto.parcel.AutoParcel;

/**
 * Created by quocdungchu on 29/09/15.
 */

//@Builder
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
//@ToString(includeFieldNames=true)
//@Parcel
@AutoParcel
public abstract class BindableArticle implements Parcelable {

	public static Builder builder(){
		return new AutoParcel_BindableArticle.Builder();
	}

	public abstract String title();
	public abstract String content();
	public abstract String publicationDate();
	@Nullable public abstract String imageURL();

	@AutoParcel.Builder
	public abstract static class Builder {

		public abstract Builder title (String title);
		public abstract Builder content (String content);
		public abstract Builder publicationDate (String publicationDate);
		public abstract Builder imageURL (@Nullable String imageURL);

		public abstract BindableArticle build();
	}
}
