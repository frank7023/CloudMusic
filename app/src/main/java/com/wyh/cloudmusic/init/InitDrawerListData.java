package com.wyh.cloudmusic.init;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.item.DrawerListItem;

import java.util.ArrayList;

/**
 * Created by haoge728 on 2016/9/16.
 */
public class InitDrawerListData {

    public static void initDrawerListItem(ArrayList<DrawerListItem> mItems) {
        DrawerListItem item1 = new DrawerListItem(R.drawable.item1, "我的消息");
        mItems.add(item1);
        DrawerListItem item2 = new DrawerListItem(R.drawable.item2, "积分商城");
        mItems.add(item2);
        DrawerListItem item3 = new DrawerListItem(R.drawable.item3, "会员中心");
        mItems.add(item3);
        DrawerListItem item4 = new DrawerListItem(R.drawable.item4, "在线听歌免流量");
        mItems.add(item4);
        DrawerListItem item5 = new DrawerListItem(R.drawable.item5, "听歌识曲");
        mItems.add(item5);
        DrawerListItem item6 = new DrawerListItem(R.drawable.item6, "主题换肤");
        mItems.add(item6);
        DrawerListItem item7 = new DrawerListItem(R.drawable.item7, "夜间模式");
        mItems.add(item7);
        DrawerListItem item8 = new DrawerListItem(R.drawable.item8, "定时停止播放");
        mItems.add(item8);
        DrawerListItem item9 = new DrawerListItem(R.drawable.item9, "音乐闹钟");
        mItems.add(item9);
        DrawerListItem item10 = new DrawerListItem(R.drawable.item10, "驾驶模式");
        mItems.add(item10);
        DrawerListItem item11 = new DrawerListItem(R.drawable.item11, "我的音乐云盘");
        mItems.add(item11);
    }

}
