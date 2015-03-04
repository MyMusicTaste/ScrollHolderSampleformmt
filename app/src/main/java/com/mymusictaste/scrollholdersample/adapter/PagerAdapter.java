package com.mymusictaste.scrollholdersample.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;

import com.mymusictaste.scrollholdersample.fragment.SampleListFragment;
import com.mymusictaste.scrollholdersample.fragment.ScrollHolderFragment;
import com.mymusictaste.scrollholdersample.fragment.SampleScrollFragment;
import com.mymusictaste.scrollholdersample.util.Constants;
import com.mymusictaste.scrollholdersample.util.ScrollTabHolderPlus;

/**
 * Created by JKtheUnique on 2014-12-03.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private SparseArrayCompat<ScrollTabHolderPlus> mScrollTabHolders;
    private final String[] TITLES = Constants.TAB_LIST;
    private ScrollTabHolderPlus mListener;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        mScrollTabHolders = new SparseArrayCompat<ScrollTabHolderPlus>();
    }

    public void setTabHolderScrollingContent(ScrollTabHolderPlus listener) {
        mListener = listener;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        args.putInt(Constants.FRAGMENT_POSITION,position);
        if(position == Constants.POSITION_VIDEO)
            args.putInt(Constants.QUERY_TYPE,Constants.TYPE_VIDEO);
        else if(position == Constants.POSITION_PLAYLIST)
            args.putInt(Constants.QUERY_TYPE, Constants.TYPE_PLAYLIST);
        else
            args.putInt(Constants.QUERY_TYPE,Constants.TYPE_VIDEO);

        ScrollHolderFragment fragment;
        if(position % 2 != 0 )
            fragment= SampleScrollFragment.getNewFragment(args);
        else
            fragment= SampleListFragment.getNewFragment(args);

        mScrollTabHolders.put(position, fragment);
        if (mListener != null) {
            fragment.setScrollTabHolder(mListener);
        }

        return fragment;
    }

    public SparseArrayCompat<ScrollTabHolderPlus> getScrollTabHolders() {
        return mScrollTabHolders;
    }

}