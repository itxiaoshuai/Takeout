package com.arvin.takeout;

import android.app.Application;
import android.content.Context;

import com.arvin.takeout.model.beans.User;

/**
 * Created by Arvin on 2017/7/28 19:50
 * E-Mail Address：it_xiaoshuai@163.com
 */

public class TakeoutApp extends Application {
    public static Context sInstance;
    public static User sUser;

    public static void setUser(User user) {
        sUser = user;
    }
    public static User getUser() {
        return sUser;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        //初始化赋值user
        sUser = new User();
        sUser.setId(-1);//未登录为-1
    }
}
