package com.qchu.once.shared.network;

import com.google.auto.value.AutoValue;

/**
 * Created by quocdungchu on 31/10/15.
 */
@AutoValue
public abstract class Request {
	public enum Type {
		GET, POST, PUT, DELETE
	}

	public abstract Type type();

	@AutoValue.Builder
	public abstract static class Builder {

		public abstract Builder type (Type type);

		public abstract Request build();
	}
}
