package com.arvin.takeout.presenter;

import com.arvin.takeout.module.net.ResponseInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arvin on 2017/7/28 00:28
 * E-Mail Address：it_xiaoshuai@163.com
 */

public class HomeFragmentPresenter extends BasePresenter {
    public void getHomeData() {
        Call<ResponseInfo> call = mRequest.getHomeInfo();
        //此处使用异步，因为没有开启子线程
        call.enqueue(mInfoCallback);
    }

    @Override
    protected void parserJson(String data) {

    }
}
