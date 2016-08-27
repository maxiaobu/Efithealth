package com.efithealth.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.efithealth.R;
import com.efithealth.app.javabean.BeanAccountInfo;
import com.efithealth.app.maxiaobu.base.App;
import com.efithealth.app.maxiaobu.base.BaseAty;
import com.efithealth.app.maxiaobu.common.Index;
import com.efithealth.app.maxiaobu.utils.IRequest;
import com.efithealth.app.maxiaobu.utils.JsonUtils;
import com.efithealth.app.maxiaobu.utils.RequestListener;
import com.efithealth.app.maxiaobu.utils.RequestParams;
import com.efithealth.app.maxiaobu.utils.storage.SPUtils;
import com.google.gson.JsonObject;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kotlin.Function;


public class PayActivity extends BaseAty implements View.OnClickListener {

    @Bind(R.id.tv_title_common)
    TextView mTvTitleCommon;
    @Bind(R.id.toolbar_common)
    Toolbar mToolbarCommon;
    @Bind(R.id.ly_epay)
    LinearLayout mLyEpay;
    @Bind(R.id.cb_wxin_pay)
    AppCompatCheckBox mCbWxinPay;
    @Bind(R.id.rl_wxin_pay)
    LinearLayout mRlWxinPay;
    @Bind(R.id.cb_ali_pay)
    AppCompatCheckBox mCbAliPay;
    @Bind(R.id.rl_ali_pay)
    LinearLayout mRlAliPay;
    @Bind(R.id.tv_now_order)
    TextView mTvNowOrder;
    @Bind(R.id.cb_e_pay)
    AppCompatCheckBox mCbEPay;
    @Bind(R.id.tv_price)
    TextView mTvPrice;
    @Bind(R.id.tv_ebi_content)
    TextView mTvEbiContent;
    private String mTotlePrice;
    private int mTotalEbi;
    private String mOrdno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        mTotlePrice = getIntent().getStringExtra("totlePrice");
        mOrdno = getIntent().getStringExtra("ordno");
        initView();
        initData();
    }

    @Override
    public void initView() {
        setCommonBackToolBar(mToolbarCommon, mTvTitleCommon, "支付页");
        mTvPrice.setText(mTotlePrice+"元");


    }

    @Override
    public void initData() {
//        Log.d("PayActivity", App.getInstance().getMemid());
        RequestParams params = new RequestParams("memid", App.getInstance().getMemid());
        IRequest.post(mActivity, Index.URL_ACCOUNT_INFO, params, "", new RequestListener() {
            @Override
            public void requestSuccess(String json) {
                BeanAccountInfo object = JsonUtils.object(json, BeanAccountInfo.class);
                mTotalEbi = (object.getYcoincashnum() + object.getYcoinnum()) / 100;
                if (mTotalEbi>Integer.parseInt(mTotlePrice)){
                    mTvEbiContent.setText("本次可抵现"+mTotlePrice+"元，抵现后余额为0元");
                }else {
                    mTvEbiContent.setText("本次可抵现"+mTotlePrice+"元，抵现后余额为"+(Integer.parseInt(mTotlePrice)-mTotalEbi)+"元");
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

    @OnClick({R.id.ly_epay, R.id.rl_wxin_pay, R.id.rl_ali_pay,R.id.tv_now_order})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_epay:
                mCbEPay.setChecked(!mCbEPay.isChecked());

                break;
            case R.id.rl_wxin_pay:
                Toast.makeText(this, "微信支付未开通", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_ali_pay:
                Toast.makeText(this, "支付宝未开通", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_now_order:
                pay();
                break;
            default:
                break;
        }
    }

    private void pay() {
        if (mCbEPay.isChecked()){
//            Log.d("PayActivity", mOrdno);
            if (mTotalEbi>Integer.parseInt(mTotlePrice)){//仅 e币
                IRequest.post(this, Index.URL_EBI_PAY, new RequestParams("ordno","{\"ordno\":"+ mOrdno+"}"), "", new RequestListener() {
                    @Override
                    public void requestSuccess(String json) {
                        try {
                            org.json.JSONObject a = new org.json.JSONObject(json);
                            if (a.get("msgFlag").equals("1")){
                               startAty();
                            }else {
                                Toast.makeText(PayActivity.this, a.get("msgContent").toString(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(mActivity, "接口是不是改了", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void requestError(VolleyError e) {
                        Toast.makeText(PayActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void resultFail(String json) {
                        Toast.makeText(PayActivity.this, json, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }else {
            Toast.makeText(this, "请选择支付方式", Toast.LENGTH_SHORT).show();
        }
    }


    private void payRequest(){



    }

    private void startAty(){
        Intent intent=new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setClass(PayActivity.this,MainActivity.class);
        intent.putExtra("foodFlag",1);
        startActivity(intent);
    }



}
