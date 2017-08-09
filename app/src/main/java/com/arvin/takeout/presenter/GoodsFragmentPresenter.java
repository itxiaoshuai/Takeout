package com.arvin.takeout.presenter;

import android.util.Log;

import com.arvin.takeout.model.beans.BusinessInfo;
import com.arvin.takeout.model.beans.GoodsInfo;
import com.arvin.takeout.model.beans.GoodsTypeInfo;
import com.arvin.takeout.model.net.ResponseInfo;
import com.arvin.takeout.ui.fragment.GoodsFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by Arvin on 2017/8/9 17:31
 * E-Mail Address：it_xiaoshuai@163.com
 */
public class GoodsFragmentPresenter extends BasePresenter {
    GoodsFragment mGoodsFragment;
    private List<GoodsTypeInfo> mGoodsTypeInfoList;

    public GoodsFragmentPresenter(GoodsFragment goodsFragment) {
        mGoodsFragment = goodsFragment;
    }

    /**
     * @描述 ：获取商品信息,通过retrofit获取数据
     * @参数：sellerId 商家ID
     */
    public void getGoodsInfo(int sellerId) {
        Call<ResponseInfo> businessCall = mRequest.getBusinessInfo(sellerId);
        businessCall.enqueue(mInfoCallback);
    }

    /**
     * @param data 请求返回的字段
     * @描述：处理请求来的返回的json数据
     */
    @Override
    protected void parserJson(String data) {
        List<GoodsInfo> mGoodsInfoList = new ArrayList<>();
        Gson gson = new Gson();
        //将json数据转化成bean
        BusinessInfo businessInfo = gson.fromJson(data, BusinessInfo.class);
        Log.e("goods",businessInfo.toString());
        mGoodsTypeInfoList = businessInfo.getList();
//        for (int i = 0; i <mGoodsTypeInfoList.size() ; i++) {
//            GoodsTypeInfo goodsTypeInfo=mGoodsTypeInfoList.get(i);
//            for (int j = 0; j <goodsTypeInfo.getList().size() ; j++) {
//                GoodsInfo goodsInfo = goodsTypeInfo.getList().get(j);
//                goodsInfo.setTypeName(goodsTypeInfo.getName());
//                goodsInfo.setTypeId(goodsTypeInfo.getId());
//                mGoodsInfoList.add(goodsInfo);
//            }
//        }
        mGoodsFragment.mGoodsTypeAdapter.setGoodsTypeInfoList(mGoodsTypeInfoList);
    }
}
