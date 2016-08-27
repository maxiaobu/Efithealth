package com.efithealth.app.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.VolleyError;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.efithealth.R;
import com.efithealth.app.Constant;
import com.efithealth.app.activity.LunchDetailActivity;
import com.efithealth.app.activity.LunchListActivity;
import com.efithealth.app.adapter.AdapterLunchOrderFrg;
import com.efithealth.app.javabean.BeanLunchOrderList;
import com.efithealth.app.maxiaobu.base.App;
import com.efithealth.app.maxiaobu.base.BaseFrg;
import com.efithealth.app.maxiaobu.common.Index;
import com.efithealth.app.maxiaobu.utils.IRequest;
import com.efithealth.app.maxiaobu.utils.JsonUtils;
import com.efithealth.app.maxiaobu.utils.RequestListener;
import com.efithealth.app.maxiaobu.utils.RequestParams;
import com.efithealth.app.maxiaobu.widget.refresh.LoadMoreFooterView;
import com.efithealth.app.maxiaobu.widget.refresh.RefreshHeaderView;

import org.json.JSONException;
import org.json.JSONObject;

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
        mAdapter.setOnCancelItemClickListener(new AdapterLunchOrderFrg.OnCancelItemClickListener() {
            @Override
            public void onItemClick(View view, final String what) {
                new MaterialDialog.Builder(getActivity())
                        .title("确定删除订单？")
                        .positiveColor(getResources().getColor(R.color.colorTextPrimary))
                        .positiveText("确认")

                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                                //http://192.168.1.121:8080/efithealth/mcancelForder.do?ordno=FO-20160726-170
                                // {"msgFlag":"1","msgContent":"取消订单成功"}
                                IRequest.post(getActivity(), Index.URL_CANCEL_ORDER, new RequestParams("ordno", what), "", new RequestListener() {
                                    @Override
                                    public void requestSuccess(String json) {
                                        try {
                                            JSONObject object=new JSONObject(json);
                                            Toast.makeText(getActivity(), object.get("msgContent").toString() , Toast.LENGTH_SHORT).show();
                                            mCurrentPage = 1;
                                            mLoadType = 0;
                                            initData();
                                        } catch (JSONException e) {
                                            Toast.makeText(getActivity(), "接口变了，我告诉我凹", Toast.LENGTH_SHORT).show();
                                            e.printStackTrace();
                                        }
                                    }
                                    @Override
                                    public void requestError(VolleyError e) {
                                        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                    @Override
                                    public void resultFail(String json) {
                                        Toast.makeText(getActivity(), json, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .negativeColor(getResources().getColor(R.color.colorTextPrimary))
                        .negativeText("取消")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });


        mAdapter.setOnDelayItemClickListener(new AdapterLunchOrderFrg.OnDelayItemClickListener() {
            @Override
            public void onItemClick(View view, final String what) {
                new MaterialDialog.Builder(getActivity())
                        .title("确定延期？")
                        .positiveColor(getResources().getColor(R.color.colorTextPrimary))
                        .positiveText("确认")

                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                                //http://192.168.1.121:8080/efithealth/mextension.do?ordno=FO-20160726-170
                                //{"msgFlag":"1","msgContent":""}
                                //msgFlag：1成功了 0失败了
                                IRequest.post(getActivity(), Index.URL_DELAY_ORDER, new RequestParams("ordno", what), "", new RequestListener() {
                                    @Override
                                    public void requestSuccess(String json) {
                                        try {
                                            JSONObject object=new JSONObject(json);
                                            Toast.makeText(getActivity(), object.get("msgContent").toString() , Toast.LENGTH_SHORT).show();
                                            mCurrentPage = 1;
                                            mLoadType = 0;
                                            initData();
                                        } catch (JSONException e) {
                                            Toast.makeText(getActivity(), "接口变了，得告诉我凹", Toast.LENGTH_SHORT).show();
                                            e.printStackTrace();
                                        }
                                    }
                                    @Override
                                    public void requestError(VolleyError e) {
                                        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                    @Override
                                    public void resultFail(String json) {
                                        Toast.makeText(getActivity(), json, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .negativeColor(getResources().getColor(R.color.colorTextPrimary))
                        .negativeText("取消")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        mAdapter.setOnAgainItemClickListener(new AdapterLunchOrderFrg.OnAgainItemClickListener() {
            @Override
            public void onItemClick(View view, String what) {
                Intent intent = new Intent(getActivity(),
                        LunchDetailActivity.class);
                intent.putExtra("merid",what);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        RequestParams params = new RequestParams();
        params.put("listtype", "forderlist");
        params.put("memid", App.getInstance().getMemid());
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
