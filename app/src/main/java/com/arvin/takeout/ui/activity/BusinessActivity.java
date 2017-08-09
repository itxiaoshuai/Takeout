package com.arvin.takeout.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arvin.takeout.R;
import com.arvin.takeout.adapter.BussinessAdapter;
import com.flipboard.bottomsheet.BottomSheetLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Arvin on 2017/8/6 16:46
 * E-Mail Address：it_xiaoshuai@163.com
 */
public class BusinessActivity extends AppCompatActivity {
    @InjectView(R.id.ib_back)
    ImageButton mIbBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.ib_menu)
    ImageButton mIbMenu;
    @InjectView(R.id.vp)
    ViewPager mVp;
    @InjectView(R.id.bottomSheetLayout)
    BottomSheetLayout mBottomSheetLayout;
    @InjectView(R.id.imgCart)
    ImageView mImgCart;
    @InjectView(R.id.tvSelectNum)
    TextView mTvSelectNum;
    @InjectView(R.id.tvCountPrice)
    TextView mTvCountPrice;
    @InjectView(R.id.tvSendPrice)
    TextView mTvSendPrice;
    @InjectView(R.id.tvDeliveryFee)
    TextView mTvDeliveryFee;
    @InjectView(R.id.tvSubmit)
    TextView mTvSubmit;
    @InjectView(R.id.bottom)
    LinearLayout mBottom;
    @InjectView(R.id.fl_Container)
    FrameLayout mFlContainer;
    @InjectView(R.id.tabs)
    TabLayout mTabs;

    private String[] mTitles = new String[]{"商品", "评价", "商家"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        ButterKnife.inject(this);
        mVp.setAdapter(new BussinessAdapter(getSupportFragmentManager()));
        initTab();//指示器
    }

    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            TabLayout.Tab tab=mTabs.newTab();
            tab.setText(mTitles[i]);
            mTabs.addTab(tab);
        }
        //与viewpager进行绑定
        mTabs.setupWithViewPager(mVp);
    }

    @OnClick({R.id.ib_back, R.id.tv_title, R.id.ib_menu, R.id.vp, R.id.bottomSheetLayout, R.id.imgCart, R.id.tvSelectNum, R.id.tvCountPrice, R.id.tvSendPrice, R.id.tvDeliveryFee, R.id.tvSubmit, R.id.bottom, R.id.fl_Container})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                break;
            case R.id.tv_title:
                break;
            case R.id.ib_menu:
                break;
            case R.id.vp:
                break;
            case R.id.bottomSheetLayout:
                break;
            case R.id.imgCart:
                break;
            case R.id.tvSelectNum:
                break;
            case R.id.tvCountPrice:
                break;
            case R.id.tvSendPrice:
                break;
            case R.id.tvDeliveryFee:
                break;
            case R.id.tvSubmit:
                break;
            case R.id.bottom:
                break;
            case R.id.fl_Container:
                break;
        }
    }
}
