package com.qchu.feedarticle.feature.article.applogic.interactor;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.article.applogic.entity.Site;
import com.qchu.feedarticle.feature.article.applogic.entity.SiteConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by quocdungchu on 07/09/15.
 */
@Singleton
public class DefaultArticleInteractor implements ArticleInteractor{

	SourceRepository sourceRepository;
	ArticleRepository articleRepository;

	@Inject public DefaultArticleInteractor(SourceRepository sourceRepository,
	                                        ArticleRepository articleRepository) {
		this.sourceRepository = sourceRepository;
		this.articleRepository = articleRepository;
	}

	@Override
	public List<Article> getArticleListInRepositoryBySiteIds(List<String> siteIdList){
		List<Article> sortedArticleList = articleRepository.getArticleBySiteIds(siteIdList);
		Collections.sort(sortedArticleList, new DescentDateSortArticleComparator());
		return sortedArticleList;
	}

	@Override
	public List<Article> getArticleListInRepositoryByArticleIds(List<String> articleIdList){
		List<Article> sortedArticleList = articleRepository.getArticleByArticleIds(articleIdList);
		Collections.sort(sortedArticleList, new DescentDateSortArticleComparator());
		return sortedArticleList;
	}

	@Override
	public Article getArticleInRepositoryByArticleId(String articleId) {
		return articleRepository.getArticleById(articleId);
	}

	@Override
	public void refreshArticles(List<SiteConfig> siteConfigList,
	                            final RefreshArticleListListener refreshArticleListListener){

		final List<Article> allArticleSortedList = new ArrayList<>();
		sourceRepository.getArticles(siteConfigList, new SourceRepository.GetArticleListListener() {
			@Override
			public void onBegin(SourceRepository sourceRepository) {
				refreshArticleListListener.onBegin(DefaultArticleInteractor.this);
			}

			@Override
			public void onNext(SourceRepository sourceRepository, SiteConfig siteConfig, Site site) {
				//update repository
				articleRepository.updateSite(site);

				allArticleSortedList.addAll(site.articleList());
				Collections.sort(allArticleSortedList, new DescentDateSortArticleComparator());
				refreshArticleListListener.onNextSite(DefaultArticleInteractor.this, site, allArticleSortedList);
			}

			@Override
			public void onComplete(SourceRepository sourceRepository) {
				Collections.sort(allArticleSortedList, new DescentDateSortArticleComparator());
				refreshArticleListListener.onComplete(DefaultArticleInteractor.this, allArticleSortedList);
			}
		});
	}



}
