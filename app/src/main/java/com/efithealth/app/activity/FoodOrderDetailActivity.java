package com.efithealth.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.efithealth.R;
import com.efithealth.app.javabean.BeanFoodDetail;
import com.efithealth.app.javabean.BeanFoodOrderDetail;
import com.efithealth.app.maxiaobu.base.BaseAty;
import com.efithealth.app.maxiaobu.common.Index;
import com.efithealth.app.maxiaobu.utils.IRequest;
import com.efithealth.app.maxiaobu.utils.JsonUtils;
import com.efithealth.app.maxiaobu.utils.RequestListener;
import com.efithealth.app.maxiaobu.utils.RequestParams;
import com.efithealth.app.maxiaobu.utils.StringUtils;
import com.efithealth.app.maxiaobu.utils.TimesUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoodOrderDetailActivity extends BaseAty implements View.OnClickListener {

    @Bind(R.id.tv_title_common)
    TextView mTvTitleCommon;
    @Bind(R.id.toolbar_common)
    Toolbar mToolbarCommon;
    @Bind(R.id.tv_complete)
    TextView mTvComplete;
    @Bind(R.id.tv_food_name)
    TextView mTvFoodName;
    @Bind(R.id.tv_food_num)
    TextView mTvFoodNum;
    @Bind(R.id.tv_order_time)
    TextView mTvOrderTime;
    @Bind(R.id.tv_dispatch_mode)
    TextView mTvDispatchMode;
    @Bind(R.id.tv_address)
    TextView mTvAddress;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;
    @Bind(R.id.tv_phone_num)
    TextView mTvPhoneNum;
    @Bind(R.id.iv_arrow)
    ImageView mIvArrow;
    @Bind(R.id.tv_dispatch_detail)
    TextView mTvDispatchDetail;
    @Bind(R.id.ly_dispatch_detail)
    RelativeLayout mLyDispatchDetail;
    @Bind(R.id.tv_again)
    TextView mTvAgain;
    private String mOrdno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_order_detail);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    @Override
    public void initView() {
        setCommonBackToolBar(mToolbarCommon, mTvTitleCommon, "订单详情");
        mOrdno = getIntent().getStringExtra("ordno");


    }

    @Override
    public void initData() {
//        http://192.168.1.121:8080/efithealth/mselectForderByOrdno.do?ordno=FO-20160726-170
//        减肥餐     mername : "壹想食健身周晚餐"      X1==  mbfordermerlist》》buynum : 1
//        下单时间：ordtime自己转
//        配送方式： disttypename : "快递配送"
//        收货地址：recaddress : "沈河区点5轮LOL哦LOL哦LOL哦"
//        收货人姓名：sendusername : ""
//        收货人电话：recphone : "13897900910"
//        快递单号：expressbillno : ""
//        下次配送： "deliverydatestr": "2016-08-01 2",+
//                共计98元：
        IRequest.post(this, Index.URL_FOOD_ORDER_DETAIL, new RequestParams("ordno", mOrdno), "", new RequestListener() {
            @Override
            public void requestSuccess(String json) {
                BeanFoodOrderDetail object = JsonUtils.object(json, BeanFoodOrderDetail.class);
                BeanFoodOrderDetail.BForderBean mData = object.getBForder();
                mTvComplete.setText(mData.getOrdstatusname());
                mTvFoodName.setText(object.getMerList().get(0).getMername() + ":");
                mTvFoodNum.setText("X" + mData.getMernum());
                mTvOrderTime.setText(mData.getOrdtimestr());
                mTvDispatchMode.setText(mData.getDisttypename());
                mTvAddress.setText(mData.getRecaddress());
                mTvUserName.setText(mData.getSendusername());
                mTvPhoneNum.setText(mData.getRecphone());
                if (!object.getDelvyList().isEmpty()) {
                    mTvDispatchDetail.setText(object.getDelvyList().get(0).getDelvystr() + " " + object.getDelvyList().get(0).getDayOfWeek()
                            + " " + object.getDelvyList().get(0).getDlvtimestr());
                } else {
                    mTvDispatchDetail.setText("");
                    mLyDispatchDetail.setClickable(false);
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

    @OnClick({R.id.ly_dispatch_detail, R.id.tv_again})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_dispatch_detail:
                startActivity(new Intent(this, DispatchDetailActivity.class));
                break;
            case R.id.tv_again:
                String merid = getIntent().getStringExtra("merid");
                Intent intent = new Intent(this,
                        LunchDetailActivity.class);
                intent.putExtra("merid", merid);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
