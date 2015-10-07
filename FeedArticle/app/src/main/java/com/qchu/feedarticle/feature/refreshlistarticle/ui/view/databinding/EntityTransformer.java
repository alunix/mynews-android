package com.qchu.feedarticle.feature.refreshlistarticle.ui.view.databinding;

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
				.title(article.getTitle())
				.publicationDate("")
				.imageURL(article.getMainImage() != null? article.getMainImage().getUrl(): null)
				.imageWidth(article.getMainImage() != null? article.getMainImage().getWidth(): 0)
				.imageHeight(article.getMainImage() != null? article.getMainImage().getHeight(): 0)
				.build());
		}

		return bindableArticleList;
	}
}
