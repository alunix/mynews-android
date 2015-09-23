package com.qchu.feedarticle.feature.menu.ui.presenter;

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
