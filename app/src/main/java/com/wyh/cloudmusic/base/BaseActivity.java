package com.wyh.cloudmusic.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.wyh.cloudmusic.R;

/**
 * Created by haoge728 on 2016/9/15.
 * 侧滑栏里ListView所有item的activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        setTranslucentStatus();//沉浸栏，如果加上了，手機底部如果有功能鍵，頂部就會被拉長
    }

    /**
     * 子类通过调用这个方法，实现在父布局上添加自己的自定义布局
     *
     * @param layoutResID
     */
    public void baseSetContentView(int layoutResID) {
        LinearLayout llContent = (LinearLayout)
                findViewById(R.id.content); //content是在基类布局文件中预定义的layout区域
        LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(layoutResID, null);
        llContent.addView(view);
    }

    /**
     * 初始化toolbar，隐藏上面的按钮以及后退键的事件处理
     */
    public void initToolbar(String title) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(title);
        mToolbar.setTitleTextColor(Color.WHITE);
        System.out.println("标题为：" + mToolbar.getTitle());
        mToolbar.setNavigationIcon(R.drawable.toolbar_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /**
     * 初始化Fragment的布局
     *
     * @return
     */
    public abstract View initView();

    /**
     * 初始化数据, 子类覆盖此方法, 来实现自己的数据初始化操作
     */
    public void initData() {

    }

    /**
     * 设置状态栏背景状态
     */
    private void setTranslucentStatus() {
        //判断当前SDK版本号，如果是4.4以上，就是支持沉浸式状态栏的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }

    }

}