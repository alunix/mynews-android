package com.qchu.feedarticle.feature.favorite.applogic.interactor;

/**
 * Created by quocdungchu on 17/10/15.
 */
public enum FavoriteActionResult {
	ADD_SUCCESSFUL,
	ADD_FAILED_REASON_EXIST_ALREADY,
	ADD_FAILED_REASON_OTHER,
	REMOVE_SUCCESSFUL,
	REMOVE_FAILED_REASON_NOT_EXIST,
	REMOVE_FAILED_REASON_OTHER
}
