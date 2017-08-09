package com.arvin.takeout.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.arvin.takeout.ui.fragment.CommentFragment;
import com.arvin.takeout.ui.fragment.GoodsFragment;
import com.arvin.takeout.ui.fragment.SellerFragment;

/**
 * Created by Arvin on 2017/8/9 11:42
 * E-Mail Address：it_xiaoshuai@163.com
 */
public class BussinessAdapter extends FragmentPagerAdapter {
    public BussinessAdapter(FragmentManager fm) {
        super(fm);
    }
    private String[] mTitles = new String[]{"商品", "评价", "商家"};
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new GoodsFragment();
                break;
            case 1:
                fragment=new CommentFragment();
                break;
            case 2:
                fragment=new SellerFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }
}
