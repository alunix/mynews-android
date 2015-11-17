package com.qchu.feedarticle.applogic.domain.article.interactor;

import com.qchu.feedarticle.applogic.domain.article.entity.ChannelConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by quocdungchu on 07/09/15.
 */
@Singleton
public class DefaultArticleInteractor implements ArticleInteractor {

	SourceRepository sourceRepository;
	ArticleRepository articleRepository;

	@Inject public DefaultArticleInteractor(SourceRepository sourceRepository,
	                                        ArticleRepository articleRepository) {
		this.sourceRepository = sourceRepository;
		this.articleRepository = articleRepository;
	}

	@Override
	public List<com.qchu.feedarticle.applogic.domain.article.entity.Article> getArticleListInRepositoryBySiteIds(List<String> siteIdList){
		List<com.qchu.feedarticle.applogic.domain.article.entity.Article> sortedArticleList = articleRepository.getArticleBySiteIds(siteIdList);
		Collections.sort(sortedArticleList, new DescentDateSortArticleComparator());
		return sortedArticleList;
	}

	@Override
	public List<com.qchu.feedarticle.applogic.domain.article.entity.Article> getArticleListInRepositoryByArticleIds(List<String> articleIdList){
		List<com.qchu.feedarticle.applogic.domain.article.entity.Article> sortedArticleList = articleRepository.getArticleByArticleIds(articleIdList);
		Collections.sort(sortedArticleList, new DescentDateSortArticleComparator());
		return sortedArticleList;
	}

	@Override
	public com.qchu.feedarticle.applogic.domain.article.entity.Article getArticleInRepositoryByArticleId(String articleId) {
		return articleRepository.getArticleById(articleId);
	}

	@Override
	public void refreshArticles(List<ChannelConfig> channelConfigList,
	                            final RefreshArticleListListener refreshArticleListListener){

		final List<com.qchu.feedarticle.applogic.domain.article.entity.Article> allArticleSortedList = new ArrayList<>();
		sourceRepository.getArticles(channelConfigList, new SourceRepository.GetArticleListListener() {
			@Override
			public void onBegin(SourceRepository sourceRepository) {
				refreshArticleListListener.onBegin(DefaultArticleInteractor.this);
			}

			@Override
			public void onNext(SourceRepository sourceRepository, ChannelConfig channelConfig, com.qchu.feedarticle.applogic.domain.article.entity.Channel channel) {
				//update repository
				articleRepository.updateSite(channel);

				allArticleSortedList.addAll(channel.articleList());
				Collections.sort(allArticleSortedList, new DescentDateSortArticleComparator());
				refreshArticleListListener.onNextSite(DefaultArticleInteractor.this, channel, allArticleSortedList);
			}

			@Override
			public void onComplete(SourceRepository sourceRepository) {
				Collections.sort(allArticleSortedList, new DescentDateSortArticleComparator());
				refreshArticleListListener.onComplete(DefaultArticleInteractor.this, allArticleSortedList);
			}
		});
	}



}
