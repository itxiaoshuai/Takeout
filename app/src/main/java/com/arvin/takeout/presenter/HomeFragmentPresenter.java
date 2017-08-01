package com.arvin.takeout.presenter;

import android.util.Log;

import com.arvin.takeout.model.beans.Seller;
import com.arvin.takeout.model.net.ResponseInfo;
import com.arvin.takeout.ui.fragment.HomeFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;

/**
 * Created by Arvin on 2017/7/28 00:28
 * E-Mail Address：it_xiaoshuai@163.com
 */

public class HomeFragmentPresenter extends BasePresenter {
    HomeFragment mHomeFragment;

    public HomeFragmentPresenter(HomeFragment homeFragment) {
        mHomeFragment = homeFragment;
    }
    public void getHomeData() {
        Call<ResponseInfo> call = mRequest.getHomeInfo();
        //此处使用异步，因为没有开启子线程
        call.enqueue(mInfoCallback);
    }

    @Override
    protected void parserJson(String data) {
        try {
            //1.解析数据
            JSONObject object = new JSONObject(data);
            String nearby = object.getString("nearbySellerList");
            String other = object.getString("otherSellerList");
            Gson gson = new Gson();
            //令牌解析得到附近的商家
            ArrayList<Seller> nearbySellers = gson.fromJson(nearby, new TypeToken<ArrayList<Seller>>() {
            }.getType());
            ArrayList<Seller> otherSellers = gson.fromJson(other, new TypeToken<ArrayList<Seller>>() {
            }.getType());

            // 2.填充数据
            //首先要拿到homeFragment才能拿到homeAdapter
            mHomeFragment.mHomeRvAdapter.setDatas(nearbySellers,otherSellers);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
