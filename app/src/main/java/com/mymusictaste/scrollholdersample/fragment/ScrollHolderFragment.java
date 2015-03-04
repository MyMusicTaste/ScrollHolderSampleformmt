package com.mymusictaste.scrollholdersample.fragment;

import android.os.Bundle;
import android.widget.ScrollView;

import com.kmshack.newsstand.ScrollTabHolderFragment;
import com.mymusictaste.scrollholdersample.util.ScrollTabHolderPlus;

abstract public class ScrollHolderFragment extends ScrollTabHolderFragment implements ScrollTabHolderPlus {

    ScrollTabHolderPlus mScrollTabHolder;

    public ScrollHolderFragment() {
    }

    public void setScrollTabHolder(ScrollTabHolderPlus scrollTabHolder) {
        mScrollTabHolder = scrollTabHolder;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initParams();
        initUi();
        populateUi();
    }

    abstract public void initParams();
    abstract public void initUi();
    abstract public void populateUi();

    public void onScroll(ScrollView view, int pagePosition){

    }
}
