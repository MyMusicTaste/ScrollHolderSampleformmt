package com.mymusictaste.scrollholdersample.fragment;

import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymusictaste.scrollholdersample.R;
import com.mymusictaste.scrollholdersample.activity.MainActivity;
import com.mymusictaste.scrollholdersample.util.AppUtil;
import com.mymusictaste.scrollholdersample.util.Constants;
import com.mymusictaste.scrollholdersample.util.ObservableScrollView;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.DefaultHeaderTransformer;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * Created by JKtheUnique on 2014-12-04.
 */
public class SampleScrollFragment extends ScrollHolderFragment {
    private int fragmentPosition = 0;
    private ObservableScrollView sampleScroll;
    private PullToRefreshLayout mPullToRefreshLayout;

    public static SampleScrollFragment getNewFragment(Bundle args){
        SampleScrollFragment fragment = new SampleScrollFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_sample_scroll,null);
        return view;
    }

    @Override
    public void adjustScroll(int scrollHeight) {
        int height = scrollHeight+1;
        if(height< getActivity().getResources().getDimensionPixelSize(R.dimen.header_height))
            ((MainActivity)getActivity()).setSampleScroll();
    }

    @Override
    public void initParams(){
        fragmentPosition = getArguments().getInt(Constants.FRAGMENT_POSITION, 0);
    }

    @Override
    public void initUi(){
        sampleScroll = (ObservableScrollView) getView().findViewById(R.id.setting_scroll);
        mPullToRefreshLayout = (PullToRefreshLayout) getView().findViewById(R.id.ptr_layout);
    }

    @Override
    public void populateUi(){
        sampleScroll.setOnScrollListener(new ObservableScrollView.OnScrollListener() {
            @Override
            public void onScrollChanged(int deltaX, int deltaY) {
                if (mScrollTabHolder != null)
                    mScrollTabHolder.onScroll(sampleScroll, fragmentPosition);
            }
        });

        ActionBarPullToRefresh.from(getActivity())
                // Mark All Children as pullable
                .allChildrenArePullable()
                        // Set a OnRefreshListener
                .listener(new OnRefreshListener() {
                    @Override
                    public void onRefreshStarted(View view) {
                        mPullToRefreshLayout.setRefreshComplete();
                    }
                })
                        // Finally commit the setup to our PullToRefreshLayout
                .setup(mPullToRefreshLayout);
        DefaultHeaderTransformer transformer = (DefaultHeaderTransformer) mPullToRefreshLayout.getHeaderTransformer();
        transformer.getHeaderView().findViewById(R.id.ptr_text).setBackgroundColor(getResources().getColor(R.color.refresh_background));
        transformer.setProgressBarColor(getResources().getColor(R.color.refresh_progress));
        transformer.setPullText(getString(R.string.ptr_pull_text));
        transformer.setRefreshingText(getString(R.string.ptr_loading_text));
    }

}
