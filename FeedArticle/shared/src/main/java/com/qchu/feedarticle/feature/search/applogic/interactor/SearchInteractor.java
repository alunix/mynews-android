package com.qchu.feedarticle.feature.search.applogic.interactor;

import com.qchu.feedarticle.feature.article.applogic.entity.Site;
import com.qchu.feedarticle.feature.search.applogic.entity.Result;
import com.qchu.feedarticle.feature.search.applogic.entity.Search;

import java.util.List;

/**
 * Created by quocdungchu on 27/10/15.
 */
public interface SearchInteractor<T> {

	void launch (
		Search search,
		OnResultListener<T> onResultListener);

	interface OnResultListener<T>{
		void onResult (List<Result<T>> resultList);
	}
}
