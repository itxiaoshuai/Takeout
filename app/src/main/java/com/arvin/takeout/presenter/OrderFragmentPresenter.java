package com.arvin.takeout.presenter;

import com.arvin.takeout.ui.fragment.OrderFragment;

/**
 * Created by Arvin on 2017/7/28 18:13
 * E-Mail Address：it_xiaoshuai@163.com
 */

public class OrderFragmentPresenter extends BasePresenter{
    public OrderFragmentPresenter(OrderFragment orderFragment) {
        mOrderFragment = orderFragment;
    }

    OrderFragment mOrderFragment;
    @Override
    protected void parserJson(String data) {

    }
}
