package com.efithealth.app.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.efithealth.R;
import com.efithealth.app.Constant;
import com.efithealth.app.javabean.BeanFoodDetail;
import com.efithealth.app.maxiaobu.base.BaseAty;
import com.efithealth.app.maxiaobu.utils.IRequest;
import com.efithealth.app.maxiaobu.utils.JsonUtils;
import com.efithealth.app.maxiaobu.utils.RequestListener;
import com.efithealth.app.maxiaobu.utils.RequestParams;

import java.lang.reflect.GenericDeclaration;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LunchDetailActivity extends BaseAty implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

    @Bind(R.id.iv_food)
    ImageView mIvFood;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.ctl_name)
    CollapsingToolbarLayout mCtlName;
    @Bind(R.id.app_bar)
    AppBarLayout mAppBar;
    @Bind(R.id.root_layout)
    CoordinatorLayout mRootLayout;
    @Bind(R.id.tv_food_name)
    TextView mTvFoodName;
    @Bind(R.id.tv_food_price)
    TextView mTvFoodPrice;
    @Bind(R.id.tv_food_sales)
    TextView mTvFoodSales;
    @Bind(R.id.tv_base_info)
    TextView mTvBaseInfo;
    @Bind(R.id.tv_material)
    TextView mTvMaterial;
    @Bind(R.id.iv_phone)
    ImageView mIvPhone;
    @Bind(R.id.rl_call_phone)
    RelativeLayout mRlCallPhone;
    @Bind(R.id.iv_food_detail)
    ImageView mIvFoodDetail;
    @Bind(R.id.rl_food_detail)
    RelativeLayout mRlFoodDetail;
    @Bind(R.id.tv_base_notice)
    TextView mTvBaseNotice;
    @Bind(R.id.tv_content_notice)
    TextView mTvContentNotice;
    @Bind(R.id.rl_order_notice)
    LinearLayout mRlOrderNotice;
    @Bind(R.id.iv_reduce)
    ImageView mIvReduce;
    @Bind(R.id.tv_food_num)
    TextView mTvFoodNum;
    @Bind(R.id.iv_add)
    ImageView mIvAdd;
    @Bind(R.id.tv_add_cart)
    TextView mTvAddCart;
    @Bind(R.id.tv_now_order)
    TextView mTvNowOrder;
    @Bind(R.id.tv_phone_num)
    TextView mTvPhoneNum;
    @Bind(R.id.nested_scroll_view)
    NestedScrollView mNestedScrollView;
    @Bind(R.id.ll_root_content)
    LinearLayout mLlRootContent;
    private BeanFoodDetail.BFoodmerBean mData;
    private int mFoodNum;
    private String mMerid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_detail);
        ButterKnife.bind(this);

        mFoodNum = 1;

        initView();
        initData();


    }

    private void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAppBar.addOnOffsetChangedListener(this);
        mMerid = getIntent().getStringExtra("merid");
        mToolbar.setTitle("");
        mCtlName.setTitle("");
        CoordinatorLayout.LayoutParams linearParams = (CoordinatorLayout.LayoutParams) mAppBar.getLayoutParams();
        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        linearParams.height = width;
        mAppBar.setLayoutParams(linearParams);


    }

    private void initData() {
        RequestParams params = new RequestParams("merid", mMerid);
        IRequest.post(this, Constant.URL_FOOD_DETAIL, params, new RequestListener() {
            @Override
            public void requestSuccess(String json) {
                BeanFoodDetail object = JsonUtils.object(json, BeanFoodDetail.class);
                mData = object.getBFoodmer();

                mTvFoodName.setText(mData.getMername());
                mTvFoodSales.setText(mData.getCreatetime().getDay() + "天已售" + mData.getSalenum() + "份");
                mTvFoodPrice.setText(String.valueOf(mData.getMerprice()) + "元");
                mTvBaseInfo.setText(mData.getEnergydescr());
                mTvMaterial.setText(mData.getCompodescr());
                mTvPhoneNum.setText(mData.getFplatConphone());
                mTvContentNotice.setText(mData.getOrdernotice());
                Glide.with(mActivity).load(mData.getImgsfilename()).placeholder(R.mipmap.ic_place_holder).into(mIvFood);

            }

            @Override
            public void requestError(VolleyError e) {

            }

            @Override
            public void resultFail(String json) {

            }
        });
    }

    @OnClick({R.id.rl_call_phone, R.id.iv_reduce, R.id.iv_add, R.id.tv_add_cart, R.id.tv_now_order})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_call_phone:
                ringUp();
                break;
            case R.id.iv_reduce:
                if (mFoodNum > 1) {
                    mFoodNum--;
                    mTvFoodNum.setText(String.valueOf(mFoodNum));
                }
                break;
            case R.id.iv_add:
                mFoodNum++;
                mTvFoodNum.setText(String.valueOf(mFoodNum));
                break;
            case R.id.tv_add_cart:
                // TODO: 2016/8/23 没写
                break;
            case R.id.tv_now_order:
                if (mData!=null){
                    Intent intent = new Intent(this, OrderConfirmActivity.class);
                    intent.putExtra("price",mData.getMerprice());
                    intent.putExtra("num",Integer.valueOf((String) mTvFoodNum.getText()));
                    intent.putExtra("merid",mMerid);
                    intent.putExtra("phoneNum",mTvPhoneNum.getText());
                    ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(mTvNowOrder,
                            view.getWidth() / 2, view.getHeight() / 2, 0, 0);
                    ActivityCompat.startActivity(this,intent,
                            compat.toBundle());
                }

                break;
            default:
                break;
        }
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
            if (mData != null)
                mCtlName.setTitle(mData.getMername());
        } else {
            mCtlName.setTitle("");
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    /**
     * 打电话确认
     */
    private void ringUp() {
        new MaterialDialog.Builder(this)
                .title("呼叫")
                .positiveColor(getResources().getColor(R.color.colorTextPrimary))
                .positiveText("确认")

                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        Uri data = Uri.parse("tel:" + mTvPhoneNum.getText());
                        intent.setData(data);
                        startActivity(intent);
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
}
