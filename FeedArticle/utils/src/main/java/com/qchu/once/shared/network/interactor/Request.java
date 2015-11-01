package com.qchu.once.shared.network.interactor;

import com.google.auto.value.AutoValue;

import java.util.Map;

/**
 * Created by quocdungchu on 31/10/15.
 */
@AutoValue
public abstract class Request {
	public enum Method {
		OPTIONS, GET, HEAD, POST, PUT, PATCH, DELETE, TRACE, CONNECT
	}

	public abstract Method method();
	public abstract String baseUrl();
	public abstract Map<String,String> parameters();
	public abstract Map<String,String> headers();


	@AutoValue.Builder
	public abstract static class Builder {

		public abstract Builder method (Method method);
		public abstract Builder baseUrl (String baseString);
		public abstract Builder parameters (Map<String,String> parameters);
		public abstract Builder headers (Map<String,String> headers);

		public abstract Request build();
	}
}
