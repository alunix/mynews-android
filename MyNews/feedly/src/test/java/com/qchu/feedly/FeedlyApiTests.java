package com.qchu.feedly;

import com.qchu.feedly.load.parsed.stream.ParsedLoadStreamRoot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by Quoc Dung Chu on 23/01/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class FeedlyApiTests {

  @Mock CallBack<ParsedLoadStreamRoot> callBack;
  @Captor ArgumentCaptor<ParsedLoadStreamRoot> resultCaptor;

  @Test
  public void testLoadStream() {
    FeedlyApi.loadService().loadStream(
      "feed/http://bongda.com.vn/rss/"
    )
      .subscribe(new Subscriber<ParsedLoadStreamRoot>() {
        @Override
        public void onCompleted() {
          System.out.println("onCompleted ");
          callBack.onComplete();
        }

        @Override
        public void onError(Throwable e) {
          callBack.onError(e);
        }

        @Override
        public void onNext(ParsedLoadStreamRoot parsedLoadStreamRoot) {
          callBack.onNext(parsedLoadStreamRoot);
        }
      });

    verify(callBack, timeout(10 * 1000).times(1))
      .onNext(resultCaptor.capture());
    verify(callBack, timeout(10 * 1000).times(1))
      .onComplete();
    verify(callBack, timeout(10 * 1000).times(0))
      .onError(any(Throwable.class));

    ParsedLoadStreamRoot parsedLoadStreamRoot = resultCaptor.getValue();
    System.out.println("id : " + parsedLoadStreamRoot.getId());
    System.out.println("title : " + parsedLoadStreamRoot.getTitle());

    assertThat(parsedLoadStreamRoot)
      .isNotNull();
    assertThat(parsedLoadStreamRoot.getId())
      .isEqualTo("feed/http://bongda.com.vn/rss/");

  }
}
