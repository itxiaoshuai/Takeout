package com.arvin.takeout.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arvin.takeout.R;
import com.arvin.takeout.dagger2.component.DaggerOrderFragmentComponent;
import com.arvin.takeout.dagger2.module.OrderFragmentModule;
import com.arvin.takeout.presenter.OrderFragmentPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Arvin on 2017/7/27 16:26
 * E-Mail Address：it_xiaoshuai@163.com
 */
public class OrderFragment extends Fragment {

    @InjectView(R.id.rv_order_list)
    RecyclerView mRvOrderList;
    @InjectView(R.id.srl_order)
    SwipeRefreshLayout mSrlOrder;
    private TextView mTextView;
    private View mView;
    @Inject
    OrderFragmentPresenter mOrderFragmentPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = View.inflate(getContext(), R.layout.fragment_order, null);
        ButterKnife.inject(this, mView);
        DaggerOrderFragmentComponent.builder().orderFragmentModule(new OrderFragmentModule(this)).build().in(this);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
