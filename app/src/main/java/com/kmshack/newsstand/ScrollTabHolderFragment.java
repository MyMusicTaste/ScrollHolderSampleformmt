package com.kmshack.newsstand;

import android.support.v4.app.Fragment;
import android.widget.AbsListView;

public abstract class ScrollTabHolderFragment extends Fragment implements com.kmshack.newsstand.ScrollTabHolder {

	protected com.kmshack.newsstand.ScrollTabHolder mScrollTabHolder;

	public void setScrollTabHolder(com.kmshack.newsstand.ScrollTabHolder scrollTabHolder) {
		mScrollTabHolder = scrollTabHolder;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition) {
		// nothing
	}

}