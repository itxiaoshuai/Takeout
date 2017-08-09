package com.arvin.takeout.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arvin.takeout.R;
import com.arvin.takeout.dagger2.component.DaggerGoodsFragmentComponent;
import com.arvin.takeout.module.GoodsFragmentModule;
import com.arvin.takeout.presenter.GoodsFragmentPresenter;
import com.arvin.takeout.ui.adapter.GoodsTypeAdapter;
import com.arvin.takeout.ui.views.RecycleViewDivider;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by Arvin on 2017/8/9 11:48
 * E-Mail Address：it_xiaoshuai@163.com
 */
public class GoodsFragment extends Fragment {
    @InjectView(R.id.rv_goods_type)
    RecyclerView mRvGoodsType;
    @InjectView(R.id.slhlv)
    StickyListHeadersListView mSlhlv;
    public GoodsTypeAdapter mGoodsTypeAdapter;

    @Inject
    public GoodsFragmentPresenter mGoodsFragmentPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_goods, null);
        ButterKnife.inject(this, view);
        //实例化presenter
        DaggerGoodsFragmentComponent.builder().goodsFragmentModule(new GoodsFragmentModule(this)).build().in(this);
        mGoodsTypeAdapter = new GoodsTypeAdapter(this,getContext());
        mRvGoodsType.setAdapter(mGoodsTypeAdapter);
        //设置布局样式
        mRvGoodsType.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        //增加分割线
        mRvGoodsType.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //TODO:传入正确的商家ID
        //发起请求数据
        mGoodsFragmentPresenter.getGoodsInfo(7);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
