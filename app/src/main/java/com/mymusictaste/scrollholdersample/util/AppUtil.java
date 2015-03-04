package com.mymusictaste.scrollholdersample.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.mymusictaste.scrollholdersample.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by JKtheUnique on 2014-11-12.
 */
public class AppUtil {

    public static void showLoadingView(Activity activity){
        View loadingView  = LayoutInflater.from(activity).inflate(R.layout.layout_loading,null);
        ProgressBar progress = (ProgressBar)loadingView.findViewById(R.id.progress);
        progress.getIndeterminateDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        ViewGroup rootView =(ViewGroup)activity.getWindow().getDecorView().getRootView();
        if(rootView.findViewById(R.id.progress_wheel_wrapper)==null){
            rootView.addView(loadingView);
        }
    }

    public static void removeLoadingView(Activity activity){
        final ViewGroup rootView = (ViewGroup)activity.getWindow().getDecorView().getRootView();
        final RelativeLayout loadingView = (RelativeLayout) rootView.findViewById(R.id.progress_wheel_wrapper);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(loadingView!=null){
                    rootView.removeView(loadingView);
                }
            }
        });
    }

    public static int getPxFromDP(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static int getDisplayWidth(Context c){
        WindowManager mWinMgr = (WindowManager)c.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        mWinMgr.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    @SuppressLint("SimpleDateFormat")
    public static String getTimeStringWithGMT(Date date){
        SimpleDateFormat dateformat= new SimpleDateFormat("yyyy.MM.dd. aa hh:mm (ZZZZ)");
        dateformat.setTimeZone(TimeZone.getDefault());
        return dateformat.format(date);

    }
}
