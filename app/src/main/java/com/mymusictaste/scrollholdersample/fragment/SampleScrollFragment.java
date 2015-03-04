package com.mymusictaste.scrollholdersample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymusictaste.scrollholdersample.R;
import com.mymusictaste.scrollholdersample.activity.MainActivity;
import com.mymusictaste.scrollholdersample.util.Constants;
import com.mymusictaste.scrollholdersample.util.ObservableScrollView;

/**
 * Created by JKtheUnique on 2014-12-04.
 */
public class SampleScrollFragment extends ScrollHolderFragment {
    private int fragmentPosition = 0;
    private ObservableScrollView settingScroll;

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
            ((MainActivity)getActivity()).setSettingScroll();
    }

    @Override
    public void initParams(){
        fragmentPosition = getArguments().getInt(Constants.FRAGMENT_POSITION, 0);
    }

    @Override
    public void initUi(){
        settingScroll = (ObservableScrollView) getView().findViewById(R.id.setting_scroll);
    }

    @Override
    public void populateUi(){
        settingScroll.setOnScrollListener(new ObservableScrollView.OnScrollListener() {
            @Override
            public void onScrollChanged(int deltaX, int deltaY) {
                if (mScrollTabHolder != null)
                    mScrollTabHolder.onScroll(settingScroll, fragmentPosition);
            }
        });
    }

}
