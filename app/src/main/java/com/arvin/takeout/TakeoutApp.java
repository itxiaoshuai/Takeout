package com.arvin.takeout;

import android.app.Application;
import android.content.Context;

/**
 * Created by Arvin on 2017/7/28 19:50
 * E-Mail Address：it_xiaoshuai@163.com
 */

public class TakeoutApp extends Application {
    public static Context sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
    }
}
