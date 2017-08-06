package com.arvin.takeout.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arvin.takeout.R;
import com.arvin.takeout.model.beans.Order;
import com.arvin.takeout.utils.OrderObservable;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Arvin on 2017/8/6 10:40
 * E-Mail Address：it_xiaoshuai@163.com
 */
public class OrderDetailActivity extends AppCompatActivity implements Observer {
    @InjectView(R.id.iv_order_detail_back)
    ImageView mIvOrderDetailBack;
    @InjectView(R.id.tv_seller_name)
    TextView mTvSellerName;
    @InjectView(R.id.tv_order_detail_time)
    TextView mTvOrderDetailTime;
    @InjectView(R.id.ll_order_detail_type_container)
    LinearLayout mLlOrderDetailTypeContainer;
    @InjectView(R.id.ll_order_detail_type_point_container)
    LinearLayout mLlOrderDetailTypePointContainer;
    private Order mOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.inject(this);
        //OrderDetailActivity作为观察者和被观察者绑定关系
        OrderObservable.getInstance().addObserver(this);
        if(getIntent()!=null){
            mOrder = (Order)getIntent().getSerializableExtra("order");
            int index = getIndex(mOrder.getType());
            //点设置为蓝色
            mLlOrderDetailTypePointContainer.getChildAt(index).setBackgroundResource(R.drawable.order_time_node_disabled);
            ((TextView) mLlOrderDetailTypeContainer.getChildAt(index)).setTextColor(Color.BLUE);
        }

    }

    @OnClick({R.id.iv_order_detail_back, R.id.tv_seller_name, R.id.tv_order_detail_time, R.id.ll_order_detail_type_container, R.id.ll_order_detail_type_point_container})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_order_detail_back:
                break;
            case R.id.tv_seller_name:
                break;
            case R.id.tv_order_detail_time:
                break;
            case R.id.ll_order_detail_type_container:
                break;
            case R.id.ll_order_detail_type_point_container:
                break;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        HashMap<String, String> mapOrder = (HashMap<String, String>) arg;
        String orderId = mapOrder.get("orderId");
        String type = mapOrder.get("type");
        if (mOrder.getId().equals(orderId)){
            int index = getIndex(type);
            //更新UI
            for(int j=0;j<mLlOrderDetailTypePointContainer.getChildCount();j++){
                if(j == index){
                    mLlOrderDetailTypePointContainer.getChildAt(index).setBackgroundResource(R.drawable.order_time_node_disabled);
                    ((TextView) mLlOrderDetailTypeContainer.getChildAt(index)).setTextColor(Color.BLUE);
                }else{
                    mLlOrderDetailTypePointContainer.getChildAt(j).setBackgroundResource(R.drawable.order_time_node_normal);
                    ((TextView) mLlOrderDetailTypeContainer.getChildAt(j)).setTextColor(Color.GRAY);
                }
            }
        }

    }
    private int getIndex(String type) {
        int index = -1;
//        String typeInfo = "";
        switch (type) {
            case OrderObservable.ORDERTYPE_UNPAYMENT:
//                typeInfo = "未支付";
                break;
            case OrderObservable.ORDERTYPE_SUBMIT:
//                typeInfo = "已提交订单";
                index = 0;
                break;
            case OrderObservable.ORDERTYPE_RECEIVEORDER:
//                typeInfo = "商家接单";
                index = 1;
                break;
            case OrderObservable.ORDERTYPE_DISTRIBUTION:
//                typeInfo = "配送中";
                index = 2;
                break;
            case OrderObservable.ORDERTYPE_SERVED:
//                typeInfo = "已送达";
                index = 3;
                break;
            case OrderObservable.ORDERTYPE_CANCELLEDORDER:
//                typeInfo = "取消的订单";
                break;
        }
        return index;
    }
}
