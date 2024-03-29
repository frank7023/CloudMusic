package com.wyh.cloudmusic.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author andong
 *         所有的Fragment的基类
 *         上下文抽取
 *         初始化布局的方法抽取: 抽象
 *         初始化数据的方法抽取: 可选
 */
public abstract class BaseFragment extends Fragment {

    public Activity mActivity;//上下文对象

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = initView();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Activity已经初始化完毕了, 当前需要初始化Fragment的数据了
        initData();
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

}
