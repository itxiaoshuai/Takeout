package com.arvin.takeout.dagger2.module;

import com.arvin.takeout.presenter.OrderFragmentPresenter;
import com.arvin.takeout.ui.fragment.OrderFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Arvin on 2017/7/31 16:51
 * E-Mail Address：it_xiaoshuai@163.com
 */
@Module
public class OrderFragmentModule {
    public OrderFragmentModule(OrderFragment orderFragment) {
        mOrderFragment = orderFragment;
    }

    OrderFragment mOrderFragment;
    @Provides
    public OrderFragmentPresenter provideOrderFragmentPresenter(){
        return new OrderFragmentPresenter(mOrderFragment);
    }
}
