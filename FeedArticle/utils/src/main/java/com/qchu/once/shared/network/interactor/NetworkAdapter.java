package com.qchu.once.shared.network.interactor;

import com.qchu.once.shared.network.interactor.Request;

/**
 * Created by quocdungchu on 01/11/15.
 */
public interface NetworkAdapter {
	void send(Request request, NetworkAdapterOnResponseListener networkAdapterOnResponseListener);
}
