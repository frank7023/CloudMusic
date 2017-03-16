package com.wyh.cloudmusic.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.item.MusicListItem;
import com.wyh.cloudmusic.service.PlayerService;
import com.wyh.cloudmusic.utils.SearchMusicUtil;

import java.util.List;

/**
 * Created by wyh on 2016/11/26.
 * 音乐播放界面
 */
public class MediaPlayActivity extends Activity {

    private SeekBar seekBar;//播放进度条
    private Button previousBtn; // 上一首
    private Button playModeBtn; // 单曲循环、全部循环、随机播放
    private Button playBtn; // 播放（播放、暂停）
    private Button nextBtn; // 下一首
    private Button backBtn;//返回
    private TextView musicTitle;//歌曲标题
    private TextView musicArtist;//歌手姓名
    private TextView musicDuration; //歌曲时长
    private TextView musicCurrentTime;//歌曲现阶段时间
    private ImageView mediaPlayBackground;//播放界面背景
    private int playModeState;        //循环标识
    private final int isCurrentRepeat = 9; // 单曲循环
    private final int isAllRepeat = 10; // 全部循环
    private final int isShuffle = 11; // 随机播放
    private boolean isFirstTime = true;
    private boolean isPlaying; // 正在播放
    private boolean isPause; // 暂停
    private PlayerReceiver playerReceiver;  //自定义的广播接收器
    private List<MusicListItem> mp3Infos;//歌曲信息集合

    //一系列动作
    public static final String UPDATE_ACTION = "com.wwj.action.UPDATE_ACTION";  //更新动作
    public static final String CTL_ACTION = "com.wwj.action.CTL_ACTION";        //控制动作
    public static final String MUSIC_CURRENT = "com.wwj.action.MUSIC_CURRENT";  //音乐当前时间改变动作
    public static final String MUSIC_DURATION = "com.wwj.action.MUSIC_DURATION";//音乐播放长度改变动作
    public static final String MUSIC_PLAYING = "com.wwj.action.MUSIC_PLAYING";  //音乐正在播放动作
    public static final String REPEAT_ACTION = "com.wwj.action.REPEAT_ACTION";  //音乐重复播放动作
    public static final String SHUFFLE_ACTION = "com.wwj.action.SHUFFLE_ACTION";//音乐随机播放动作

    public static final int PLAY_MSG = 1;        //播放
    public static final int PAUSE_MSG = 2;        //暂停
    public static final int STOP_MSG = 3;        //停止
    public static final int CONTINUE_MSG = 4;    //继续
    public static final int PREVIOUS_MSG = 5;    //上一首
    public static final int NEXT_MSG = 6;        //下一首
    public static final int PROGRESS_CHANGE = 7;//进度改变
    public static final int PLAYING_MSG = 8;    //正在播放

    private String title;       //歌曲标题
    private String artist;      //歌曲艺术家
    private String url;         //歌曲路径
    private int listPosition;   //播放歌曲在mp3Infos的位置
    private int currentTime;    //当前歌曲播放时间
    private String duration;       //歌曲长度
    private int flag;           //播放标识

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_media_play_view);
        try {
            mp3Infos = SearchMusicUtil.searchMusicONPhone(getApplicationContext());  //获取歌曲对象集合
        } catch (Exception e) {
            e.printStackTrace();
        }
        //注册广播
        registerReceiver();
        //找到界面上的每一个控件
        findViewById();
        //为一些控件设置监听器
        setViewOnclickListener();
        //从Bundle中获取来自LocalMusicActivity中传过来的数据并初始化界面
        getDataFromBundle();
        //初始状态为列表循环播放状态
        playModeState = isAllRepeat;
    }

    private void registerReceiver() {
        //定义和注册广播接收器
        playerReceiver = new PlayerReceiver();
        IntentFilter filter = new IntentFilter();
        // 指定BroadcastReceiver监听的Action
        filter.addAction(UPDATE_ACTION);//更新动作
        filter.addAction(MUSIC_CURRENT);//音乐当前时间改变动作
        filter.addAction(MUSIC_DURATION);//音乐播放长度改变动作
        // 注册BroadcastReceiver
        registerReceiver(playerReceiver, filter);
    }

    /**
     * 从界面上根据id获取按钮
     */
    private void findViewById() {
        backBtn = (Button) findViewById(R.id.media_play_back);
        playModeBtn = (Button) findViewById(R.id.btn_play_mode);
        previousBtn = (Button) findViewById(R.id.btn_previous);
        playBtn = (Button) findViewById(R.id.btn_play_pause);
        nextBtn = (Button) findViewById(R.id.btn_next);
        musicTitle = (TextView) findViewById(R.id.media_play_title);
        musicArtist = (TextView) findViewById(R.id.media_play_singer);
        musicDuration = (TextView) findViewById(R.id.media_play_duration);
        musicCurrentTime = (TextView) findViewById(R.id.media_play_current_time);
        seekBar = (SeekBar) findViewById(R.id.media_play_seekbar);
        mediaPlayBackground = (ImageView) findViewById(R.id.media_play_background_image);
    }

    /**
     * 从Bundle中获取来自LocalMusicActivity中传过来的数据并初始化界面
     */
    private void getDataFromBundle() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        title = bundle.getString("title");
        artist = bundle.getString("artist");
        url = bundle.getString("url");
        listPosition = bundle.getInt("listPosition");
        duration = bundle.getString("duration");
        //初始化播放界面
        if (title != null && artist != null && url != null) {
            initView();
        } else {
            System.out.println("初始化界面失败");
        }
    }

    /**
     * 初始化播放器界面
     */
    public void initView() {
        musicTitle.setText(title);
        musicArtist.setText(artist);
        musicDuration.setText(String.valueOf(duration));
        //根据playModeState来设置模式按钮的图片
        switch (playModeState) {
            case isCurrentRepeat:
                playModeBtn.setBackgroundResource(R.drawable.media_current_repeat);
                break;
            case isAllRepeat:
                playModeBtn.setBackgroundResource(R.drawable.media_all_repeat);
                break;
            case isShuffle:
                playModeBtn.setBackgroundResource(R.drawable.media_no_repeat);
                break;
        }
        //设置播放按钮初始状态为暂停状态
        playBtn.setBackgroundResource(R.drawable.media_pause);
        play();//播放音乐
        isPlaying = true;
        isPause = false;

    }

    @Override
    protected void onPause() {
        super.onPause();
//        System.out.println("MediaPlayActivity has onPause");
    }

    /**
     * 初始化播放界面和接收从LocalMusicActivity中传来的数据
     */
    @Override
    protected void onResume() {
        super.onResume();
        //registerReceiver();//注册广播
//        System.out.println("MediaPlayActivity has onResume");
    }

    /**
     * 反注册广播
     */
    @Override
    protected void onStop() {
        super.onStop();
        //unregisterReceiver(playerReceiver);
//        System.out.println("MediaPlayActivity has onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(playerReceiver);
//        System.out.println("MediaPlayActivity has onDestroy");
    }

    /**
     * 给每个按钮设置监听器
     */
    private void setViewOnclickListener() {
        MediaPlayOnclickListener mediaPlayOnclickListener = new MediaPlayOnclickListener();
        backBtn.setOnClickListener(mediaPlayOnclickListener);
        playModeBtn.setOnClickListener(mediaPlayOnclickListener);
        previousBtn.setOnClickListener(mediaPlayOnclickListener);
        playBtn.setOnClickListener(mediaPlayOnclickListener);
        nextBtn.setOnClickListener(mediaPlayOnclickListener);
        seekBar.setOnSeekBarChangeListener(new SeekBarChangeListener());
    }

    /**
     * 控件点击事件
     */
    private class MediaPlayOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_play_pause: //播放暂停
                    if (isPlaying) {
                        Intent intent = new Intent(MediaPlayActivity.this, PlayerService.class);
                        //当前状态为正在播放，把按钮设置为播放
                        playBtn.setBackgroundResource(R.drawable.media_play);
                        //intent.setAction("com.wyh.media.MUSIC_SERVICE");
                        intent.putExtra("url", url);
                        intent.putExtra("listPosition", listPosition);
                        intent.putExtra("MSG", PAUSE_MSG);
                        //启动action为：com.wyh.media.MUSIC_SERVICE的服务
                        startService(intent);
                        isPlaying = false;
                        isPause = true;
                    } else if (isPause) {
                        Intent intent = new Intent(MediaPlayActivity.this, PlayerService.class);
                        //当前状态为暂停，把按钮设置为播放
                        playBtn.setBackgroundResource(R.drawable.media_pause);
                        //intent.setAction("com.wyh.media.MUSIC_SERVICE");
                        intent.putExtra("url", url);
                        intent.putExtra("listPosition", listPosition);
                        intent.putExtra("MSG", CONTINUE_MSG);
                        //启动action为：com.wyh.media.MUSIC_SERVICE的服务
                        startService(intent);
                        isPlaying = true;
                        isPause = false;
                    }
                    break;
                case R.id.btn_previous: //上一首歌曲
                    previous_music();
                    break;
                case R.id.btn_next: //下一首歌曲
                    next_music();
                    break;
                case R.id.btn_play_mode://播放模式切换，单曲循环->列表循环->随机播放->单曲循环
                    Intent intent = new Intent(REPEAT_ACTION);//设置循环动作，用于发送广播给service
                    if (playModeState == isCurrentRepeat) {//如果当前模式为单曲循环，那么就转为列表循环并发送广播
                        repeat_all();
                        playModeState = isAllRepeat;
                        playModeBtn.setBackgroundResource(R.drawable.media_all_repeat);
                        Toast.makeText(MediaPlayActivity.this, "列表循环", Toast.LENGTH_SHORT).show();
                        intent.putExtra("playModeState", isAllRepeat);
                        sendBroadcast(intent);
                    } else if (playModeState == isAllRepeat) {//如果当前模式为列表循环，那么就转为随机播放并发送广播
                        shuffleMusic();
                        playModeState = isShuffle;
                        playModeBtn.setBackgroundResource(R.drawable.media_no_repeat);
                        Toast.makeText(MediaPlayActivity.this, "随机播放", Toast.LENGTH_SHORT).show();
                        intent.putExtra("playModeState", isShuffle);
                        sendBroadcast(intent);
                    } else if (playModeState == isShuffle) {//如果当前模式为随机播放，那么就转为单曲循环并发送广播
                        repeat_one();
                        playModeState = isCurrentRepeat;
                        playModeBtn.setBackgroundResource(R.drawable.media_current_repeat);
                        Toast.makeText(MediaPlayActivity.this, "单曲循环", Toast.LENGTH_SHORT).show();
                        intent.putExtra("playModeState", isCurrentRepeat);
                        sendBroadcast(intent);
                    }
                    break;
            }
        }
    }

    /**
     * 播放音乐
     */
    public void play() {
        //开始播放的时候为列表循环播放
        repeat_all();
        Intent intent = new Intent(MediaPlayActivity.this, PlayerService.class);
        //intent.setAction("com.wyh.media.MUSIC_SERVICE");
        intent.putExtra("url", url);
        intent.putExtra("listPosition", listPosition);
        intent.putExtra("MSG", PLAY_MSG);
        startService(intent);

    }

    /**
     * 上一首
     */
    public void previous_music() {
        playBtn.setBackgroundResource(R.drawable.media_pause);
        listPosition = listPosition - 1;
        if (listPosition >= 0) {
            MusicListItem mp3Info = mp3Infos.get(listPosition);   //上一首MP3
            musicTitle.setText(mp3Info.getTitle());
            musicArtist.setText(mp3Info.getArtist());
            url = mp3Info.getUrl();
            //设置PlayerService
            Intent intent = new Intent(MediaPlayActivity.this, PlayerService.class);
            //intent.setAction("com.wyh.media.MUSIC_SERVICE");
            intent.putExtra("url", mp3Info.getUrl());
            intent.putExtra("listPosition", listPosition);
            intent.putExtra("MSG", PREVIOUS_MSG);
            //启动PlayerService
            startService(intent);
            isPlaying = true;
            isPause = false;
        } else {
            Toast.makeText(MediaPlayActivity.this, "没有上一首了", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 下一首
     */
    public void next_music() {
        playBtn.setBackgroundResource(R.drawable.media_pause);
        listPosition = listPosition + 1;
        if (listPosition <= mp3Infos.size() - 1) {
            MusicListItem mp3Info = mp3Infos.get(listPosition);
            url = mp3Info.getUrl();
            musicTitle.setText(mp3Info.getTitle());
            musicArtist.setText(mp3Info.getArtist());
            //设置PlayerService
            Intent intent = new Intent(MediaPlayActivity.this, PlayerService.class);
            //intent.setAction("com.wyh.media.MUSIC_SERVICE");
            intent.putExtra("url", mp3Info.getUrl());
            intent.putExtra("listPosition", listPosition);
            intent.putExtra("MSG", NEXT_MSG);
            //启动PlayerService
            startService(intent);
            isPlaying = true;
            isPause = false;
        } else {
            Toast.makeText(MediaPlayActivity.this, "没有下一首了", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 随机播放
     */
    public void shuffleMusic() {
        Intent intent = new Intent(CTL_ACTION);
        intent.putExtra("control", 3);
        sendBroadcast(intent);
    }

    /**
     * 单曲循环
     */
    public void repeat_one() {
        Intent intent = new Intent(CTL_ACTION);
        intent.putExtra("control", 1);
        sendBroadcast(intent);
    }

    /**
     * 全部循环
     */
    public void repeat_all() {
        Intent intent = new Intent(CTL_ACTION);
        intent.putExtra("control", 2);
        sendBroadcast(intent);
    }

    /**
     * 顺序播放
     */
    public void repeat_none() {
        Intent intent = new Intent(CTL_ACTION);
        intent.putExtra("control", 3);
        sendBroadcast(intent);
    }

    /**
     * 播放进度改变
     *
     * @param progress
     */
    public void audioTrackChange(int progress) {
        Intent intent = new Intent(MediaPlayActivity.this, PlayerService.class);
        //intent.setAction("com.wyh.media.MUSIC_SERVICE");
        intent.putExtra("url", url);
        intent.putExtra("listPosition", listPosition);
        if (isPause) {
            intent.putExtra("MSG", PAUSE_MSG);
        } else {
            intent.putExtra("MSG", PROGRESS_CHANGE);
        }
        intent.putExtra("progress", progress);
        startService(intent);
    }

    /**
     * 实现监听seekbar的类
     */
    private class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (seekBar.getId()) {
                case R.id.media_play_seekbar:
                    if (fromUser) {
                        audioTrackChange(progress);
                    }
                    break;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }


    /**
     * 用来接收从PlayService传回来的广播的内部类
     */
    public class PlayerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(MUSIC_CURRENT)) {
                //如果action为com.wwj.action.MUSIC_CURRENT，获取歌曲当前播放时间并在播放界面中更新
                currentTime = intent.getIntExtra("currentTime", -1);
                musicCurrentTime.setText(SearchMusicUtil.formatTime(currentTime));
                //更新播放进度条
                seekBar.setProgress(currentTime);
            } else if (action.equals(MUSIC_DURATION)) {
                //如果action为com.wwj.action.MUSIC_DURATION，获取歌曲的时长并在播放界面中更新
                int duration = intent.getIntExtra("duration", -1);
                //设置播放进度条最大值
                seekBar.setMax(duration);
                musicDuration.setText(SearchMusicUtil.formatTime(duration));
            } else if (action.equals(UPDATE_ACTION)) {
                // 获取Intent中的current消息，current代表当前正在播放的歌曲
                listPosition = intent.getIntExtra("current", -1);
                url = mp3Infos.get(listPosition).getUrl();
                if (listPosition >= 0) {
                    musicTitle.setText(mp3Infos.get(listPosition).getTitle());
                    musicArtist.setText(mp3Infos.get(listPosition).getArtist());
                    play();
                }
                if (listPosition == 0) {
                    musicDuration.setText(mp3Infos.get(listPosition).getTimes());
                    playBtn.setBackgroundResource(R.drawable.media_pause);
                    isPause = true;
                }
            }
        }
    }
}
