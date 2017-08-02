package com.arvin.takeout.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Arvin on 2017/8/2 17:27
 * E-Mail Address：it_xiaoshuai@163.com
 */

public class JpushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("jpush","收到了广播.....");
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            // SDK 向 JPush Server 注册所得到的注册 全局唯一的 ID ，可以通过此 ID 向对应的客户端发送消息和通知。
            String id = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            //保存服务器推送下来的消息的标题。
            String title = bundle.getString(JPushInterface.EXTRA_TITLE);
            //保存服务器推送下来的消息内容
            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            //保存服务器推送下来的附加字段。这是个 JSON 字符串。
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            //保存服务器推送下来的内容类型。
            String type = bundle.getString(JPushInterface.EXTRA_CONTENT_TYPE);

            processExtra(extras);
        }
    }

    private void processExtra(String extras) {
        Log.e("push",extras);
        try {
            JSONObject jsonObject=new JSONObject(extras);
            String orderId =  jsonObject.getString("orderId");
            String type =  jsonObject.getString("type");
            Map<String,String> orderInfo=new HashMap<>();
            orderInfo.put("orderId",orderId);
            orderInfo.put("type",type);
            OrderObservable.getInstance().changeData(orderInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
