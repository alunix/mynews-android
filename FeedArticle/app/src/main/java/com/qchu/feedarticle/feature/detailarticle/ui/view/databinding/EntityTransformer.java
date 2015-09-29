package com.qchu.feedarticle.feature.detailarticle.ui.view.databinding;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quocdungchu on 29/09/15.
 */
public class EntityTransformer {
	public static List<BindableArticle> bindableArticleList(List<Article> articleList) {
		List<BindableArticle> bindableArticleList = new ArrayList<>();

		for(Article article: articleList) {
			bindableArticleList.add(BindableArticle.builder()
				.title(article.getTitle())
				.content(article.getContent())
				.publicationDate("")
				.build());
		}

		return bindableArticleList;
	}
}
