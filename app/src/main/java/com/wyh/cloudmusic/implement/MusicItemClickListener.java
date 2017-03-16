package com.wyh.cloudmusic.implement;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.wyh.cloudmusic.activity.DownloadActivity;
import com.wyh.cloudmusic.activity.LocalMusicActivity;
import com.wyh.cloudmusic.activity.MyRadioStationActivity;
import com.wyh.cloudmusic.activity.MySingerActivity;
import com.wyh.cloudmusic.activity.RecentlyPlayActivity;

/**
 * Created by haoge728 on 2016/10/9.
 * 音乐界面ListView中item点击事件处理
 */
public class MusicItemClickListener implements AdapterView.OnItemClickListener {

    private Intent intent = new Intent();

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                intent.setClass(view.getContext(), LocalMusicActivity.class);
                view.getContext().startActivity(intent);
                break;
            case 1:
                intent.setClass(view.getContext(), RecentlyPlayActivity.class);
                view.getContext().startActivity(intent);
                break;
            case 2:
                intent.setClass(view.getContext(), DownloadActivity.class);
                view.getContext().startActivity(intent);
                break;
            case 3:
                intent.setClass(view.getContext(), MySingerActivity.class);
                view.getContext().startActivity(intent);
                break;
            case 4:
                intent.setClass(view.getContext(), MyRadioStationActivity.class);
                view.getContext().startActivity(intent);
                break;
        }
    }
}
