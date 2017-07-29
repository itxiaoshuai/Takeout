package com.arvin.takeout.ui.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.arvin.takeout.ui.fragment.HomeFragment;
import com.arvin.takeout.ui.fragment.MoreFragment;
import com.arvin.takeout.ui.fragment.OrderFragment;
import com.arvin.takeout.R;
import com.arvin.takeout.ui.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.main_fragment_container)
    FrameLayout mMainFragmentContainer;
    @InjectView(R.id.main_bottome_switcher_container)
    LinearLayout mMainBottomeSwitcherContainer;

    //list集合存储fragment
    private List<Fragment> mFragmentList = new ArrayList<>();
    private String [] mTitles=new String[]{"首页","个人","订单","更多"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        //下面的代码可以写在BaseActivity里面
        highApiEffects();
        initFragment();
        initBottom();
        changeTab(0);
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void highApiEffects() {
        getWindow().getDecorView().setFitsSystemWindows(true);
        //透明状态栏 @顶部
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏 @底部 这一句不要加，目的是防止沉浸式状态栏和部分底部自带虚拟按键的手机（比如华为）发生冲突，注释掉就好了
// getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }
    /**
     * 初始化fragment,并添加到集合
     */
    private void initFragment() {
        for (int i = 0; i <mTitles.length ; i++) {
            switch (i){
                case 0:
                    mFragmentList.add(new HomeFragment());
                    break;
                case 1:
                    mFragmentList.add(new OrderFragment());
                    break;
                case 2:
                    mFragmentList.add(new UserFragment());
                    break;
                case 3:
                    mFragmentList.add(new MoreFragment());
                    break;
            }
        }

    }

    private void initBottom() {
        //进入时禁用首页按钮，然后点击哪个就禁用哪个页面
        //1.给底部所有按钮（孩子）加上点击事件
        int childCount = mMainBottomeSwitcherContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = mMainBottomeSwitcherContainer.getChildAt(i);
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = mMainBottomeSwitcherContainer.indexOfChild(child);
                    changeTab(index);
                }
            });
        }
    }

    private void changeTab(int index) {
        //点击了第index个，禁用它，其他的就启用
        int count = mMainBottomeSwitcherContainer.getChildCount();
        for(int i=0;i<count;i++){
            final View child = mMainBottomeSwitcherContainer.getChildAt(i);
            if(i == index){
                //选中的禁用
                setEnable(child, false);
            }else{
                setEnable(child, true);
            }
        }
        //切换fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container,mFragmentList.get(index)).commit();
    }

    private void setEnable(View child, boolean isEnable) {
        child.setEnabled(isEnable);
        if (child instanceof ViewGroup) {
            ViewGroup childViewGroup = (ViewGroup) child;
            //禁用孙子
            for (int i = 0; i < childViewGroup.getChildCount(); i++) {
                setEnable(childViewGroup.getChildAt(i), isEnable);
            }
        }
    }

    @OnClick({R.id.main_fragment_container, R.id.main_bottome_switcher_container})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_fragment_container:
                break;
            case R.id.main_bottome_switcher_container:
                break;
        }
    }
}
