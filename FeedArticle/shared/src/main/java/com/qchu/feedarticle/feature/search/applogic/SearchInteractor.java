package com.qchu.feedarticle.feature.search.applogic;

import com.qchu.feedarticle.feature.article.applogic.entity.Site;

import java.util.List;

/**
 * Created by quocdungchu on 27/10/15.
 */
public interface SearchInteractor {

	void search(String query, OnSearchListener onSearchListener);

	interface OnSearchListener{
		void onSearch(List<Site> siteList);
	}
}
