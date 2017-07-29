package com.arvin.takeout.dagger2.component;

import com.arvin.takeout.dagger2.module.LoginActivityModule;
import com.arvin.takeout.ui.activity.LoginActivity;

import dagger.Component;
import dagger.Module;

/**
 * Created by Arvin on 2017/7/29 21:29
 * E-Mail Address：it_xiaoshuai@163.com
 */
@Component(modules = LoginActivityModule.class)
public interface LoginActivityComponent {
    void in(LoginActivity loginActivity);
}
