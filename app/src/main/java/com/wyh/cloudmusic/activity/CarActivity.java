package com.wyh.cloudmusic.activity;

import android.os.Bundle;
import android.view.View;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.base.BaseActivity;

/**
 * Created by haoge728 on 2016/9/15.
 * 驾驶模式
 */
public class CarActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseSetContentView(R.layout.car_activity);
        initToolbar("驾驶模式");//调用基类的初始化toolbar方法
    }

    public void car() {

    }
    public void car1() {

    }
    @Override
    public View initView() {
        return null;
    }
}
