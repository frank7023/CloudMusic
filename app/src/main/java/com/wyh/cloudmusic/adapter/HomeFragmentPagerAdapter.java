package com.wyh.cloudmusic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by haoge728 on 2016/9/17.
 * 推荐界面VierPager和TabLayout的适配器
 */
public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragment_list;//ViewPager要填充的fragment列表
    private ArrayList<String> tab_title_list;//tab中的title文字列表

    public HomeFragmentPagerAdapter(FragmentManager fm, ArrayList<String> tab_title_list, ArrayList<Fragment> fragment_list) {
        super(fm);
        this.tab_title_list = tab_title_list;
        this.fragment_list = fragment_list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragment_list.get(position);
    }

    @Override
    public int getCount() {
        return fragment_list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab_title_list.get(position);
    }
}
