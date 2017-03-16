package com.wyh.cloudmusic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyh.cloudmusic.R;

/**
 * Created by wyh on 2017/1/16.
 */
public class BottomMusicActivity extends Activity {

    ImageView singerImage;//底部播放栏照片
    TextView songName;//底部播放栏歌曲名
    TextView singerName;//底部播放栏歌手名
    ImageView musicListBtn, nextBtn;//底部播放栏歌曲列表，播放，下一首按钮
    Button playBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_music_layout);
        findViewById();
    }

    public void findViewById() {
//        singerImage = (ImageView) findViewById(R.id.bottom_singer_image);
//        songName = (TextView) findViewById(R.id.bottom_song_name);
//        singerName = (TextView) findViewById(R.id.bottom_singer_name);
//        musicListBtn = (ImageView) findViewById(R.id.bottom_music_more);
        playBtn = (Button) findViewById(R.id.bottom_music_play);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BottomMusicActivity.this, "播放", Toast.LENGTH_SHORT).show();
                System.out.println("================================");
            }
        });
    }
}
