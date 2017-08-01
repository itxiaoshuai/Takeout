package com.arvin.takeout.presenter;

import com.arvin.takeout.model.beans.Order;
import com.arvin.takeout.model.net.ResponseInfo;
import com.arvin.takeout.ui.fragment.OrderFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Arvin on 2017/7/28 18:13
 * E-Mail Address：it_xiaoshuai@163.com
 */

public class OrderFragmentPresenter extends BasePresenter{
    public OrderFragmentPresenter(OrderFragment orderFragment) {
        mOrderFragment = orderFragment;
    }

    OrderFragment mOrderFragment;
    @Override
    protected void parserJson(String data) {
        Gson gson=new Gson();
        List<Order> orderList=gson.fromJson(data,new TypeToken<List<Order>>(){}.getType());
        //把数据set到recycleview
        mOrderFragment.mOrderRvAdapter.setOrderList(orderList);
    }
    public void getOrderList(int userId){
        Call<ResponseInfo> orderListCall=mRequest.getOrderList(userId);
        orderListCall.enqueue(mInfoCallback);
    }
}
