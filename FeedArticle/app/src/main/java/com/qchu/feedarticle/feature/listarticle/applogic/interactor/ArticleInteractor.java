package com.qchu.feedarticle.feature.listarticle.applogic.interactor;

import com.qchu.feedarticle.feature.listarticle.applogic.entity.Article;
import com.qchu.feedarticle.feature.listarticle.applogic.entity.Site;
import com.qchu.feedarticle.feature.listarticle.applogic.entity.SiteConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by quocdungchu on 07/09/15.
 */
public class ArticleInteractor {

	DataAdapter mDataAdapter;

	public ArticleInteractor(DataAdapter dataAdapter) {
		mDataAdapter = dataAdapter;
	}

	public void getArticle(List<SiteConfig> siteConfigList,
	                       final GetArticleListListener getArticleListListener){

		final List<Article> allArticleList = new ArrayList<>();
		mDataAdapter.getArticles(siteConfigList, new DataAdapter.GetArticleListListener() {
			@Override
			public void onBegin() {
				getArticleListListener.onBegin(ArticleInteractor.this);
			}

			@Override
			public void onNext(SiteConfig siteConfig, List<Article> siteArticles, Site site) {
				allArticleList.addAll(siteArticles);
			}

			@Override
			public void onFinish() {
				Collections.sort(allArticleList, new DescentDateSortArticleComparator());
				getArticleListListener.onFinish(ArticleInteractor.this, allArticleList);
			}
		});
	}

	public interface DataAdapter {
		void getArticles(List<SiteConfig> siteConfigList,
		                 GetArticleListListener getArticleListListener);
		interface GetArticleListListener {
			void onBegin();
			void onNext(SiteConfig siteConfig, List<Article> articles, Site site);
			void onFinish();
		}
	}

	public interface GetArticleListListener {
		void onBegin(ArticleInteractor articleInteractor);
		void onFinish(ArticleInteractor articleInteractor, List<Article> articleList);
	}

}
