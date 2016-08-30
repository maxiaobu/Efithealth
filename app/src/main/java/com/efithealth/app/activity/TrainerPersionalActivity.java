package com.efithealth.app.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.efithealth.R;
import com.efithealth.app.maxiaobu.base.BaseAty;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TrainerPersionalActivity extends BaseAty implements AppBarLayout.OnOffsetChangedListener {

    @Bind(R.id.ly_bar)
    LinearLayout mLyBar;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.ctl_name)
    CollapsingToolbarLayout mCtlName;
    @Bind(R.id.app_bar)
    AppBarLayout mAppBar;
    @Bind(R.id.tabs)
    TabLayout mTabs;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.nested_scroll_view)
    NestedScrollView mNestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_persional);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAppBar.addOnOffsetChangedListener(this);
        mToolbar.setTitle("");
        mCtlName.setTitle("");
//        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) mAppBar.getLayoutParams();
//        WindowManager wm = this.getWindowManager();
//        int width = wm.getDefaultDisplay().getWidth();
//        linearParams.height = width;
//        mAppBar.setLayoutParams(linearParams);
    }

    @Override
    public void initData() {

    }

    /**
     * toolbar滑动监听
     *
     * @param appBarLayout
     * @param verticalOffset
     */
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//        Log.i("sdfjkh", "onOffsetChanged: "+verticalOffset);
//        Log.i("321321", "onOffsetChanged: "+ (-mCtlName.getHeight() ));
        if (verticalOffset <= -mCtlName.getHeight() + mToolbar.getHeight() + 180) {
//            if (mData != null)
                mCtlName.setTitle("杰西卡");
        } else {
            mCtlName.setTitle("");
        }
    }
}
