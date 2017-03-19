package com.wyh.cloudmusic.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.adapter.HomeFragmentPagerAdapter;
import com.wyh.cloudmusic.base.BaseActivity;
import com.wyh.cloudmusic.innerfragment.AlbumFragment;
import com.wyh.cloudmusic.innerfragment.FileFragment;
import com.wyh.cloudmusic.innerfragment.MusicsFragment;
import com.wyh.cloudmusic.innerfragment.SingerFragment;

import java.util.ArrayList;

/**
 * Created by haoge728 on 2016/9/15.
 * 本地音乐
 */
public class LocalMusicActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<String> tab_title_list = new ArrayList<>();//页卡标题集合
    private ArrayList<Fragment> fragment_list = new ArrayList<>();//ViewPager要填充的fragment列表
    private Fragment musicsFragment, singerFragment, albumFragment, fileFragment;
    private HomeFragmentPagerAdapter adapter;//viewpager适配器
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseSetContentView(R.layout.local_music_activity);
        System.out.println("LocalMusicActivity创建成功");
        initToolbar("本地音乐");//调用基类的初始化toolbar方法
        tabLayout = (TabLayout) findViewById(R.id.my_local_music_tablayout);
        viewPager = (ViewPager) findViewById(R.id.my_local_music_viewpager);
        initData();
    }

    @Override
    public View initView() {
        return null;
    }

    @Override
    public void initData() {
        tab_title_list.add("单曲");
        tab_title_list.add("歌手");
        tab_title_list.add("专辑");
        tab_title_list.add("文件夹");
        tabLayout.addTab(tabLayout.newTab().setText(tab_title_list.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(tab_title_list.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(tab_title_list.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(tab_title_list.get(3)));
        if (musicsFragment == null) {
            musicsFragment = new MusicsFragment(context);
            fragment_list.add(musicsFragment);
        }
        if (singerFragment == null) {
            singerFragment = new SingerFragment(context);
            fragment_list.add(singerFragment);
        }
        if (albumFragment == null) {
            albumFragment = new AlbumFragment(context);
            fragment_list.add(albumFragment);
        }
        if (fileFragment == null) {
            fileFragment = new FileFragment(context);
            fragment_list.add(fileFragment);
        }
        adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(), tab_title_list, fragment_list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来
        tabLayout.setTabsFromPagerAdapter(adapter);//给Tabs设置适配器

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_findmusic:
                //扫描歌曲
                Toast.makeText(getApplicationContext(), "扫描歌曲", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_sort:
                //选择排序方式
                Toast.makeText(getApplicationContext(), "选择排序方式", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_getpicture:
                //一键获取封面歌词
                Toast.makeText(getApplicationContext(), "一键获取封面歌词", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_update:
                //升级音质
                Toast.makeText(getApplicationContext(), "升级音质", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
