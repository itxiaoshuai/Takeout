package com.arvin.takeout.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arvin.takeout.R;
import com.arvin.takeout.ui.activity.LoginActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Arvin on 2017/7/27 16:26
 * E-Mail Address：it_xiaoshuai@163.com
 */
public class UserFragment extends Fragment {

    @InjectView(R.id.tv_user_setting)
    ImageView mTvUserSetting;
    @InjectView(R.id.iv_user_notice)
    ImageView mIvUserNotice;
    @InjectView(R.id.login)
    ImageView mLogin;
    @InjectView(R.id.username)
    TextView mUsername;
    @InjectView(R.id.phone)
    TextView mPhone;
    @InjectView(R.id.ll_userinfo)
    LinearLayout mLlUserinfo;
    @InjectView(R.id.iv_address_manager)
    ImageView mIvAddressManager;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = View.inflate(getContext(), R.layout.fragment_user, null);
        ButterKnife.inject(this, mView);
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

    @OnClick({R.id.tv_user_setting, R.id.login, R.id.ll_userinfo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_user_setting:
                break;
            case R.id.login:
                Intent intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_userinfo:
                break;
        }
    }
}
