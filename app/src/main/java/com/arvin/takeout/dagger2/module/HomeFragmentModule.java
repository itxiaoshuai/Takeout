package com.arvin.takeout.dagger2.module;

import com.arvin.takeout.presenter.HomeFragmentPresenter;
import com.arvin.takeout.ui.activity.MainActivity;
import com.arvin.takeout.ui.fragment.HomeFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Arvin on 2017/7/29 11:53
 * E-Mail Address：it_xiaoshuai@163.com
 */
@Module
public class HomeFragmentModule {
    HomeFragment mHomeFragment;

    public HomeFragmentModule(HomeFragment homeFragment) {
        mHomeFragment = homeFragment;
    }

    @Provides
    HomeFragmentPresenter providerHomeFragmentPresenter() {
        return new HomeFragmentPresenter(mHomeFragment);
    }
}
