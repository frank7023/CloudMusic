package com.wyh.cloudmusic.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wyh.cloudmusic.innerfragment.DynamicsFragment;
import com.wyh.cloudmusic.innerfragment.FriendsFragment;
import com.wyh.cloudmusic.innerfragment.NearbyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoge728 on 2016/9/17.
 * 朋友圈界面VierPager和TabLayout的适配器
 */
public class MomentsFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<String> tab_title_list = new ArrayList<String>();
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private Fragment dynamicsFragment, nearbyFragment, friendsFragment;

    public MomentsFragmentPagerAdapter(Context context, FragmentManager fm, List<String> tab_title_list) {
        super(fm);
        this.context = context;
        this.tab_title_list = tab_title_list;
        dynamicsFragment = new DynamicsFragment();
        nearbyFragment = new NearbyFragment();
        friendsFragment = new FriendsFragment();
        mFragmentList.add(dynamicsFragment);
        mFragmentList.add(nearbyFragment);
        mFragmentList.add(friendsFragment);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab_title_list.get(position);
    }
}
