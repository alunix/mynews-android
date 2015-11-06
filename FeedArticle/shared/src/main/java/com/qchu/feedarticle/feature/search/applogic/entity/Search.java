package com.qchu.feedarticle.feature.search.applogic.entity;

import com.google.auto.value.AutoValue;

/**
 * Created by quocdungchu on 05/11/15.
 */
@AutoValue
public abstract class Search {

	public static Builder builder(){
		return new AutoValue_Search.Builder();
	}

	public abstract String query();

	@AutoValue.Builder
	public static abstract class Builder {
		public abstract Builder query (String query);

		public abstract Search build();
	}
}
