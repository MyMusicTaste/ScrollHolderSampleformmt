package com.mymusictaste.scrollholdersample.fragment;

import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mymusictaste.scrollholdersample.R;
import com.mymusictaste.scrollholdersample.adapter.SampleAdapter;
import com.mymusictaste.scrollholdersample.type.Sample;
import com.mymusictaste.scrollholdersample.util.AppUtil;
import com.mymusictaste.scrollholdersample.util.Constants;

import java.util.ArrayList;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.DefaultHeaderTransformer;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * Created by JKtheUnique on 2014-12-03.
 */
public class SampleListFragment extends ScrollHolderFragment implements AbsListView.OnScrollListener {
    private ListView listView;
    private int queryType = 0;
    private int fragmentPosition = 0;

    private SampleAdapter sampleAdapter;

    private PullToRefreshLayout mPullToRefreshLayout;

    public static SampleListFragment getNewFragment(Bundle args){
        SampleListFragment fragment = new SampleListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_sample_list,null);
        return view;
    }

    @Override
    public void adjustScroll(int scrollHeight) {
        if (scrollHeight == 0 && listView.getFirstVisiblePosition() >= 1) {
            return;
        }

        listView.setSelectionFromTop(1, scrollHeight);

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mScrollTabHolder != null)
            mScrollTabHolder.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount, fragmentPosition);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // nothing
    }

    @Override
    public void initParams(){
        queryType = getArguments().getInt(Constants.QUERY_TYPE,0);
        fragmentPosition = getArguments().getInt(Constants.FRAGMENT_POSITION, 0);
        sampleAdapter = new SampleAdapter(getActivity(),getSampleList());
    }

    @Override
    public void initUi(){
        listView = (ListView) getView().findViewById(R.id.listview);
        View placeHolderView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_fragment_header, listView, false);
        listView.addHeaderView(placeHolderView);
        mPullToRefreshLayout = (PullToRefreshLayout) getView().findViewById(R.id.ptr_layout);
    }

    @Override
    public void populateUi(){
        listView.setAdapter(sampleAdapter);
        listView.setOnItemClickListener(itemClickListener);
        listView.setOnScrollListener(this);
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


    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Sample result = (Sample)parent.getItemAtPosition(position);
            Toast.makeText(getActivity(), result.getTitle(),Toast.LENGTH_SHORT).show();
        }
    };

    private ArrayList<Sample> getSampleList(){
        ArrayList<Sample> returnList = new ArrayList<>();
        for (int count=0;count<50;count++){
            Sample result = new Sample();
            result.setTitle("Test Title"+ String.valueOf(count+1));
            result.setSubTitle("Test SubTitle"+ String.valueOf(count+1));
            result.setExtra(String.valueOf(count));
            returnList.add(result);
        }
        return returnList;
    }

}
