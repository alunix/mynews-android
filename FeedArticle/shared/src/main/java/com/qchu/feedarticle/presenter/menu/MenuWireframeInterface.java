package com.qchu.feedarticle.presenter.menu;

/**
 * Created by quocdungchu on 20/09/15.
 */
public interface MenuWireframeInterface {
	enum MenuItem {
		HOME,
		SEARCH
	}
	void presentUserInterfaceForMenuItem(MenuItem menuItem);
}
