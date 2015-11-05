package com.qchu.once.shared.network.entity;

/**
 * Created by quocdungchu on 31/10/15.
 */
public class Response <Parsed> {

	Parsed body;
	int statusCode;

	public Response (Parsed body, int statusCode){
		this.body = body;
		this.statusCode = statusCode;
	}

	public Parsed body() {
		return this.body;
	}

	public int statusCode(){
		return this.statusCode;
	}
}
