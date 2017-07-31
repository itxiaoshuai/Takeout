package com.arvin.takeout.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.arvin.takeout.TakeoutApp;
import com.arvin.takeout.model.beans.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Arvin on 2017/7/29 22:52
 * E-Mail Address：it_xiaoshuai@163.com
 */

public class TakeoutOpenHelper extends OrmLiteSqliteOpenHelper {
    private static TakeoutOpenHelper sTakeoutOpenHelper;

    public static TakeoutOpenHelper getInstance() {
        if (sTakeoutOpenHelper == null) {
            sTakeoutOpenHelper = new TakeoutOpenHelper(TakeoutApp.sInstance);
        }
        return sTakeoutOpenHelper;
    }

    public TakeoutOpenHelper(Context context) {
        super(context, "take", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        //根据javabean生成sqlite的数据表
        try {
            TableUtils.clearTable(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
