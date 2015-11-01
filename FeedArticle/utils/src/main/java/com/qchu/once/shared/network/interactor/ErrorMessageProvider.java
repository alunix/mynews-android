package com.qchu.once.shared.network.interactor;

/**
 * Created by quocdungchu on 01/11/15.
 */
public interface ErrorMessageProvider {
	String messageForNoInternetConnection();
	String message(int statusCode, String responseErrorMessage);
}
