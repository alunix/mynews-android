package com.qchu.rss;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.qchu.common.Log;
import com.qchu.rss.entity.Article;
import com.qchu.rss.entity.Channel;
import com.qchu.rss.entity.Image;
import com.qchu.rss.parsed.html.HtmlParser;
import com.qchu.rss.parsed.html.ParsedImage;
import com.qchu.rss.parsed.xml.ParsedChannel;
import com.qchu.rss.parsed.xml.ParsedItem;
import com.qchu.rss.parsed.xml.ParsedLink;
import com.qchu.rss.parsed.xml.ParsedRSS;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import autovalue.shaded.com.google.common.common.collect.Lists;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.SimpleXmlConverterFactory;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Quoc Dung Chu on 02/01/16.
 */
@Singleton
public class DefaultRssApi implements RssApi {

  private final static String TAG = "rss:DefaultRssApi";
  private final Scheduler observedOnScheduler;
  private final Scheduler subscribedOnScheduler;
  private final Log log;
  private DateFormat dateFormat;

  @Inject
  public DefaultRssApi(
    @Named(Constants.SCHEDULER_OBSERVED) Scheduler observedOnScheduler,
    @Named(Constants.SCHEDULER_SUBSCRIBED) Scheduler subscribedOnScheduler,
    Log log) {

    this.observedOnScheduler = observedOnScheduler;
    this.subscribedOnScheduler = subscribedOnScheduler;
    this.log = log;
  }

  @Override
  public void load(final String rssUrl, final OnLoadListener onLoadListener) {
    log.d(TAG, "load ...");
    if(onLoadListener != null) {
      onLoadListener.onStarted(rssUrl);
    }

    buildRetrofit(rssUrl).create(RSSService.class)
      .load("")
      .flatMap(new Func1<ParsedRSS, Observable<Channel>>() {
        @Override
        public Observable<Channel> call(ParsedRSS parsedRSS) {
          return  Observable.just(channelFrom(parsedRSS.channel));
        }
      })
      .subscribeOn(subscribedOnScheduler)
      .observeOn(observedOnScheduler)
      .subscribe(new Subscriber<Channel>() {
        @Override
        public void onCompleted() {
          log.d(TAG, "load:onCompleted for rssUrl " + rssUrl);
          log.d(TAG, "load:onCompleted in thread " + Thread.currentThread());

          if(onLoadListener != null) {
            onLoadListener.onCompleted(rssUrl);
          }
        }

        @Override
        public void onError(Throwable e) {
          log.e(TAG, "load:onError for rssUrl " + rssUrl + ", error " + e.getLocalizedMessage());
          log.d(TAG, "load:onError in thread " + Thread.currentThread());

          if(onLoadListener != null) {
            onLoadListener.onError(rssUrl, e);
          }
        }

        @Override
        public void onNext(Channel channel) {
          log.d(TAG, "load:onNext for rssUrl " + rssUrl + ", channel " + channel);
          log.d(TAG, "load:onNext in thread " + Thread.currentThread());

          if(onLoadListener != null) {
            onLoadListener.onNext(rssUrl, channel);
          }
        }
      });
  }

  private Channel channelFrom (ParsedChannel parsedChannel){
    if(parsedChannel == null) return null;

    String rssUrl = null;
    for(ParsedLink parsedLink: parsedChannel.links) {
      if(parsedLink.link != null) {
        rssUrl = parsedLink.link;
        break;
      }
    }

    List<Article> articles = Lists.newArrayList(Collections2.transform(parsedChannel.items,
      new Function<ParsedItem, Article>() {
        @Nullable @Override public Article apply(ParsedItem input) {
          return articleFrom(input);
        }
      }));

    return Channel.builder()
      .rssUrl(rssUrl)
      .title(parsedChannel.title)
      .articles(articles)
      .build();
  }

  private Article articleFrom (ParsedItem parsedItem) {
    if(parsedItem == null) return null;

    Date publishedDate = null;
    try {
      publishedDate = dateFormat().parse(parsedItem.pubDate);
    } catch (ParseException e) {
      log.e(TAG, e.getLocalizedMessage());
    }

    String content = parsedItem.content != null? parsedItem.content: parsedItem.description;

    List<ParsedImage> parsedImages = HtmlParser.getImagesFromHtml(content);

    List<Image> images = Lists.newArrayList(
      Collections2.transform(
        Collections2.filter(parsedImages,
          new Predicate<ParsedImage>() {
            @Override
            public boolean apply(@Nullable ParsedImage input) {
              return input != null && input.isValid();
            }
          }),
        new Function<ParsedImage, Image>() {
          @Nullable @Override
          public Image apply(@Nullable ParsedImage input) {
            return imageFrom(input);
          }
        })
      );

    return Article.builder()
      .link(parsedItem.link)
      .title(parsedItem.title)
      .content(content)
      .publicationDate(publishedDate)
      .summary(parsedItem.description)
      .mainImage(images.isEmpty() ? null: images.get(0))
      .images(images)
      .build();
  }

  private Image imageFrom (ParsedImage parsedImage){
    if(parsedImage == null) return null;

    return Image.builder()
      .url(parsedImage.src())
      .width(parsedImage.width())
      .height(parsedImage.height())
      .build();
  }

  private DateFormat dateFormat() {
    if (dateFormat == null) {
      dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
    }
    return dateFormat;
  }

  private static Retrofit buildRetrofit(String rssUrl) {
    Retrofit retrofit = new Retrofit.Builder()
      //.addConverterFactory(GsonConverterFactory.create())
      .addConverterFactory(SimpleXmlConverterFactory.create())
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .baseUrl(rssUrl)
      .build();
    return retrofit;
  }

  interface RSSService {
    @GET("{path}")
    Observable<ParsedRSS> load(@Path("path") String path);
  }
}
