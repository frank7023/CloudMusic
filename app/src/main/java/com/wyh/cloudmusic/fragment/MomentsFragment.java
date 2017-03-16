package com.wyh.cloudmusic.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.adapter.MomentsFragmentPagerAdapter;
import com.wyh.cloudmusic.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoge728 on 2016/9/16.
 * 朋友圈界面
 */
public class MomentsFragment extends BaseFragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView textView;
    private List<String> mTitleList = new ArrayList<String>();//页卡标题集合
    private MomentsFragmentPagerAdapter adapter;//viewpager适配器

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.moments_fragment, null);
        viewPager = (ViewPager) view.findViewById(R.id.my_moments_viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.my_moments_tablayout);
        textView = (TextView) view.findViewById(R.id.textview);
        return view;
    }

    @Override
    public void initData() {
        mTitleList.add("动态");
        mTitleList.add("附近");
        mTitleList.add("好友");
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(2)));
        adapter = new MomentsFragmentPagerAdapter(mActivity,getChildFragmentManager(),mTitleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来
        tabLayout.setTabsFromPagerAdapter(adapter);////给Tabs设置适配器
    }
}
