package com.qchu.feedarticle.ui.view.search;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.qchu.feedarticle.R;
import com.qchu.feedarticle.ui.common.BaseFragment;
import com.qchu.feedarticle.ui.common.IntentController;

/**
 * Created by louischu on 18/11/15.
 */
public class SearchFragment extends BaseFragment {

  private static final String TAG = "SearchFragment";
  private SearchView searchView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);

    activityComponent().titleController().title(getString(R.string.title_search));
    activityComponent().intentController().addHandler(new IntentController.Handler() {
      @Override
      public void onHandleNewIntent(Intent newIntent) {
        if (Intent.ACTION_SEARCH.equals(newIntent.getAction())) {
          String query = newIntent.getStringExtra(SearchManager.QUERY);

          Log.d(TAG, "on search : query " + query);
        }
      }
    });
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_fragment_search, menu);
    MenuItem searchItem = menu.findItem(R.id.search);
    searchView = (SearchView) searchItem.getActionView();
    setupSearchView(searchItem);
  }

  private void setupSearchView(MenuItem searchItem) {
    searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM
      | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

    SearchManager searchManager = (SearchManager) getActivity().getSystemService(
      Context.SEARCH_SERVICE);
    if (searchManager != null) {
      SearchableInfo info = searchManager.getSearchableInfo(getActivity().getComponentName());
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
