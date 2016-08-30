package com.efithealth.app.maxiaobu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.efithealth.R;

import butterknife.Bind;


/**
 * Created by 马小布 on 2016/8/29.
 */
public class ItemDispatchDetailView extends RelativeLayout {
    public static String TAG = "ItemDispatchDetailView";
    public static String NAME_SPACE = "http://schemas.android.com/apk/res-auto";
    TextView mTvDate;
    TextView mTvWeek;
    TextView mTvState;
    TextView mTvLunch;


    public ItemDispatchDetailView(Context context) {
        super(context);
        initView();
    }

    public ItemDispatchDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public ItemDispatchDetailView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, null);
    }

    private void initView() {
//        Log.i(TAG, "initView(): 构造调用");

        View inflate = View.inflate(getContext(), R.layout.item_dispatch_detail_view, this);
        mTvDate = (TextView) inflate.findViewById(R.id.tv_date);
        mTvWeek = (TextView) inflate.findViewById(R.id.tv_week);
        mTvLunch = (TextView) inflate.findViewById(R.id.tv_lunch);
        mTvState = (TextView) inflate.findViewById(R.id.tv_state);
    }

    public void setComment(String date, String week, String lunch, String state) {
//        View.inflate(getContext(), R.layout.item_dispatch_detail_view, this);
        if ("".equals(date)){
            mTvDate.setVisibility(INVISIBLE);
        }else {
            mTvDate.setText(date);
        }

        mTvWeek.setText(week);
        mTvLunch.setText(lunch);
        mTvState.setText(state);
    }
}
