package com.arvin.takeout.module;

import com.arvin.takeout.presenter.GoodsFragmentPresenter;
import com.arvin.takeout.ui.fragment.GoodsFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Arvin on 2017/8/9 17:35
 * E-Mail Address：it_xiaoshuai@163.com
 */
@Module
public class GoodsFragmentModule {
    public GoodsFragmentModule(GoodsFragment goodsFragment) {
        mGoodsFragment = goodsFragment;
    }

    private GoodsFragment mGoodsFragment;
   @Provides
   GoodsFragmentPresenter providerGoodsFragmentPresenter() {
       return new GoodsFragmentPresenter(mGoodsFragment);
   }
}
