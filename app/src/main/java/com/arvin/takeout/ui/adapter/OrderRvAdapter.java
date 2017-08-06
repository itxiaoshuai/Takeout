package com.arvin.takeout.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arvin.takeout.R;
import com.arvin.takeout.model.beans.Order;
import com.arvin.takeout.model.beans.OrderDetail;
import com.arvin.takeout.ui.activity.OrderDetailActivity;
import com.arvin.takeout.utils.OrderObservable;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Arvin on 2017/8/1 12:23
 * E-Mail Address：it_xiaoshuai@163.com
 */

public class OrderRvAdapter extends RecyclerView.Adapter implements Observer {

    private Context mContext;
    private List<Order> mOrderList;

    public OrderRvAdapter(Context context, List<Order> orderList) {
        mContext = context;
        mOrderList = orderList;
        //让adapter作为观察者和被观察者绑定关系
        OrderObservable.getInstance().addObserver(this);
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

    @Override
    public void update(Observable o, Object arg) {
        HashMap<String, String> oderMap = (HashMap<String, String>) arg;
        String orderId = oderMap.get("orderId");
        String type = oderMap.get("type");
        //订单列表item的位置
        int index = -1;
        //遍历订单列表，如果orderId，相同，就表示这个用户的订单要刷新状态
        for (int i = 0; i < mOrderList.size(); i++) {
            Order order = mOrderList.get(i);
            if (order.getId().equals(orderId)) {
                order.setType(type);
                Log.e("jpush", getOrderTypeInfo(type));
                index = i;
                Log.e("jpush", "i:" + i);
            }
        }
        if(index !=-1){
            //只修改需要修改的那一个
            notifyItemChanged(index);
        }
    }

    private String getOrderTypeInfo(String type) {
        /**
         * 订单状态
         * 1 未支付 2 已提交订单 3 商家接单  4 配送中,等待送达 5已送达 6 取消的订单
         */
//            public static final String ORDERTYPE_UNPAYMENT = "10";
//            public static final String ORDERTYPE_SUBMIT = "20";
//            public static final String ORDERTYPE_RECEIVEORDER = "30";
//            public static final String ORDERTYPE_DISTRIBUTION = "40";
//            public static final String ORDERTYPE_SERVED = "50";
//            public static final String ORDERTYPE_CANCELLEDORDER = "60";

        String typeInfo = "";
        switch (type) {
            case OrderObservable.ORDERTYPE_UNPAYMENT:
                typeInfo = "未支付";
                break;
            case OrderObservable.ORDERTYPE_SUBMIT:
                typeInfo = "已提交订单";
                break;
            case OrderObservable.ORDERTYPE_RECEIVEORDER:
                typeInfo = "商家接单";
                break;
            case OrderObservable.ORDERTYPE_DISTRIBUTION:
                typeInfo = "配送中";
                break;
            case OrderObservable.ORDERTYPE_SERVED:
                typeInfo = "已送达";
                break;
            case OrderObservable.ORDERTYPE_CANCELLEDORDER:
                typeInfo = "取消的订单";
                break;
        }
        return typeInfo;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
        private Order mOrder;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            view.setOnClickListener(this);
        }

        public void setData(Order order) {
            this.mOrder=order;
            mTvOrderItemSellerName.setText(order.getSeller().getName());
            mTvOrderItemType.setText(getOrderTypeInfo(order.getType()));
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(mContext, OrderDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("order",mOrder);
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        }
    }
}
