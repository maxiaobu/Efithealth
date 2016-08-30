package com.efithealth.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.VolleyError;
import com.efithealth.R;
import com.efithealth.app.javabean.BeanModifyOrderAddress;
import com.efithealth.app.maxiaobu.base.BaseAty;
import com.efithealth.app.maxiaobu.common.Index;
import com.efithealth.app.maxiaobu.utils.IRequest;
import com.efithealth.app.maxiaobu.utils.JsonUtils;
import com.efithealth.app.maxiaobu.utils.RequestListener;
import com.efithealth.app.maxiaobu.utils.RequestParams;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyOrderAddressActivity extends BaseAty implements View.OnClickListener {
    public static final String[] AREA = new String[]{"和平区", "沈河区", "大东区", "皇姑区"
            , "铁西区", "苏家屯区", "浑南区", "沈北新区", "于洪区"};
    public static final String[] RANGE = new String[]{"所有", "仅明天"};

    @Bind(R.id.tv_title_common)
    TextView mTvTitleCommon;
    @Bind(R.id.btn_save)
    TextView mBtnSave;
    @Bind(R.id.toolbar_common)
    Toolbar mToolbarCommon;
    @Bind(R.id.tv_area)
    TextView mTvArea;
    @Bind(R.id.ed_address)
    AppCompatEditText mEdAddress;
    @Bind(R.id.tv_range)
    TextView mTvRange;

    private String mAddress;
    private String mArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_order_address);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    @Override
    public void initView() {
        setCommonBackToolBar(mToolbarCommon, mTvTitleCommon, "地址详情");
        String[] split = getIntent().getStringExtra("recaddress").split("区");
        mAddress = split[1];
        mArea =split[0]+"区";
        mTvArea.setText(mArea);
        mEdAddress.setText(mAddress);


    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_area, R.id.tv_range, R.id.btn_save})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_area:
                new MaterialDialog.Builder(this)
                        .items(AREA)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                mTvArea.setText(AREA[which]);
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
            case R.id.tv_range:
                new MaterialDialog.Builder(this)
                        .items(RANGE)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                mTvRange.setText(RANGE[which]);
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
            case R.id.btn_save:
                /**
                 * 单个订单修改地址
                 * http://192.168.1.121:8080/efithealth/mupdateAddress.do?updateMode=all&region=dhhkjhsf&address=dskjhfkjsdh&ordno=FO-20160726-170
                 *
                 * 参数：updateMode（all，tomorrow）
                 * region（区域）
                 * address（地址）
                 *  ordno（订单编号）
                 */
                if (!"".equals(mEdAddress.getText())) {
                    RequestParams params = new RequestParams();
                    params.put("updateMode", String.valueOf(mTvRange.getText()).equals("所有") ? "all" : "tomorrow");
                    Log.d("ModifyOrderAddressActiv", String.valueOf(mTvRange.getText()).equals("所有") ? "all"+"+++++++"+mTvRange.getText() : "tomorrow"+"+++++++"+mTvRange.getText());
                    params.put("region", String.valueOf(mTvArea.getText()));
                    params.put("address", String.valueOf(mEdAddress.getText()));
                    params.put("ordno", String.valueOf(getIntent().getStringExtra("ordno")));
                    IRequest.post(this, Index.URL_MODIFY_ORDER_ADDRESS, params, "", new RequestListener() {
                        @Override
                        public void requestSuccess(String json) {
                            Log.d("ModifyOrderAddressActiv", json);
                            BeanModifyOrderAddress object = JsonUtils.object(json, BeanModifyOrderAddress.class);
                            if (Integer.parseInt(object.getMsgFlag()) == 1) {
                                setResult(2);
                                ModifyOrderAddressActivity.this.finish();
                                Toast.makeText(mActivity, object.getMsgContent(), Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(mActivity, json, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void requestError(VolleyError e) {
                            Toast.makeText(mActivity, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void resultFail(String json) {
                            Toast.makeText(mActivity, json, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
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
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
