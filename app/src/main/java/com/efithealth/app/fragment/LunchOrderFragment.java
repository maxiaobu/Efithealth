package com.efithealth.app.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.efithealth.R;
import com.efithealth.app.Constant;
import com.efithealth.app.MyApplication;
import com.efithealth.app.adapter.AdapterLunchOrderFrg;
import com.efithealth.app.javabean.BeanLunchOrderList;
import com.efithealth.app.maxiaobu.base.BaseFrg;
import com.efithealth.app.maxiaobu.utils.IRequest;
import com.efithealth.app.maxiaobu.utils.JsonUtils;
import com.efithealth.app.maxiaobu.utils.RequestListener;
import com.efithealth.app.maxiaobu.utils.RequestParams;
import com.efithealth.app.maxiaobu.widget.refresh.LoadMoreFooterView;
import com.efithealth.app.maxiaobu.widget.refresh.RefreshHeaderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LunchOrderFragment extends BaseFrg implements OnRefreshListener, OnLoadMoreListener {

    @Bind(R.id.swipe_refresh_header)
    RefreshHeaderView mSwipeRefreshHeader;
    @Bind(R.id.swipe_target)
    RecyclerView mSwipeTarget;
    @Bind(R.id.swipe_load_more_footer)
    LoadMoreFooterView mSwipeLoadMoreFooter;
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    @Bind(R.id.ivNoDataLogo)
    ImageView mIvNoDataLogo;
    @Bind(R.id.ivNoDataBac)
    ImageView mIvNoDataBac;
    @Bind(R.id.rlNoData)
    RelativeLayout mRlNoData;
    private View mRootView;
    /**
     * 0刷新  1加载
     */
    private int mLoadType;
    private int mCurrentPage;
    private AdapterLunchOrderFrg mAdapter;
    private List<BeanLunchOrderList.ForderListBean> mData;

    public LunchOrderFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_msj_order, container, false);
        ButterKnife.bind(this, mRootView);

        mLoadType = 0;
        mCurrentPage = 1;
        mData = new ArrayList<>();
        initView();
        initData();

        return mRootView;
    }

    @Override
    public void initView() {
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
        mSwipeTarget.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mSwipeTarget.setLayoutManager(layoutManager);
        mSwipeTarget.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new AdapterLunchOrderFrg(getActivity(), mData);
        mSwipeTarget.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        RequestParams params = new RequestParams();
        params.put("listtype", "forderlist");
        params.put("memid", MyApplication.getInstance().getMemid());
//        params.put("memid", "M000440");

//http://192.168.1.121:8080/efithealth/morderlist.do?memid=M000440&listtype=forderlist
        IRequest.post(getActivity(), Constant.FOOD_ORDER_LIST_URL, params, "", new RequestListener() {
            @Override
            public void requestSuccess(String json) {
//                Log.d("MsjOrderFragment", json);
                if (mRlNoData.getVisibility()==View.VISIBLE)
                    mRlNoData.setVisibility(View.GONE);
                BeanLunchOrderList object = JsonUtils.object(json, BeanLunchOrderList.class);
                if (mLoadType == 0) {//刷新
                    mData.clear();
                    mData.addAll(object.getForderList());
                    mAdapter.notifyDataSetChanged();
                    if (mSwipeToLoadLayout != null) {
                        mSwipeToLoadLayout.setRefreshing(false);
                    }
                } else if (mLoadType == 1) {//加载更多
//                    int position = mAdapter.getItemCount();
//                    mData.addAll(object.getForderList());
//                    mAdapter.notifyItemRangeInserted(position, object.getForderList().size());
                    mSwipeToLoadLayout.setLoadingMore(false);
                } else {
                    Toast.makeText(getActivity(), "刷新什么情况", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void requestError(VolleyError e) {
                mRlNoData.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "请求数据失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void resultFail(String json) {
                Toast.makeText(getActivity(), json, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        mCurrentPage = 1;
        mLoadType = 0;
        if (mSwipeToLoadLayout != null) {
            mSwipeToLoadLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    initData();
                }
            }, 2);
        }
    }

    @Override
    public void onLoadMore() {
        mCurrentPage++;
        mLoadType = 1;
        mSwipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        });
    }
}
