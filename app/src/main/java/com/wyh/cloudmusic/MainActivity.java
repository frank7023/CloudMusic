package com.wyh.cloudmusic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyh.cloudmusic.activity.MediaPlayActivity;
import com.wyh.cloudmusic.activity.SearchActivity;
import com.wyh.cloudmusic.activity.SettingActivity;
import com.wyh.cloudmusic.adapter.DrawerListAdapter;
import com.wyh.cloudmusic.adapter.MyFragmentPagerAdapter;
import com.wyh.cloudmusic.implement.LeftItemClickListener;
import com.wyh.cloudmusic.init.InitDrawerListData;
import com.wyh.cloudmusic.item.DrawerListItem;
import com.wyh.cloudmusic.item.MusicListItem;
import com.wyh.cloudmusic.service.PlayerService;
import com.wyh.cloudmusic.utils.SearchMusicUtil;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private ListView mLeftLsitView;
    private ImageView titleBtn1, titleBtn2, titleBtn3, titleBtn4;
    private TextView settingBtn, quitBtn;
    private ArrayList<DrawerListItem> mDrawerListItems = new ArrayList<DrawerListItem>();
    private Intent intent = new Intent();
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private ImageView bottomSingerImage;//底部播放栏照片
    private TextView musicTitle, musicArtist;//底部播放栏歌曲名和歌手名
    private ImageView moreBtn, playBtn, nextBtn;//底部播放栏三个按钮
    private List<MusicListItem> musicList = new ArrayList<MusicListItem>();//存放音乐信息的集合
    private boolean isPlaying, isPause;//播放和暂停标识
    private String title;       //歌曲标题
    private String artist;      //歌曲艺术家
    private String url;         //歌曲路径
    private int songID;         //歌曲ID
    private int albumID;        //专辑ID
    private int listPosition;   //播放歌曲在mp3Infos的位置
    private boolean isFirstTime = true;//标识是否首次播放
    private BottomPlayerReceiver bottomPlayerReceiver;//广播类
    //一系列动作
    public static final String UPDATE_ACTION = "com.wwj.action.UPDATE_ACTION";  //更新动作

    public static final String CTL_ACTION = "com.wwj.action.CTL_ACTION";        //控制动作
    public static final int PLAY_MSG = 1;        //播放
    public static final int PAUSE_MSG = 2;        //暂停
    public static final int STOP_MSG = 3;        //停止
    public static final int CONTINUE_MSG = 4;    //继续
    public static final int PREVIOUS_MSG = 5;    //上一首
    public static final int NEXT_MSG = 6;        //下一首

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTranslucentStatus();//沉浸栏，如果加上了，手機底部如果有功能鍵，頂部就會被拉長
        findViews();//初始化控件
        initToolbar();//初始化toolbar
        InitDrawerListData.initDrawerListItem(mDrawerListItems);//初始化侧滑栏item数据
        initListView();//初始化侧滑栏里的ListView
        initFragmentPager();//初始化ViewPager和Fragment
        initPlayingState();//初始化播放状态和歌曲信息
        registerReceiver();//注册广播

    }

    public void findViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.my_drawer_layout);
        mViewPager = (ViewPager) findViewById(R.id.my_viewpager);
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        mLeftLsitView = (ListView) findViewById(R.id.my_left_listview);
        titleBtn1 = (ImageView) findViewById(R.id.toolbar_btn1);
        titleBtn2 = (ImageView) findViewById(R.id.toolbar_btn2);
        titleBtn3 = (ImageView) findViewById(R.id.toolbar_btn3);
        titleBtn4 = (ImageView) findViewById(R.id.toolbar_btn4);
        settingBtn = (TextView) findViewById(R.id.user_setting);
        quitBtn = (TextView) findViewById(R.id.user_quit);
        //底部播放器组件初始化
        bottomSingerImage = (ImageView) findViewById(R.id.bottom_singer_image);
        musicTitle = (TextView) findViewById(R.id.bottom_song_name);
        musicArtist = (TextView) findViewById(R.id.bottom_singer_name);
        playBtn = (ImageView) findViewById(R.id.bottom_music_play);
        nextBtn = (ImageView) findViewById(R.id.bottom_music_next);
        bottomSingerImage.setOnClickListener(this);
        settingBtn.setOnClickListener(this);//给设置按钮添加事件
        quitBtn.setOnClickListener(this);//给退出按钮添加事件
        playBtn.setOnClickListener(this);//给底部播放栏的播放按钮添加事件
        nextBtn.setOnClickListener(this);//给底部播放栏的下一首按钮添加事件
    }

    /**
     * 初始化toolbar，设置菜单按钮
     */
    public void initToolbar() {
        //mToolbar.setTitle("");//设置Toolbar标题
        //mToolbar.setTitleTextColor(Color.parseColor("#FFFFFF")); //设置标题颜色
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        titleBtn1.setOnClickListener(this);
        titleBtn2.setOnClickListener(this);
        titleBtn3.setOnClickListener(this);
        titleBtn4.setOnClickListener(this);
        titleBtn2.setBackgroundResource(R.drawable.toolbar_music_selected);
    }


    /**
     * 设置ListView
     */
    public void initListView() {
        DrawerListAdapter mDrawerListAdapter = new DrawerListAdapter(this, mDrawerListItems);
        mLeftLsitView.setAdapter(mDrawerListAdapter);
        mLeftLsitView.setOnItemClickListener(new LeftItemClickListener());//设置item的点击监听
    }

    /**
     * 设置状态栏背景状态
     */
    private void setTranslucentStatus() {
        //判断当前SDK版本号，如果是4.4以上，就是支持沉浸式状态栏的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }

    }

    /**
     * 初始化播放状态和歌曲信息的方法
     */
    private void initPlayingState() {
        musicList = SearchMusicUtil.searchMusicONPhone(MainActivity.this);//获取手机上所有歌曲的信息
        listPosition = 0;//从第一首歌开始
        url = musicList.get(0).getUrl();//歌曲播放路径
        artist = musicList.get(0).getArtist();//歌手名称
        title = musicList.get(0).getTitle();//歌曲名称
        songID = musicList.get(0).getId();//歌曲ID
        albumID = musicList.get(0).getAlbumID();//专辑ID
        bottomSingerImage.setImageBitmap(SearchMusicUtil.getArtwork(this, songID, albumID, true, true));
        musicArtist.setText(artist);//设置歌手名称
        musicTitle.setText(title);//设置歌曲名称
//        repeat_all();//设置播放模式为循环播放
        isPlaying = false;
        isPause = true;
    }

    private void registerReceiver() {
        //定义和注册广播接收器
        bottomPlayerReceiver = new BottomPlayerReceiver();
        IntentFilter filter = new IntentFilter();
        // 指定BroadcastReceiver监听的Action
        filter.addAction(UPDATE_ACTION);//更新动作
        // 注册BroadcastReceiver
        registerReceiver(bottomPlayerReceiver, filter);
    }

    /**
     * 按钮和退出按钮被点击时触发此方法
     * toolbar上按钮被点击时触发此方法
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_setting:
                intent.setClass(this, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.user_quit:
                finish();
                break;
            case R.id.toolbar_btn1:
                updateBtnState(0);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.toolbar_btn2:
                updateBtnState(1);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.toolbar_btn3:
                updateBtnState(2);
                mViewPager.setCurrentItem(2);
                break;
            case R.id.toolbar_btn4:
                intent.setClass(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.bottom_singer_image:
                intent.setClass(MainActivity.this, MediaPlayActivity.class);
                startActivity(intent);
                break;
            case R.id.bottom_music_play:
                if (isPlaying) {
                    Toast.makeText(MainActivity.this, "暂停", Toast.LENGTH_SHORT).show();
                    playBtn.setImageResource(R.drawable.bottom_playbar_btn_play);
                    Intent intent = new Intent(MainActivity.this, PlayerService.class);
                    intent.putExtra("url", url);
                    intent.putExtra("listPosition", listPosition);
                    intent.putExtra("MSG", PAUSE_MSG);
                    startService(intent);
                    isPlaying = false;
                    isPause = true;
                } else if (isPause) {
                    Toast.makeText(MainActivity.this, "播放", Toast.LENGTH_SHORT).show();
                    playBtn.setImageResource(R.drawable.bottom_playbar_btn_pause);
                    Intent intent = new Intent(MainActivity.this, PlayerService.class);
                    intent.putExtra("url", url);
                    intent.putExtra("listPosition", listPosition);
                    //是否首次播放，判断是否继续播放
                    if (isFirstTime) {
                        intent.putExtra("MSG", PLAY_MSG);
                        isFirstTime = false;
                    } else {
                        intent.putExtra("MSG", CONTINUE_MSG);
                    }
                    startService(intent);
                    isPlaying = true;
                    isPause = false;
                }
                break;
            case R.id.bottom_music_next:
                next_music();//播放下一首歌曲
                break;
        }
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
     * 下一首
     */
    public void next_music() {
        playBtn.setImageResource(R.drawable.bottom_playbar_btn_pause);
        listPosition = listPosition + 1;
        if (listPosition <= musicList.size() - 1) {
            MusicListItem mp3Info = musicList.get(listPosition);
            url = mp3Info.getUrl();
            //设置PlayerService
            Intent intent = new Intent(MainActivity.this, PlayerService.class);
            //intent.setAction("com.wyh.media.MUSIC_SERVICE");
            intent.putExtra("url", mp3Info.getUrl());
            intent.putExtra("listPosition", listPosition);
            intent.putExtra("MSG", NEXT_MSG);
            //启动PlayerService
            startService(intent);
            isPlaying = true;
            isPause = false;
        } else {
            Toast.makeText(MainActivity.this, "没有下一首了", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 初始化ViewPager和Fragment
     */
    public void initFragmentPager() {
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(myFragmentPagerAdapter);
        mViewPager.setCurrentItem(1);//设置当前页面为第二页
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateBtnState(position);//更新ToolBar上三个按钮的状态
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 更新ToolBar上三个按钮的状态
     *
     * @param position
     */
    public void updateBtnState(int position) {
        switch (position) {
            case 0:
                titleBtn1.setBackgroundResource(R.drawable.toolbar_discover_selected);
                titleBtn2.setBackgroundResource(R.drawable.toolbar_music_normal);
                titleBtn3.setBackgroundResource(R.drawable.toolbar_friends_normal);
                break;
            case 1:
                titleBtn2.setBackgroundResource(R.drawable.toolbar_music_selected);
                titleBtn1.setBackgroundResource(R.drawable.toolbar_discover_normal);
                titleBtn3.setBackgroundResource(R.drawable.toolbar_friends_normal);
                break;
            case 2:
                titleBtn3.setBackgroundResource(R.drawable.toolbar_friends_selected);
                titleBtn1.setBackgroundResource(R.drawable.toolbar_discover_normal);
                titleBtn2.setBackgroundResource(R.drawable.toolbar_music_normal);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(bottomPlayerReceiver);
    }

    /**
     * 用来接收从PlayService传回来的广播的内部类
     */
    public class BottomPlayerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(UPDATE_ACTION)) {
                playBtn.setImageResource(R.drawable.bottom_playbar_btn_pause);
                // 获取Intent中的current消息，current代表当前正在播放的歌曲
                listPosition = intent.getIntExtra("current", -1);
                url = musicList.get(listPosition).getUrl();
                if (listPosition >= 0) {
                    musicTitle.setText(musicList.get(listPosition).getTitle());
                    musicArtist.setText(musicList.get(listPosition).getArtist());
                    //设置底部播放栏的专辑照片
                    bottomSingerImage.setImageBitmap(SearchMusicUtil.getArtwork(MainActivity.this, musicList.get(listPosition).getId(), musicList.get(listPosition).getAlbumID(), true, true));
                    isPlaying = true;
                    isPause = false;
//                    play();
                }
//                if (listPosition == 0) {
//                    musicDuration.setText(mp3Infos.get(listPosition).getTimes());
//                    playBtn.setBackgroundResource(R.drawable.media_pause);
//                    isPause = true;
//                }

            }
        }
    }
}
