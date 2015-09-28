package com.qchu.feedarticle.feature.article.applogic.interactor;

import com.qchu.feedarticle.feature.article.applogic.entity.Site;
import com.qchu.feedarticle.feature.article.applogic.entity.SiteConfig;

import java.util.List;

/**
 * Created by quocdungchu on 28/09/15.
 */
public interface NetworkAdapter {
	void getArticles(List<SiteConfig> siteConfigList,
	                 GetArticleListListener getArticleListListener);
	interface GetArticleListListener {
		void onBegin(NetworkAdapter networkAdapter);
		void onNext(NetworkAdapter networkAdapter, SiteConfig siteConfig, Site site);
		void onComplete(NetworkAdapter networkAdapter);
	}
}
