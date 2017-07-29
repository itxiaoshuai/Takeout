package com.arvin.takeout.dagger2.component;

import com.arvin.takeout.dagger2.module.HomeFragmentModule;
import com.arvin.takeout.ui.fragment.HomeFragment;

import dagger.Component;

/**
 * Created by Arvin on 2017/7/29 11:59
 * E-Mail Address：it_xiaoshuai@163.com
 */
@Component(modules = HomeFragmentModule.class)
public interface HomeFragmentComponent {
    void in(HomeFragment homeFragment);
}
