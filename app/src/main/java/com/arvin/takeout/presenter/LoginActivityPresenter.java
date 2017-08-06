package com.arvin.takeout.presenter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.arvin.takeout.TakeoutApp;
import com.arvin.takeout.model.beans.User;
import com.arvin.takeout.model.dao.TakeoutOpenHelper;
import com.arvin.takeout.model.net.ResponseInfo;
import com.arvin.takeout.ui.activity.LoginActivity;
import com.arvin.takeout.ui.activity.MainActivity;
import com.google.gson.Gson;
import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.sql.Savepoint;
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
        Log.e("login", data);
        Gson gson = new Gson();
        //将json数据转化成javabean
        User user = gson.fromJson(data, User.class);
        Log.e("xxxxx",user.toString());
        if (user.getId() != -1) {
            //1.把登录信息存到本地（保存到内存中），（保存到sqlite中）

            TakeoutApp.setUser(user);

            // a.创建openhelp
            TakeoutOpenHelper openHelper = TakeoutOpenHelper.getInstance();
            SQLiteDatabase db = openHelper.getWritableDatabase();
            AndroidDatabaseConnection androidDataBaseConnection = new AndroidDatabaseConnection(db, true);
            Savepoint startPoint = null;
            // b.创建User对应的Dao
            try {
                // c.使用事物需要数据库连接conection
                startPoint = androidDataBaseConnection.setSavePoint("start");
                // d.使用事物需要取消自动提交
                androidDataBaseConnection.setAutoCommit(false);
                Dao<User, Integer> userDao = openHelper.getDao(User.class);
//            userDao.create(user);
//            userDao.update(user);
                userDao.createIfNotExists(user);
                Log.e("login", "成功存入用户id为" + user.getId());
                androidDataBaseConnection.commit(startPoint);
            } catch (SQLException e) {
                e.printStackTrace();
                if (startPoint != null) {
                    try {
                        androidDataBaseConnection.rollback(startPoint);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                //TODO:2.登录失败，切换UI
                mLoginActivity.changeUi(false);
            }
            //TODO:2.登录成功，切换UI
            mLoginActivity.changeUi(true);
        } else {
            //TODO:2.登录失败，切换UI
            mLoginActivity.changeUi(false);
        }
    }

    public void login(Map<String, String> params) {
        Call<ResponseInfo> callLogin = mRequest.login(params);
        callLogin.enqueue(mInfoCallback);
    }
}
