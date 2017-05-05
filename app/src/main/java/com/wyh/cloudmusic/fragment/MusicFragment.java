package com.wyh.cloudmusic.fragment;

import android.view.View;
import android.widget.ListView;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.adapter.MusicMenuListAdapter;
import com.wyh.cloudmusic.base.BaseFragment;
import com.wyh.cloudmusic.implement.MusicItemClickListener;
import com.wyh.cloudmusic.init.InitMusicMenuListData;
import com.wyh.cloudmusic.item.MusicMenuListItem;

import java.util.ArrayList;

/**
 * Created by haoge728 on 2016/9/16.
 * 我的音乐界面
 */
public class MusicFragment extends BaseFragment {

    private ListView listView;//音乐界面的五个菜单item
    private MusicMenuListAdapter adapter;//音乐界面ListView的适配器
    private ArrayList<MusicMenuListItem> mMenuItems = new ArrayList<MusicMenuListItem>();//音乐界面ListView的item集合

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.music_fragment, null);
        listView = (ListView) view.findViewById(R.id.music_listview);
        return view;
    }

    @Override
    public void initData() {
        System.out.println("MusicFragment创建成功");
        InitMusicMenuListData.initMusicMenuListItem(mMenuItems);
        adapter = new MusicMenuListAdapter(mActivity, mMenuItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new MusicItemClickListener());//设置item的点击监听
    }

}
