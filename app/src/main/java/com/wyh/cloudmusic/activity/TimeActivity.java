package com.wyh.cloudmusic.activity;

import android.os.Bundle;
import android.view.View;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.base.BaseActivity;

/**
 * Created by haoge728 on 2016/9/15.
 * 定时停止播放
 */
public class TimeActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseSetContentView(R.layout.time_activity);
        initToolbar("定时停止播放");//调用基类的初始化toolbar方法
    }

    @Override
    public View initView() {
        return null;
    }
}
