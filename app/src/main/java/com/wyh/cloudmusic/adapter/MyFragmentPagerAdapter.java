package com.wyh.cloudmusic.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.wyh.cloudmusic.fragment.HomepageFragment;
import com.wyh.cloudmusic.fragment.MomentsFragment;
import com.wyh.cloudmusic.fragment.MusicFragment;

import java.util.ArrayList;

/**
 * Created by haoge728 on 2016/9/16.
 * ToolBar上三大页面ViewPager的适配器
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();//用于存放主界面的三个fragment
    private Fragment homepagerFragment, momentsFragment, musicFragment;
    private FragmentTransaction transaction;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        homepagerFragment = new HomepageFragment();
        musicFragment = new MusicFragment();
        momentsFragment = new MomentsFragment();
        fragmentArrayList.add(homepagerFragment);
        fragmentArrayList.add(musicFragment);
        fragmentArrayList.add(momentsFragment);
//        transaction = fm.beginTransaction();
//        transaction.add(homepagerFragment, "homepager");
//        transaction.add(musicFragment, "music");
//        transaction.add(momentsFragment, "moments");
//        transaction.commit();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
}
