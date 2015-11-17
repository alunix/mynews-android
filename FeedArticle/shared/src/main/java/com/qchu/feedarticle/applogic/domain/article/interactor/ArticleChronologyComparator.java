package com.qchu.feedarticle.applogic.domain.article.interactor;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.qchu.feedarticle.applogic.domain.article.entity.Article;

import java.util.Comparator;

/**
 * Created by quocdungchu on 26/09/15.
 */
public class ArticleChronologyComparator implements Comparator<Article> {
	@Override
	public int compare(Article lhs, Article rhs) {
		return ComparisonChain.start()
			.compare(
				rhs.publicationDate(),
				lhs.publicationDate(),
				Ordering.natural().nullsLast())
			.result();
	}
}
