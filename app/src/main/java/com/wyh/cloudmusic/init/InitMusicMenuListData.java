package com.wyh.cloudmusic.init;

import com.wyh.cloudmusic.MainActivity;
import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.item.MusicMenuListItem;

import java.util.List;

/**
 * Created by haoge728 on 2016/10/2.
 */
public class InitMusicMenuListData {
    public static void initMusicMenuListItem(List<MusicMenuListItem> mItems) {
        MusicMenuListItem item1 = new MusicMenuListItem(0, R.drawable.music_menu_list_icon1, "本地音乐", "("+ MainActivity.mItems.size()+")");
        mItems.add(item1);
        MusicMenuListItem item2 = new MusicMenuListItem(0, R.drawable.music_menu_list_icon2, "最近播放", "(0)");
        mItems.add(item2);
        MusicMenuListItem item3 = new MusicMenuListItem(0, R.drawable.music_menu_list_icon3, "下载管理", "(0)");
        mItems.add(item3);
        MusicMenuListItem item4 = new MusicMenuListItem(0, R.drawable.music_menu_list_icon4, "我的歌手", "(0)");
        mItems.add(item4);
        MusicMenuListItem item5 = new MusicMenuListItem(0, R.drawable.music_menu_list_icon5, "我的电台", "(0)");
        mItems.add(item5);
        MusicMenuListItem item6 = new MusicMenuListItem(1, R.drawable.music_menu_arrow, "创建的歌单", "(0)", R.drawable.playlist_menu_button);
        mItems.add(item6);
        MusicMenuListItem item7 = new MusicMenuListItem(2, R.drawable.playlist_love, "我喜欢的音乐", "0首，已下载0首", R.drawable.playlist_menu_button);
        mItems.add(item7);
    }
}
