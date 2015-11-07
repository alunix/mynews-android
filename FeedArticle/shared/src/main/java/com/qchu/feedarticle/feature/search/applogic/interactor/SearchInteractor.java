package com.qchu.feedarticle.feature.search.applogic.interactor;

import com.qchu.feedarticle.feature.search.applogic.entity.Result;
import com.qchu.feedarticle.feature.search.applogic.entity.Search;

import java.util.List;

/**
 * Created by quocdungchu on 27/10/15.
 */
public interface SearchInteractor {

	void launch (
		Search search,
		OnResultListener onResultListener);

	interface OnResultListener{
		void onResult (List<Result> resultList);
	}
}
