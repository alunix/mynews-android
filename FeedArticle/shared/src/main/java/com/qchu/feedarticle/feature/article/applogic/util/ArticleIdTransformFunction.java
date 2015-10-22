package com.qchu.feedarticle.feature.article.applogic.util;

import com.google.common.base.Function;
import com.qchu.feedarticle.feature.article.applogic.entity.Article;

import javax.annotation.Nullable;

/**
 * Created by quocdungchu on 22/10/15.
 */
public class ArticleIdTransformFunction implements Function<Article, String> {
	@Nullable @Override
	public String apply(Article article) {
		return article.identifier();
	}
}
