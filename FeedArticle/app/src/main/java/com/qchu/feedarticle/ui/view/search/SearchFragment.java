package com.qchu.feedarticle.ui.view.search;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.qchu.feedarticle.R;
import com.qchu.feedarticle.applogic.domain.search.entity.Entry;
import com.qchu.feedarticle.applogic.domain.search.interactor.OnResultListener;
import com.qchu.feedarticle.applogic.domain.search.interactor.SearchError;
import com.qchu.feedarticle.ui.common.BaseFragment;
import com.qchu.feedarticle.ui.common.IntentController;
import com.qchu.feedarticle.ui.view.search.databinding.SearchFragmentDataBinding;

import java.util.List;

/**
 * Created by louischu on 18/11/15.
 */
public class SearchFragment extends BaseFragment {

  private static final String TAG = "SearchFragment";
  private SearchFragmentDataBinding dataBinding;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);

    activityComponent().titleController().title(getString(R.string.title_search));
    activityComponent().intentController().addHandler(new IntentController.Handler() {
      @Override
      public void onHandleNewIntent(Intent newIntent) {
        if (Intent.ACTION_SEARCH.equals(newIntent.getAction())) {
          final String query = newIntent.getStringExtra(SearchManager.QUERY);

          Log.d(TAG, "on search : query " + query);

          appComponent().searchInterator().search(query, new OnResultListener() {
            @Override
            public void onResult(List<Entry> entries, SearchError searchError, String errorMessage) {
              Log.d(TAG, "on result : query " + query + ", results :" + entries);
            }
          });
        }
      }
    });
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    this.dataBinding = SearchFragmentDataBinding.inflate(inflater, container, false);
    return this.dataBinding.getRoot();
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_fragment_search, menu);
    MenuItem searchItem = menu.findItem(R.id.search);
    setupSearchView(searchItem);
    MenuItemCompat.setOnActionExpandListener(searchItem,
      new MenuItemCompat.OnActionExpandListener() {
        @Override
        public boolean onMenuItemActionExpand(MenuItem item) {
          Log.d(TAG, "on menu action expand");
          return true;
        }

        @Override
        public boolean onMenuItemActionCollapse(MenuItem item) {
          Log.d(TAG, "on menu action collapse");
          return true;
        }
      });
  }

  private void setupSearchView(MenuItem searchItem) {
    searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM
      | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

    SearchManager searchManager = (SearchManager) getActivity().getSystemService(
      Context.SEARCH_SERVICE);
    if (searchManager != null) {
      SearchableInfo info = searchManager.getSearchableInfo(getActivity().getComponentName());
      SearchView searchView = (SearchView) searchItem.getActionView();
      searchView.setSearchableInfo(info);
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.search) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
