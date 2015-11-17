package com.qchu.feedarticle.applogic.domain.article.interactor.comparator;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.qchu.feedarticle.applogic.domain.article.entity.Article;

import java.util.Comparator;

/**
 * Created by quocdungchu on 26/09/15.
 */
public class Comparators {

  public static Comparator<Article> asArticleChronology(){
    return new Comparator<Article>() {
      @Override
      public int compare(Article article1, Article article2) {
        return ComparisonChain.start()
          .compare(
            article2.publicationDate(),
            article1.publicationDate(),
            Ordering.natural().nullsLast())
          .result();
      }
    };
  }
}
