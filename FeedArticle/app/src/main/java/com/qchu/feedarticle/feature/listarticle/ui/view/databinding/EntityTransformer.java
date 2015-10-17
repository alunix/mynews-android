package com.qchu.feedarticle.feature.listarticle.ui.view.databinding;

import com.qchu.feedarticle.feature.article.applogic.entity.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quocdungchu on 26/09/15.
 */
public class EntityTransformer {
	public static List<BindableArticle> boundArticleList(List<Article> articleList) {
		List<BindableArticle> bindableArticleList = new ArrayList<>();

		for(Article article: articleList) {
			bindableArticleList.add(BindableArticle.builder()
				.title(article.title())
				.publicationDate("")
				.imageURL(article.mainImage() != null? article.mainImage().url(): null)
				.imageWidth(article.mainImage() != null? article.mainImage().width(): 0)
				.imageHeight(article.mainImage() != null? article.mainImage().height(): 0)
				.build());
		}

		return bindableArticleList;
	}
}
