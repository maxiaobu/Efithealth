package com.efithealth.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.efithealth.R;
import com.efithealth.app.Constant;
import com.efithealth.app.javabean.BeanSaveOrderInfo;
import com.efithealth.app.javabean.BeanUserInfoById;
import com.efithealth.app.maxiaobu.base.App;
import com.efithealth.app.maxiaobu.base.BaseAty;
import com.efithealth.app.maxiaobu.utils.IRequest;
import com.efithealth.app.maxiaobu.utils.JsonUtils;
import com.efithealth.app.maxiaobu.utils.RequestListener;
import com.efithealth.app.maxiaobu.utils.RequestParams;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderConfirmActivity extends BaseAty implements View.OnClickListener {


    @Bind(R.id.tv_title_common)
    TextView mTvTitleCommon;
    @Bind(R.id.toolbar_common)
    Toolbar mToolbarCommon;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_address)
    TextView mTvAddress;
    @Bind(R.id.ly_user_info)
    LinearLayout mLyUserInfo;
    @Bind(R.id.iv_reduce)
    ImageView mIvReduce;
    @Bind(R.id.tv_food_num)
    TextView mTvFoodNum;
    @Bind(R.id.iv_add)
    ImageView mIvAdd;
    @Bind(R.id.tv_price)
    TextView mTvPrice;
    @Bind(R.id.tv_phone_num)
    TextView mTvPhoneNum;
    @Bind(R.id.tv_shipping_method)
    TextView mTvShippingMethod;
    @Bind(R.id.tv_totle_price)
    TextView mTvTotlePrice;
    @Bind(R.id.tv_totle_price_bottom)
    TextView mTvTotlePriceBottom;
    @Bind(R.id.tv_now_order)
    TextView mTvNowOrder;
    private BeanUserInfoById.BMemberBean mData;
    private int mPrice;
    private int mNum;
    private String mMerid;
    private String mPhoneNum;
    private int mTotlePrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);
        ButterKnife.bind(this);
        setCommonBackToolBar(mToolbarCommon, mTvTitleCommon, "订单确认");
        Intent intent = getIntent();
        mPrice = intent.getIntExtra("price", -1);
        mNum = intent.getIntExtra("num", -1);
        mMerid = intent.getStringExtra("merid");
        mPhoneNum = intent.getStringExtra("phoneNum");

        initView();
        initData();
    }

    public void initView() {
        mTotlePrice = mPrice * mNum;
        mTvPrice.setText(String.valueOf(mPrice) + "元");
        mTvFoodNum.setText(String.valueOf(mNum));
        mTvPhoneNum.setText(mPhoneNum);
        mTvTotlePrice.setText("共计：" + String.valueOf(mTotlePrice) + "元");
        mTvTotlePriceBottom.setText("共计：" + String.valueOf(mTotlePrice) + "元");

    }

    public void initData() {
        //http://192.168.1.121:8080/efithealth/mmeById.do?memid=M000439 用户信息
        RequestParams para = new RequestParams("memid", App.getInstance().getMemid());
        IRequest.post(mActivity, Constant.URL_USER_INFO_BY_ID, para, "", new RequestListener() {
            @Override
            public void requestSuccess(String json) {
                BeanUserInfoById object = JsonUtils.object(json, BeanUserInfoById.class);
                Log.d("OrderConfirmActivity", json);
                mData = object.getBMember();
                mTvName.setText("收货人：" + mData.getRecname() + " " + mData.getMobphone());
                mTvAddress.setText("收货地址：" + mData.getRecaddress());
            }

            @Override
            public void requestError(VolleyError e) {

            }

            @Override
            public void resultFail(String json) {

            }
        });

    }

    @OnClick({R.id.iv_reduce, R.id.iv_add, R.id.tv_now_order, R.id.ly_user_info})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_reduce:
                if (mNum > 1) {
                    mNum--;
                    mTotlePrice = mNum * mPrice;
                    mTvFoodNum.setText(String.valueOf(mNum));
                    mTvTotlePrice.setText("共计：" + String.valueOf(mTotlePrice) + "元");
                    mTvTotlePriceBottom.setText("共计：" + String.valueOf(mTotlePrice) + "元");
                }
                break;
            case R.id.iv_add:
                mNum++;
                mTotlePrice = mNum * mPrice;
                mTvFoodNum.setText(String.valueOf(mNum));
                mTvTotlePrice.setText("共计：" + String.valueOf(mTotlePrice) + "元");
                mTvTotlePriceBottom.setText("共计：" + String.valueOf(mTotlePrice) + "元");
                break;
            case R.id.ly_user_info:
//                Intent intent = new Intent();
//                intent.setClass(OrderConfirmActivity.this, RevampAddress.class);
//                Log.d("OrderConfirmActivity", "dslfnhlkdjf");
                startActivityForResult(new Intent(OrderConfirmActivity.this, RevampAddress.class), 11);

                break;
            case R.id.tv_now_order:

                RequestParams requestParams = new RequestParams();
                requestParams.put("memid", App.getInstance().getMemid());
                requestParams.put("mernum", String.valueOf(mNum));
                requestParams.put("ordamt", String.valueOf(mTotlePrice));
                requestParams.put("isShopcart", "0");
                String s = "{foodmers:[{merid:" + mMerid + ",buynum:" + mNum + "}]}";
                requestParams.put("foodmers", s);

                IRequest.post(this, Constant.URL_SAVE_ORDER_INDO, requestParams, "", new RequestListener() {
                    @Override
                    public void requestSuccess(String json) {
                        Log.d("OrderConfirmActivity", json);
                        BeanSaveOrderInfo object = JsonUtils.object(json, BeanSaveOrderInfo.class);
                        if ("1".equals(object.getMsgFlag())) {
                            Intent intent = new Intent(new Intent(OrderConfirmActivity.this, PayActivity.class));
                            intent.putExtra("totlePrice", String.valueOf(mTotlePrice));
                            intent.putExtra("ordno", String.valueOf(object.getOrdno()));
//                                Log.d("OrderConfirmActivity", object.getOrdno().toString());
                            startActivity(intent);
                        } else {
                            Toast.makeText(mActivity, "订单确认失败!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void requestError(VolleyError e) {
                        Toast.makeText(mActivity, "订单确认失败!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void resultFail(String json) {
                        Toast.makeText(mActivity, "接口变了!", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            default:
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 11) {
            initData();
        }
    }
}
