package com.qchu.feedly;

import com.qchu.feedly.load.parsed.entry.ParsedLoadEntryRoot;
import com.qchu.feedly.load.parsed.stream.ParsedLoadStreamRoot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Subscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by Quoc Dung Chu on 23/01/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoadServiceTests {

  private final static String FEED_ID = "feed/http://bongda.com.vn/rss/";

  @Mock CallBack<ParsedLoadStreamRoot> loadStreamCallBack;
  @Captor ArgumentCaptor<ParsedLoadStreamRoot> loadStreamCaptor;
  @Mock CallBack<ParsedLoadEntryRoot> loadEntryCallBack;
  @Captor ArgumentCaptor<ParsedLoadEntryRoot> loadEntryCaptor;

  @Test
  public void testLoadStream() {
    FeedlyApi.loadService().loadStream(FEED_ID)
      .subscribe(new Subscriber<ParsedLoadStreamRoot>() {
        @Override
        public void onCompleted() {
          loadStreamCallBack.onComplete();
        }

        @Override
        public void onError(Throwable e) {
          loadStreamCallBack.onError(e);
        }

        @Override
        public void onNext(ParsedLoadStreamRoot parsedLoadStreamRoot) {
          loadStreamCallBack.onNext(parsedLoadStreamRoot);
        }
      });

    verify(loadStreamCallBack, timeout(Constants.TIME_OUT).times(1))
      .onNext(loadStreamCaptor.capture());
    verify(loadStreamCallBack, timeout(Constants.TIME_OUT).times(1))
      .onComplete();
    verify(loadStreamCallBack, timeout(Constants.TIME_OUT).times(0))
      .onError(any(Throwable.class));

    ParsedLoadStreamRoot parsedLoadStreamRoot = loadStreamCaptor.getValue();
    System.out.println("----------- result : ---------");
    System.out.println(parsedLoadStreamRoot);

    assertThat(parsedLoadStreamRoot)
      .isNotNull();
    assertThat(parsedLoadStreamRoot.getId())
      .isEqualTo(FEED_ID);
  }

  @Test
  public void testLoadEntry(){
    FeedlyApi.loadService().loadEntry(FEED_ID)
      .subscribe(new Subscriber<ParsedLoadEntryRoot>() {
        @Override
        public void onCompleted() {
          loadEntryCallBack.onComplete();
        }

        @Override
        public void onError(Throwable e) {
          loadEntryCallBack.onError(e);
        }

        @Override
        public void onNext(ParsedLoadEntryRoot parsedLoadEntryRoot) {
          loadEntryCallBack.onNext(parsedLoadEntryRoot);
        }
      });

    verify(loadEntryCallBack, timeout(Constants.TIME_OUT).times(1))
      .onNext(loadEntryCaptor.capture());
    verify(loadEntryCallBack, timeout(Constants.TIME_OUT).times(1))
      .onComplete();
    verify(loadEntryCallBack, timeout(Constants.TIME_OUT).times(0))
      .onError(any(Throwable.class));

    ParsedLoadEntryRoot parsedLoadEntryRoot = loadEntryCaptor.getValue();
    System.out.println("----------- result : ---------");
    System.out.println(parsedLoadEntryRoot);

    assertThat(parsedLoadEntryRoot)
      .isNotNull();
  }
}
