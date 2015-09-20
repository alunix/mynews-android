package com.qchu.feedarticle.feature.listarticle.applogic.interactor;

import com.qchu.feedarticle.feature.listarticle.applogic.entity.Article;
import com.qchu.feedarticle.feature.listarticle.applogic.entity.Site;
import com.qchu.feedarticle.feature.listarticle.applogic.entity.SiteConfig;

import java.util.List;

/**
 * Created by quocdungchu on 07/09/15.
 */
public class ArticleInteractor {

	DataAdapter mDataAdapter;

	public ArticleInteractor(DataAdapter dataAdapter) {
		mDataAdapter = dataAdapter;
	}

	public void getArticle(List<SiteConfig> siteConfigList, GetArticleListCallback getArticleListCallback){
		mDataAdapter.getArticles(siteConfigList, getArticleListCallback);
	}

	public interface DataAdapter {
		void getArticles(List<SiteConfig> siteConfigList, GetArticleListCallback getArticleListCallback);
	}

	public interface GetArticleListCallback {
		void onBegin();
		void onNext(SiteConfig siteConfig, List<Article> articles, Site site);
		void onFinish();
	}

}
