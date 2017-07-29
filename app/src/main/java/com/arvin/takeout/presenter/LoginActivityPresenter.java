package com.arvin.takeout.presenter;

import android.util.Log;

import com.arvin.takeout.model.net.ResponseInfo;
import com.arvin.takeout.ui.activity.LoginActivity;
import com.arvin.takeout.ui.activity.MainActivity;

import java.util.Map;

import retrofit2.Call;

/**
 * Created by Arvin on 2017/7/29 21:26
 * E-Mail Address：it_xiaoshuai@163.com
 */

/**
 * 封装登入业务逻辑
 */
public class LoginActivityPresenter extends BasePresenter {
    public LoginActivityPresenter(LoginActivity loginActivity) {
        mLoginActivity = loginActivity;
    }

    LoginActivity mLoginActivity;

    @Override
    protected void parserJson(String data) {
        Log.e("login",data);
    }

    public void login(Map<String, String> params) {
        Call<ResponseInfo> callLogin = mRequest.login(params);
        callLogin.enqueue(mInfoCallback);
    }
}
