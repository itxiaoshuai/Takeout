package com.arvin.takeout.presenter;

import com.arvin.takeout.ui.activity.LoginActivity;

/**
 * Created by Arvin on 2017/7/29 21:26
 * E-Mail Address：it_xiaoshuai@163.com
 */

public class LoginActivityPresenter extends BasePresenter {
    public LoginActivityPresenter(LoginActivity loginActivity) {
        mLoginActivity = loginActivity;
    }

    LoginActivity mLoginActivity;
    @Override
    protected void parserJson(String data) {

    }
}
