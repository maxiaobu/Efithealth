package com.efithealth.app.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.efithealth.R;
import com.efithealth.app.fragment.CourseOrderFragment;
import com.efithealth.app.fragment.LunchOrderFragment;
import com.efithealth.app.fragment.MsjOrderFragment;
import com.efithealth.app.maxiaobu.base.BaseAty;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OrderListActivity extends BaseAty {

    @Bind(R.id.tv_title_common)
    TextView mTvTitleCommon;
    @Bind(R.id.toolbar_common)
    Toolbar mToolbarCommon;
    @Bind(R.id.tabs)
    TabLayout mTabs;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        ButterKnife.bind(this);

        init();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    private void init() {
        int foodFlag = getIntent().getIntExtra("orderFlag", -1);
        setCommonBackToolBar(mToolbarCommon,mTvTitleCommon,"订单列表");
        if (mViewpager != null) {
            setupViewPager(mViewpager);
            mViewpager.setOffscreenPageLimit(1);
        }
        mTabs.setupWithViewPager(mViewpager);
        if (foodFlag==2){
            mViewpager.setCurrentItem(1,true);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new CourseOrderFragment(), "课程");
        adapter.addFragment(new LunchOrderFragment(), "配餐");
        adapter.addFragment(new MsjOrderFragment(), "按摩");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }

    }
}
