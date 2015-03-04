package com.mymusictaste.scrollholdersample.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mymusictaste.scrollholdersample.R;
import com.mymusictaste.scrollholdersample.util.AppUtil;

import java.util.ArrayList;

/**
 * Created by JKtheUnique on 2014-11-11.
 */
public abstract class ScrollHolderActivity extends ActionBarActivity {

    protected ArrayList<View> enableList = new ArrayList<View>();
    protected Toolbar actionToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initApplication();
        initActionBar();
        initParams();
        initUi();
        populateUi();
    }

    private void initApplication(){
    }

    protected void initActionBar(){
    }

    protected abstract void initParams();
    protected abstract void initUi();
    protected abstract void populateUi();

    protected void onNetwork(boolean on){
        if(on){
            AppUtil.showLoadingView(ScrollHolderActivity.this);
            if(enableList.size()>0){
                for(View currentView : enableList)
                    currentView.setEnabled(false);
            }
        }else{
            AppUtil.removeLoadingView(ScrollHolderActivity.this);
            if(enableList.size()>0){
                for(View currentView : enableList)
                    currentView.setEnabled(true);
            }
        }
    }

    @Override
    public void setContentView(int layoutResID){
        super.setContentView(layoutResID);
        setToolBar();
    }

    protected void setToolBar(){
        if (actionToolbar == null) {
            actionToolbar = (Toolbar) findViewById(R.id.action_toolbar);
            if (actionToolbar == null)
                actionToolbar = new Toolbar(this);
            setSupportActionBar(actionToolbar);
        }
    }
}
