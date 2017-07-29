package com.arvin.takeout.dagger2.module;

import com.arvin.takeout.presenter.LoginActivityPresenter;
import com.arvin.takeout.ui.activity.LoginActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Arvin on 2017/7/29 21:29
 * E-Mail Address：it_xiaoshuai@163.com
 */
@Module
public class LoginActivityModule {
    public LoginActivityModule(LoginActivity loginActivity) {
        mLoginActivity = loginActivity;
    }

    LoginActivity mLoginActivity;
    @Provides
    LoginActivityPresenter privateLoginActivityPresenter() {
        return new LoginActivityPresenter(mLoginActivity);
    }
}
