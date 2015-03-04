package com.mymusictaste.scrollholdersample.util;

import android.widget.AbsListView;
import android.widget.ScrollView;

public interface ScrollTabHolderPlus extends com.kmshack.newsstand.ScrollTabHolder{

    @Override
	void adjustScroll(int scrollHeight);

    @Override
	void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition);

    void onScroll(ScrollView view, int pagePosition);
}
