package com.efithealth.app.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.efithealth.R;
import com.efithealth.app.javabean.BeanLunchOrderList;
import com.efithealth.app.javabean.BeanMsjOrderList;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 马小布 on 2016/8/25.
 */
public class AdapterLunchOrderFrg extends RecyclerView.Adapter {
    private Activity mActivity;
    private List<BeanLunchOrderList.ForderListBean> mData;

    public AdapterLunchOrderFrg(Activity activity, List<BeanLunchOrderList.ForderListBean> mData) {
        mActivity = activity;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_msj_order_list_frg, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        BeanLunchOrderList.ForderListBean listBean = mData.get(position);
        //0 代付款；1 待收货；2已完成
        Glide.with(mActivity).load(listBean.getImagesfilename()).placeholder(R.mipmap.ic_place_holder).into(viewHolder.mIvPhoto);
        viewHolder.mTvTitle.setText(listBean.getClubname());
        viewHolder.mTvContent.setText(listBean.getMername()+" X"+listBean.getMbfordermerlist().get(0).getBuynum());
        viewHolder.mTvPrice.setText(String.valueOf(listBean.getOrdamt()) + "元");
        viewHolder.mTvPayPrice.setText(String.valueOf(listBean.getPayamt()) + "元");

        if (listBean.getOrdstatus().equals("0")) {//未完成
            if (listBean.getPaystatus().equals("0")) {//待付款
                viewHolder.mTvComplete.setText("待付款");
                viewHolder.mTvPay.setText("继续支付");
                viewHolder.mTvPay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO: 2016/8/25 付款

                    }
                });
                viewHolder.mTvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO: 2016/8/25 删除
                    }
                });

            } else {//已付款 为评价
                viewHolder.mTvComplete.setText("未完成");
                viewHolder.mTvPay.setText("延期");
                viewHolder.mTvDelete.setText("修改地址");
                viewHolder.mTvPay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO: 2016/8/25 付款

                    }
                });
                viewHolder.mTvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO: 2016/8/25 删除
                    }
                });

            }
        } else {//2已评价
            viewHolder.mTvComplete.setText("已完成");
            viewHolder.mTvPay.setText("再来一单");
            viewHolder.mTvPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: 2016/8/25 再来一旦去
                }
            });
            viewHolder.mTvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: 2016/8/25 删除去
                }
            });

        }

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_photo)
        ImageView mIvPhoto;
        @Bind(R.id.tv_title)
        TextView mTvTitle;
        @Bind(R.id.tv_content)
        TextView mTvContent;
        @Bind(R.id.tv_complete)
        TextView mTvComplete;
        @Bind(R.id.tv_price)
        TextView mTvPrice;
        @Bind(R.id.tv_pay_price)
        TextView mTvPayPrice;
        @Bind(R.id.tv_pay)
        TextView mTvPay;
        @Bind(R.id.tv_delete)
        TextView mTvDelete;
        @Bind(R.id.ly_nopay)
        LinearLayout mlyNopay;
        @Bind(R.id.tv_evaluate)
        TextView mTvEvaluate;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
