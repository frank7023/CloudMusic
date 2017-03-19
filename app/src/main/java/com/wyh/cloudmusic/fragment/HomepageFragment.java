package com.wyh.cloudmusic.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.adapter.HomeFragmentPagerAdapter;
import com.wyh.cloudmusic.base.BaseFragment;
import com.wyh.cloudmusic.innerfragment.PlayListFragment;
import com.wyh.cloudmusic.innerfragment.RadioStationFragment;
import com.wyh.cloudmusic.innerfragment.RankListFragment;
import com.wyh.cloudmusic.innerfragment.RecommendFragment;

import java.util.ArrayList;


/**
 * Created by haoge728 on 2016/9/16.
 * 主页推荐界面
 */
public class HomepageFragment extends BaseFragment {

    private static ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView textView;
    private Fragment recommendFragemnt, playlistFragment, radiostationFragment, ranklistFragment;
    private ArrayList<String> tab_title_list = new ArrayList<String>();//页卡标题集合
    private ArrayList<Fragment> fragment_list = new ArrayList<>();//ViewPager要填充的fragment列表
    private HomeFragmentPagerAdapter adapter;//viewpager适配器

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.homepager_fragment, null);
        viewPager = (ViewPager) view.findViewById(R.id.my_home_viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.my_home_tablayout);
        textView = (TextView) view.findViewById(R.id.textview);
        return view;
    }

    @Override
    public void initData() {
        tab_title_list.add("个性推荐");
        tab_title_list.add("歌单");
        tab_title_list.add("主播电台");
        tab_title_list.add("排行榜");
        tabLayout.addTab(tabLayout.newTab().setText(tab_title_list.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(tab_title_list.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(tab_title_list.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(tab_title_list.get(3)));
        if (recommendFragemnt == null && playlistFragment == null && radiostationFragment == null && ranklistFragment == null) {
            recommendFragemnt = new RecommendFragment();
            playlistFragment = new PlayListFragment();
            radiostationFragment = new RadioStationFragment();
            ranklistFragment = new RankListFragment();
            fragment_list.add(recommendFragemnt);
            fragment_list.add(playlistFragment);
            fragment_list.add(radiostationFragment);
            fragment_list.add(ranklistFragment);
        }
        adapter = new HomeFragmentPagerAdapter(getChildFragmentManager(), tab_title_list, fragment_list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来
        tabLayout.setTabsFromPagerAdapter(adapter);//给Tabs设置适配器
    }

    public void setCurrentPager(int position) {
        switch (position) {
            case 0:
                viewPager.setCurrentItem(0);
                break;
            case 1:
                viewPager.setCurrentItem(1);
                break;
            case 2:
                viewPager.setCurrentItem(2);
                break;
            case 3:
                viewPager.setCurrentItem(3);
                break;
        }
    }
}
