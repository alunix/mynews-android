package com.qchu.feedarticle.feature.search.applogic.interactor;

/**
 * Created by quocdungchu on 05/11/15.
 */
public interface Transform <From, To> {
	To doTransform (From from);
}
