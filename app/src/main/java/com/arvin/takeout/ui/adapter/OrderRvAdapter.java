package com.arvin.takeout.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.arvin.takeout.model.beans.Order;

import java.util.List;

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
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
