package com.qchu.once.shared.network.base;

/**
 * Created by quocdungchu on 01/11/15.
 */
public interface NetworkAdapterOnResponseListener {
	void onResponse(String responseBodyString, int statusCode);
}
