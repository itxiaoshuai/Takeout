package com.arvin.takeout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Arvin on 2017/7/27 16:26
 * E-Mail Address：it_xiaoshuai@163.com
 */
public class OrderFragment extends android.support.v4.app.Fragment {

    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mTextView = new TextView(getContext());

        return mTextView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTextView.setText("订单");
        mTextView.setTextColor(getResources().getColor(R.color.colorAccent));
        mTextView.setTextSize(18);
        mTextView.setGravity(Gravity.CENTER);
    }
}
