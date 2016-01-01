package com.qchu.mynews.ui.search;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.qchu.mynews.R;
import com.qchu.mynews.ui.common.BaseFragment;

/**
 * Created by Quoc Dung Chu on 31/12/15.
 */
public class SearchFragment extends BaseFragment {

  private final static String TAG = "SearchFragment";
  private SearchFragmentDataBinding dataBinding;

  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    this.dataBinding = SearchFragmentDataBinding.inflate(inflater, container, false);
    dataBinding.recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
    return dataBinding.getRoot();
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
          appComponent().log().d(TAG, "on menu action expand");
          return true;
        }

        @Override
        public boolean onMenuItemActionCollapse(MenuItem item) {
          appComponent().log().d(TAG, "on menu action collapse");
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
