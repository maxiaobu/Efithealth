package com.efithealth.app.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.efithealth.R;
import com.efithealth.app.javabean.BeanDispatchDetailAty;
import com.efithealth.app.maxiaobu.base.BaseAty;
import com.efithealth.app.maxiaobu.common.Index;
import com.efithealth.app.maxiaobu.utils.IRequest;
import com.efithealth.app.maxiaobu.utils.JsonUtils;
import com.efithealth.app.maxiaobu.utils.RequestListener;
import com.efithealth.app.maxiaobu.utils.RequestParams;
import com.efithealth.app.maxiaobu.widget.ItemDispatchDetailView;
import com.parse.codec.binary.StringUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DispatchDetailActivity extends BaseAty {

    @Bind(R.id.tv_title_common)
    TextView mTvTitleCommon;
    @Bind(R.id.toolbar_common)
    Toolbar mToolbarCommon;
    @Bind(R.id.ly_list)
    LinearLayout mLyList;

    private String mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_detail);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        setCommonBackToolBar(mToolbarCommon, mTvTitleCommon, "配送详情");
    }

    @Override
    public void initData() {
        IRequest.post(this, Index.URL_FOOD_DISPATCH_DETAIL, new RequestParams("ordno", getIntent().getStringExtra("ordno")), "", new RequestListener() {
            @Override
            public void requestSuccess(String json) {
                BeanDispatchDetailAty object = JsonUtils.object(json, BeanDispatchDetailAty.class);
                List<BeanDispatchDetailAty.ListBean> mData = object.getList();
                if (mData.size() > 0) {
                    for (BeanDispatchDetailAty.ListBean listBean : mData) {
                        ItemDispatchDetailView view = new ItemDispatchDetailView(DispatchDetailActivity.this);
                        //status:1待配送 2已配送 3已取消
                        String state = "";
                        switch (Integer.parseInt(listBean.getStatus())) {
                            case 1:
                                state = "待配送";
                                break;
                            case 2:
                                state = "已配送";
                                break;
                            case 3:
                                state = "已取消";
                                break;
                            default:
                                break;
                        }
                        String date = listBean.getDelvystr();
                        if (mDate!=null  && mDate.equals(date)) {
                            date="";
                        }
                        mDate=date;
                        view.setComment(date, listBean.getDayOfWeek(), listBean.getDlvtimestr(), state);
                        mLyList.addView(view);

                    }
                }

            }

            @Override
            public void requestError(VolleyError e) {

            }

            @Override
            public void resultFail(String json) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}
