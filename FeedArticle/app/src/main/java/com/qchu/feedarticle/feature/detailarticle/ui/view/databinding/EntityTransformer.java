package com.qchu.feedarticle.feature.detailarticle.ui.view.databinding;

import com.qchu.feedarticle.domain.article.entity.Article;

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
				.title(article.title())
				.content(article.content())
				.publicationDate("")
				.imageURL(article.mainImage() != null? article.mainImage().url(): null)
				.build());
		}

		return bindableArticleList;
	}
}
