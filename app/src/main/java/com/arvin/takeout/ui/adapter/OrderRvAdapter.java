package com.arvin.takeout.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arvin.takeout.R;
import com.arvin.takeout.model.beans.Order;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Arvin on 2017/8/1 12:23
 * E-Mail Address：it_xiaoshuai@163.com
 */

public class OrderRvAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Order> mOrderList;

    public OrderRvAdapter(Context context, List<Order> orderList) {
        mContext = context;
        mOrderList = orderList;
    }

    public void setOrderList(List<Order> orderList) {
        mOrderList = orderList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = View.inflate(mContext, R.layout.item_order_item, null);
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_order_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(mOrderList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mOrderList != null) {
            return mOrderList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_order_item_seller_logo)
        ImageView mIvOrderItemSellerLogo;
        @InjectView(R.id.tv_order_item_seller_name)
        TextView mTvOrderItemSellerName;
        @InjectView(R.id.tv_order_item_type)
        TextView mTvOrderItemType;
        @InjectView(R.id.tv_order_item_time)
        TextView mTvOrderItemTime;
        @InjectView(R.id.tv_order_item_foods)
        TextView mTvOrderItemFoods;
        @InjectView(R.id.tv_order_item_money)
        TextView mTvOrderItemMoney;
        @InjectView(R.id.tv_order_item_multi_function)
        TextView mTvOrderItemMultiFunction;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(Order order) {
            mTvOrderItemSellerName.setText(order.getSeller().getName());
        }
    }
}
