package com.arvin.takeout.dagger2.component;

import com.arvin.takeout.dagger2.module.OrderFragmentModule;
import com.arvin.takeout.ui.fragment.OrderFragment;

import dagger.Component;
import dagger.Module;

/**
 * Created by Arvin on 2017/7/31 16:48
 * E-Mail Address：it_xiaoshuai@163.com
 */
@Component(modules=OrderFragmentModule.class)
public interface OrderFragmentComponent {
    void in(OrderFragment orderFragment);
}
