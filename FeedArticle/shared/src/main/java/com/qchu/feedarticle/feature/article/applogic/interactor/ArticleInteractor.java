package com.qchu.feedarticle.feature.article.applogic.interactor;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;
import com.qchu.feedarticle.feature.article.applogic.entity.Site;
import com.qchu.feedarticle.feature.article.applogic.entity.SiteConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by quocdungchu on 07/09/15.
 */
public class ArticleInteractor {

	SourceRepository sourceRepository;
	ArticleRepository articleRepository;

	public ArticleInteractor(SourceRepository sourceRepository, ArticleRepository articleRepository) {
		this.sourceRepository = sourceRepository;
		this.articleRepository = articleRepository;
	}

	public List<Article> getArticleListInRepositoryBySiteIds(List<String> siteIdList){
		List<Article> sortedArticleList = articleRepository.getArticleBySiteIds(siteIdList);
		Collections.sort(sortedArticleList, new DescentDateSortArticleComparator());
		return sortedArticleList;
	}

	public List<Article> getArticleListInRepositoryByArticleIds(List<String> articleIdList){
		List<Article> sortedArticleList = articleRepository.getArticleByArticleIds(articleIdList);
		Collections.sort(sortedArticleList, new DescentDateSortArticleComparator());
		return sortedArticleList;
	}

	public Article getArticleInRepositoryByArticleId(String articleId) {
		return articleRepository.getArticleById(articleId);
	}

	public void refreshArticles(List<SiteConfig> siteConfigList,
	                            final RefreshArticleListListener refreshArticleListListener){

		final List<Article> allArticleSortedList = new ArrayList<>();
		sourceRepository.getArticles(siteConfigList, new SourceRepository.GetArticleListListener() {
			@Override
			public void onBegin(SourceRepository sourceRepository) {
				refreshArticleListListener.onBegin(ArticleInteractor.this);
			}

			@Override
			public void onNext(SourceRepository sourceRepository, SiteConfig siteConfig, Site site) {
				//update repository
				articleRepository.updateSite(site);

				allArticleSortedList.addAll(site.articleList());
				Collections.sort(allArticleSortedList, new DescentDateSortArticleComparator());
				refreshArticleListListener.onNextSite(ArticleInteractor.this, site, allArticleSortedList);
			}

			@Override
			public void onComplete(SourceRepository sourceRepository) {
				Collections.sort(allArticleSortedList, new DescentDateSortArticleComparator());
				refreshArticleListListener.onComplete(ArticleInteractor.this, allArticleSortedList);
			}
		});
	}

	public interface RefreshArticleListListener {
		void onBegin(ArticleInteractor articleInteractor);
		void onNextSite(ArticleInteractor articleInteractor, Site site,
		                List<Article> allArticleSortedList);
		void onComplete(ArticleInteractor articleInteractor, List<Article> articleList);
	}

}
