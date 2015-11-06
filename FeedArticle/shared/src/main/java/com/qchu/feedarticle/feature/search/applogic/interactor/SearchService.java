package com.qchu.feedarticle.feature.search.applogic.interactor;

/**
 * Created by quocdungchu on 30/10/15.
 */
public interface SearchService {
	void search (String query);

	interface OnResponseListener {
		void onResponse();
	}
}
