package com.wyh.cloudmusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.wyh.cloudmusic.MainActivity;
import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.base.BaseActivity;

/**
 * Created by muyang on 2016/3/1.
 */
public class WelcomeActivity extends BaseActivity {
    private static final int DELAY_TIME = 3000;
//    public static List<MusicListItem> mItems = new ArrayList<MusicListItem>();//存储歌曲信息的集合

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, DELAY_TIME);

//        MediaScannerConnection.scanFile(this, new String[]{Environment
//                .getExternalStorageDirectory().getAbsolutePath()}, null, null);

//        //提前加载手机里的音乐资源，供本地音乐使用
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //抛出异常，避免因为没有歌曲而闪退
//                try {
//                    mItems = SearchMusicUtil.searchMusicONPhone(WelcomeActivity.this);//扫描SD卡内的歌曲信息
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

    }

    @Override
    public View initView() {
        return null;
    }
}
