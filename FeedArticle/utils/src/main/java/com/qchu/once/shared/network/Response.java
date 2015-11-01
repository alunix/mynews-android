package com.qchu.once.shared.network;

import com.google.auto.value.AutoValue;

/**
 * Created by quocdungchu on 31/10/15.
 */
public class Response <T> {

	T body;
	int statusCode;

	public Response (T body, int statusCode){
		this.body = body;
		this.statusCode = statusCode;
	}

	public T body() {
		return this.body;
	}

	public int statusCode(){
		return this.statusCode;
	}
}
