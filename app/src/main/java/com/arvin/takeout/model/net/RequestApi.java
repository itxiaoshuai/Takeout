package com.arvin.takeout.model.net;

import com.arvin.takeout.utils.Constants;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Arvin on 2017/7/28 19:05
 * E-Mail Address：it_xiaoshuai@163.com
 */

/**
 * retrofit网络请求的封装
 */
public interface RequestApi {
    @GET(Constants.HOME)//主页
        //返回的数据
    Call<ResponseInfo> getHomeInfo();

    @GET(Constants.ORDER)//订单
        //返回的数据
    Call<ResponseInfo> getOrderList();

    @GET(Constants.LOGIN)//登入
    Call<ResponseInfo> login(@QueryMap Map<String, String> params);
}
