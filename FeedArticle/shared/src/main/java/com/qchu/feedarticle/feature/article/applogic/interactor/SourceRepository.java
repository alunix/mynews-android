package com.qchu.feedarticle.feature.article.applogic.interactor;

import com.qchu.feedarticle.feature.article.applogic.entity.Site;
import com.qchu.feedarticle.feature.article.applogic.entity.SiteConfig;

import java.util.List;

/**
 * Created by quocdungchu on 28/09/15.
 */
public interface SourceRepository {
	void getArticles(List<SiteConfig> siteConfigList,
	                 GetArticleListListener getArticleListListener);
	interface GetArticleListListener {
		void onBegin(SourceRepository sourceRepository);
		void onNext(SourceRepository sourceRepository, SiteConfig siteConfig, Site site);
		void onComplete(SourceRepository sourceRepository);
	}
}
