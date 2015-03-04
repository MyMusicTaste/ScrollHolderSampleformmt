package com.mymusictaste.scrollholdersample.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by JKtheUnique on 2014-12-09.
 */
public class ObservableScrollView extends ScrollView {
    OnScrollListener listener;
    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (listener!=null){
            listener.onScrollChanged(l - oldl, t - oldt);
        }
    }

    @Override
    public int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

    public void setOnScrollListener(OnScrollListener listener) {
        this.listener = listener;
    }

    public static interface OnScrollListener {
        public void onScrollChanged(int deltaX, int deltaY);
    }
}
