package com.qchu.feedarticle.feature.listarticle.applogic.interactor;

import com.qchu.feedarticle.feature.listarticle.applogic.entity.Article;
import com.qchu.feedarticle.feature.listarticle.applogic.entity.Site;
import com.qchu.feedarticle.feature.listarticle.applogic.entity.SiteConfig;

import java.util.List;
import java.util.Map;

/**
 * Created by quocdungchu on 07/09/15.
 */
public class ArticleInteractor {

	DataAdapter mDataAdapter;

	public ArticleInteractor(DataAdapter dataAdapter) {
		mDataAdapter = dataAdapter;
	}

	public void getArticle(List<SiteConfig> siteConfigList,
	                       GetArticleListListener getArticleListListener){
		mDataAdapter.getArticles(siteConfigList, getArticleListListener);
	}

	public interface DataAdapter {
		void getArticles(List<SiteConfig> siteConfigList, GetArticleListListener getArticleListListener);
	}

	public interface GetArticleListListener {
		void onBegin();
		void onNext(SiteConfig siteConfig, List<Article> articles, Site site);
		void onFinish();
	}

	public interface BroadcastAdapter {
		void sendBroadcast(String name, Map<String, Object> userInfo);
	}

}
