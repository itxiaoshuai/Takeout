package com.arvin.takeout.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arvin.takeout.R;
import com.arvin.takeout.TakeoutApp;
import com.arvin.takeout.dagger2.component.DaggerOrderFragmentComponent;
import com.arvin.takeout.dagger2.module.OrderFragmentModule;
import com.arvin.takeout.model.beans.Order;
import com.arvin.takeout.model.beans.User;
import com.arvin.takeout.presenter.OrderFragmentPresenter;
import com.arvin.takeout.ui.adapter.OrderRvAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Arvin on 2017/7/27 16:26
 * E-Mail Address：it_xiaoshuai@163.com
 */
public class OrderFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.rv_order_list)
    RecyclerView mRvOrderList;
    @InjectView(R.id.srl_order)
    public SwipeRefreshLayout mSrlOrder;
    private TextView mTextView;
    private View mView;
    private List<Order> mOrderList = new ArrayList<>();
    @Inject
    OrderFragmentPresenter mOrderFragmentPresenter;
    public OrderRvAdapter mOrderRvAdapter;
    private User mUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = View.inflate(getContext(), R.layout.fragment_order, null);
        ButterKnife.inject(this, mView);
        //初始化presenter
        DaggerOrderFragmentComponent.builder().orderFragmentModule(new OrderFragmentModule(this)).build().in(this);
        mOrderRvAdapter = new OrderRvAdapter(getContext(), mOrderList);
        mRvOrderList.setAdapter(mOrderRvAdapter);
        mRvOrderList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUser = TakeoutApp.getUser();
        if (mUser.getId() != -1) {
            //开始刷新加载数据
            mSrlOrder.setRefreshing(true);
            mOrderFragmentPresenter.getOrderList(mUser.getId());
            //设置下拉刷新监听
            mSrlOrder.setOnRefreshListener(this);
        }else {
            Toast.makeText(getContext(), "请先登入", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onRefresh() {
        mOrderFragmentPresenter.getOrderList(mUser.getId());
    }
}
