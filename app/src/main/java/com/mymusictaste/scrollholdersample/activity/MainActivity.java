package com.mymusictaste.scrollholdersample.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.astuetz.PagerSlidingTabStrip;
import com.mymusictaste.scrollholdersample.R;
import com.mymusictaste.scrollholdersample.adapter.PagerAdapter;
import com.mymusictaste.scrollholdersample.util.AppUtil;
import com.mymusictaste.scrollholdersample.util.Constants;
import com.mymusictaste.scrollholdersample.util.ScrollTabHolderPlus;
import com.nineoldandroids.view.ViewHelper;

public class MainActivity extends ScrollHolderActivity implements ScrollTabHolderPlus, ViewPager.OnPageChangeListener {

    private ImageView mHeaderPicture;
    private View mHeader;

    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    private int mMinHeaderHeight;
    private int mHeaderHeight;
    private int mMinHeaderTranslation;
    private ImageView mHeaderLogo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        setIsTest(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    private void setIsTest(Bundle savedInstanceState){
        if(savedInstanceState!=null) {
            Constants.isTest = savedInstanceState.getBoolean(Constants.IS_TEST, false);
            getIntent().putExtra(Constants.QUERY_TYPE,String.valueOf(savedInstanceState.getInt(Constants.QUERY_TYPE, 0)));
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void initParams(){
        mMinHeaderHeight = getResources().getDimensionPixelSize(R.dimen.min_header_height);
        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mMinHeaderTranslation = -mMinHeaderHeight;// + getActionBarHeight();
    }
    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int position) {
        SparseArrayCompat<ScrollTabHolderPlus> scrollTabHolders = mPagerAdapter.getScrollTabHolders();
        ScrollTabHolderPlus currentHolder = scrollTabHolders.valueAt(position);
        currentHolder.adjustScroll((int) (mHeader.getHeight() + ViewHelper.getTranslationY(mHeader)));
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition) {
        if (mViewPager.getCurrentItem() == pagePosition) {
            int scrollY = getScrollY(view);
            ViewHelper.setTranslationY(mHeader, Math.max(-scrollY, mMinHeaderTranslation));
            float ratio = clamp(ViewHelper.getTranslationY(mHeader) / mMinHeaderTranslation, 0.0f, 1.0f);
            setAlpha(clamp(5.0F * ratio - 4.0F, 0.0F, 1.0F));
        }
    }

    @Override
    public void onScroll(ScrollView view, int pagePosition) {
        if (mViewPager.getCurrentItem() == pagePosition) {
            int scrollY = view.getScrollY();
            ViewHelper.setTranslationY(mHeader, Math.max(-scrollY, mMinHeaderTranslation));
            float ratio = clamp(ViewHelper.getTranslationY(mHeader) / mMinHeaderTranslation, 0.0f, 1.0f);
            setAlpha(clamp(5.0F * ratio - 4.0F, 0.0F, 1.0F));
        }
    }

    @Override
    public void adjustScroll(int scrollHeight) {
        // nothing
    }

    public int getScrollY(AbsListView view) {
        View c = view.getChildAt(0);
        if (c == null) {
            return 0;
        }

        int firstVisiblePosition = view.getFirstVisiblePosition();
        int top = c.getTop();

        int headerHeight = 0;
        if (firstVisiblePosition >= 1) {
            headerHeight = mHeaderHeight;
        }

        return -top + firstVisiblePosition * c.getHeight() + headerHeight;
    }

    public static float clamp(float value, float max, float min) {
        return Math.max(Math.min(value, min), max);
    }

    private void setAlpha(float alpha) {
        ColorDrawable stripBackground = new ColorDrawable(getResources().getColor(R.color.tab_strip_background));
        stripBackground.setAlpha((int)(alpha*255f));
        mPagerSlidingTabStrip.setBackgroundDrawable(stripBackground);
    }

    @Override
    protected void initUi(){
        mHeaderPicture = (ImageView) findViewById(R.id.header_picture);
        mHeaderLogo = (ImageView) findViewById(R.id.header_logo);
        mHeader = findViewById(R.id.header);

        mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.pager);

    }
    @Override
    protected void populateUi(){
        mHeaderPicture.setImageDrawable(getResources().getDrawable(R.drawable.landscape_82968_640));
        mViewPager.setOffscreenPageLimit(3);

        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mPagerAdapter.setTabHolderScrollingContent(this);

        mViewPager.setAdapter(mPagerAdapter);

        mPagerSlidingTabStrip.setViewPager(mViewPager);
        mPagerSlidingTabStrip.setOnPageChangeListener(this);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#202020")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
    }


    public void setSampleScroll(){
        if(mViewPager.getCurrentItem() == mPagerAdapter.getCount()-1){
            ViewHelper.setTranslationY(mHeader, -AppUtil.getPxFromDP(48)+AppUtil.getPxFromDP(48));
            mPagerSlidingTabStrip.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }
}
