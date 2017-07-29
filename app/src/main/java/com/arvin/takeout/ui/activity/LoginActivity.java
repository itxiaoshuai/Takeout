package com.arvin.takeout.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arvin.takeout.R;
import com.arvin.takeout.dagger2.component.DaggerLoginActivityComponent;
import com.arvin.takeout.dagger2.module.LoginActivityModule;
import com.arvin.takeout.presenter.LoginActivityPresenter;
import com.arvin.takeout.utils.Constants;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Arvin on 2017/7/29 17:12
 * E-Mail Address：it_xiaoshuai@163.com
 */
public class LoginActivity extends AppCompatActivity {

    private static final int CUT_DOWN = -9;
    private static final int RESEND = -8;
    @InjectView(R.id.iv_user_back)
    ImageView mIvUserBack;
    @InjectView(R.id.iv_user_password_login)
    TextView mIvUserPasswordLogin;
    @InjectView(R.id.et_user_phone)
    EditText mEtUserPhone;
    @InjectView(R.id.tv_user_code)
    TextView mTvUserCode;
    @InjectView(R.id.et_user_code)
    EditText mEtUserCode;
    @InjectView(R.id.login)
    TextView mLogin;
    private String mPhoneNum;
    private int mTime;
    @Inject
    LoginActivityPresenter mLoginActivityPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        //初始化LoginActivityPresenter
        DaggerLoginActivityComponent.builder().loginActivityModule(new LoginActivityModule(this)).build().in(this);
        SMSSDK.initSDK(this, Constants.APP_KEY, Constants.APP_SECRET);
        SMSSDK.registerEventHandler(mEventHandler);
    }

    private EventHandler mEventHandler = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    Log.e("sms", "提交验证码成功,可以登录了");
                    //TODO:登录自己的服务器（提交电话号码信息）
                    Map<String,String> params = new HashMap<>();
                    params.put("type", "2"); //类型1为传统登录，类型2位短信登陆，类型3为第三方登录
                    params.put("phoneNum",mPhoneNum);
                    mLoginActivityPresenter.login(params);
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    Log.e("sms", "获取验证码成功");
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
    };

    @OnClick({R.id.iv_user_back, R.id.tv_user_code, R.id.login})
    public void onClick(View view) {
        mPhoneNum = mEtUserPhone.getText().toString().trim();
        switch (view.getId()) {
            case R.id.iv_user_back:
                onBackPressed();
                break;
            case R.id.tv_user_code:
                //点击获取验证码
                SMSSDK.getVerificationCode("86", mPhoneNum);
                //点击后禁用按钮，倒计时1分钟，如果还没收到短信，让用户可以重新点击
                mTvUserCode.setEnabled(false);
                new Thread(new Countdown()) {
                }.start();
                break;
            case R.id.login:
                //点击提交验证码---》登入
                String code = mEtUserCode.getText().toString().trim();
                SMSSDK.submitVerificationCode("86", mPhoneNum, code);
                break;
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CUT_DOWN:
                    mTvUserCode.setText("剩余时间(" + mTime + ")");
                    break;
                case RESEND:
                    mTvUserCode.setText("获取验证码");
                    mTvUserCode.setEnabled(true);
                    break;
            }
        }
    };

    private class Countdown implements Runnable {
        @Override
        public void run() {
            mTime = 60;
            for (; mTime > 0; mTime--) {
                //发送消息更新UI
                mHandler.sendEmptyMessage(CUT_DOWN);
                SystemClock.sleep(999);
            }
            mHandler.sendEmptyMessage(RESEND);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
        //注销短信验证码监听
        SMSSDK.unregisterEventHandler(mEventHandler);

    }
}
