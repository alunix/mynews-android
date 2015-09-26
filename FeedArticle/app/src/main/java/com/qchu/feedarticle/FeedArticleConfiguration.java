package com.qchu.feedarticle;

import com.qchu.feedarticle.feature.listarticle.applogic.interactor.ArticleInteractor;
import com.qchu.feedarticle.feature.listarticle.applogic.manager.data.ArticleDataManager;

import lombok.Getter;

/**
 * Created by quocdungchu on 20/09/15.
 */
public class FeedArticleConfiguration {

	static FeedArticleConfiguration sFeedArticleConfiguration;

	@Getter ArticleInteractor articleInteractor;

	public static FeedArticleConfiguration get() {
		if(sFeedArticleConfiguration == null) {
			sFeedArticleConfiguration = new FeedArticleConfiguration();
		}
		return sFeedArticleConfiguration;
	}

	FeedArticleConfiguration(){
		articleInteractor = new ArticleInteractor(new ArticleDataManager());
	}
}
