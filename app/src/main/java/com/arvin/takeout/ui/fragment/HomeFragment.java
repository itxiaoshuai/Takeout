package com.arvin.takeout.ui.fragment;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arvin.takeout.R;
import com.arvin.takeout.ui.adapter.HomeRvAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Arvin on 2017/7/27 16:26
 * E-Mail Address：it_xiaoshuai@163.com
 */
public class HomeFragment extends Fragment {

    @InjectView(R.id.rv_home)
    RecyclerView mRvHome;
    @InjectView(R.id.home_tv_address)
    TextView mHomeTvAddress;
    @InjectView(R.id.ll_title_search)
    LinearLayout mLlTitleSearch;
    @InjectView(R.id.ll_title_container)
    LinearLayout mLlTitleContainer;
    private View mView;
    private List<String> mDatas = new ArrayList<>();
    private HomeRvAdapter mHomeRvAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = View.inflate(getContext(), R.layout.fragment_home, null);
        ButterKnife.inject(this, mView);
        mHomeRvAdapter = new HomeRvAdapter(getContext(), mDatas);
        //1.设置适配器
        mRvHome.setAdapter(mHomeRvAdapter);
        //设置布局样式
        mRvHome.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return mView;
    }

    int sumY;//当前滚动的距离
    float distance = 200.00f;  //滚动到150.00颜色最深，alpha值最大,临界值
    int startbgColor = 0x553190E8;
    int endbgColor = 0xff3190E8;
    int bgcolor = 0;//背景色变量
    ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 2 加载数据
        LoadData();
        mRvHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //需要知道y方向滚动多少，累加得到总共滚动多少；
                sumY += dy;
                if (sumY<0){//没有滚动
                    bgcolor=startbgColor;
                }else if (sumY>distance){//超出渐变范围
                    bgcolor=endbgColor;
                }else {
                    bgcolor= (int) mArgbEvaluator.evaluate(sumY/distance,startbgColor,endbgColor);
                }
                mLlTitleContainer.setBackgroundColor(bgcolor);
            }
        });
    }
    private List<String> mNearbySellers = new ArrayList<>();
    private List<String> mOtherSellers = new ArrayList<>();
    private void LoadData() {
        for (int i = 0; i < 10; i++) {
            mNearbySellers.add("我是附近商家：" + i);
            mHomeRvAdapter.setDatas(mDatas, mNearbySellers);
        }
        for (int i = 0; i < 31; i++) {
            mOtherSellers.add("我是普通商家：" + i);
        }
        mHomeRvAdapter.setDatas(mNearbySellers,mOtherSellers);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
