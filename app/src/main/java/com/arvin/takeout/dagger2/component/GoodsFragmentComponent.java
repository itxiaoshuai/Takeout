package com.arvin.takeout.dagger2.component;

import com.arvin.takeout.module.GoodsFragmentModule;
import com.arvin.takeout.ui.fragment.GoodsFragment;

import dagger.Component;
import dagger.Module;

/**
 * Created by Arvin on 2017/8/9 17:34
 * E-Mail Address：it_xiaoshuai@163.com
 */
@Component(modules = GoodsFragmentModule.class)
public interface GoodsFragmentComponent {
    void in(GoodsFragment goodsFragment);
}
