package com.arvin.takeout.model.net;

import com.arvin.takeout.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Arvin on 2017/7/28 19:05
 * E-Mail Address：it_xiaoshuai@163.com
 */
public interface RequestApi {
    @GET(Constants.HOME)//主页
        //返回的数据
    Call<ResponseInfo> getHomeInfo();

    @GET(Constants.ORDER)//订单
        //返回的数据
    Call<ResponseInfo> getOrderList();
}
