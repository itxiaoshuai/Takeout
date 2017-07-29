package com.arvin.takeout.presenter;

import android.widget.Toast;

import com.arvin.takeout.TakeoutApp;
import com.arvin.takeout.model.net.RequestApi;
import com.arvin.takeout.model.net.ResponseInfo;
import com.arvin.takeout.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Arvin on 2017/7/28 18:14
 * E-Mail Address：it_xiaoshuai@163.com
 */

/**
 * 封装Retrofit实例
 */
public abstract class BasePresenter {

    protected Retrofit mRetrofit;
    protected final RequestApi mRequest;

    public BasePresenter() {
        mRetrofit = new Retrofit.Builder().baseUrl(Constants.HOST).addConverterFactory(GsonConverterFactory.create()).build();
        //构建一个request
        mRequest = mRetrofit.create(RequestApi.class);
    }

    protected Callback<ResponseInfo> mInfoCallback = new Callback<ResponseInfo>() {
        @Override
        public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {
            ResponseInfo responseInfo = response.body();
            if (responseInfo.code.equals("0")){//链接成功
                String data=responseInfo.data;
                parserJson(data);
            }else if (responseInfo.code.equals("-1")){
                Toast.makeText(TakeoutApp.sInstance,"服务器",Toast.LENGTH_SHORT).show();
            }else {
                onFailure(call,new RuntimeException("其他错误"));
            }
        }

        @Override
        public void onFailure(Call<ResponseInfo> call, Throwable t) {
            Toast.makeText(TakeoutApp.sInstance,"没连上服务器",Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 这里作为抽象方法，子类想怎么解析由他自己决定
     * @param data
     */
    protected abstract void parserJson(String data);

}
