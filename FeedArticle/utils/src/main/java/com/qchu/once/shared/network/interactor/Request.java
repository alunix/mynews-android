package com.qchu.once.shared.network.interactor;

import com.google.auto.value.AutoValue;
import javax.annotation.Nullable;


import java.util.Map;

/**
 * Created by quocdungchu on 31/10/15.
 */
@AutoValue
public abstract class Request {
	public enum Method {
		OPTIONS, GET, HEAD, POST, PUT, PATCH, DELETE, TRACE, CONNECT
	}

	public static Builder builder(){
		return new AutoValue_Request.Builder();
	}

	public abstract Method method();
	public abstract String baseUrl();
	@Nullable public abstract Map<String,String> parameters();
	@Nullable public abstract Map<String,String> headers();


	@AutoValue.Builder
	public abstract static class Builder {

		public abstract Builder method (Method method);
		public abstract Builder baseUrl (String baseString);
		public abstract Builder parameters (Map<String,String> parameters);
		public abstract Builder headers (Map<String,String> headers);

		public abstract Request build();
	}
}
