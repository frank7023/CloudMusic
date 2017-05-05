package com.wyh.cloudmusic.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.wyh.cloudmusic.item.MusicListItem;
import com.wyh.cloudmusic.utils.SearchMusicUtil;

import java.util.List;

/**
 * Created by wyh on 2016/11/26.
 */
public class PlayerService extends Service {
    private MediaPlayer mediaPlayer; // 媒体播放器对象
    private String path;            // 音乐文件路径
    private int msg;
    private boolean isPause;        // 暂停状态
    private int current = 0;        // 记录当前正在播放的音乐
    private List<MusicListItem> mp3Infos;   //存放Mp3Info对象的集合
    private int status = 3;         //播放状态，默认为顺序播放
    private MyReceiver myReceiver;  //自定义广播接收器
    private int currentTime;        //当前播放进度
    private int duration;           //播放长度

    //服务要发送的一些Action
    public static final String UPDATE_ACTION = "com.wwj.action.UPDATE_ACTION";  //更新动作
    public static final String CTL_ACTION = "com.wwj.action.CTL_ACTION";        //控制动作
    public static final String MUSIC_CURRENT = "com.wwj.action.MUSIC_CURRENT";  //当前音乐播放时间更新动作
    public static final String MUSIC_DURATION = "com.wwj.action.MUSIC_DURATION";//新音乐长度更新动作

    public static final int PLAY_MSG = 1;        //播放
    public static final int PAUSE_MSG = 2;        //暂停
    public static final int STOP_MSG = 3;        //停止
    public static final int CONTINUE_MSG = 4;    //继续
    public static final int PREVIOUS_MSG = 5;    //上一首
    public static final int NEXT_MSG = 6;        //下一首
    public static final int PROGRESS_CHANGE = 7;//进度改变
    public static final int PLAYING_MSG = 8;    //正在播放
    /**
     * handler用来接收消息，来发送广播更新播放时间
     */
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
                if (mediaPlayer != null) {
                    currentTime = mediaPlayer.getCurrentPosition(); // 获取当前音乐播放的位置(时间)
                    Intent intent = new Intent();
                    intent.setAction(MUSIC_CURRENT);
                    intent.putExtra("currentTime", currentTime);
                    //给PlayerActivity发送广播，通知PlayerActivity修改当前播放时间
                    sendBroadcast(intent);
                    //每隔1秒发送一次消息通知PlayerActivity修改当前播放时间
                    handler.sendEmptyMessageDelayed(1, 1000);
                }

            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        //创建媒体播放器对象
        mediaPlayer = new MediaPlayer();
        //获取数据库中的歌曲集合
        mp3Infos = SearchMusicUtil.searchMusicONPhone(PlayerService.this);
        //设置音乐播放完成时的监听器
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (status == 1) {  //单曲循环
                    mediaPlayer.start();//播放音乐
                } else if (status == 2) {    //列表循环
                    current++;
                    if (current > mp3Infos.size() - 1) {    //如果current大于歌曲数，就从第一首歌开始播放
                        current = 0;
                    }
                    Intent sendIntent = new Intent(UPDATE_ACTION);
                    sendIntent.putExtra("current", current);
                    //发送广播，将被MediaPlayActivity组件中的BroadcastReceiver接收到
                    sendBroadcast(sendIntent);
                    path = mp3Infos.get(current).getUrl();
                    play(0);//播放音乐
                } else if (status == 3) {   //随机播放
                    current = getRandomIndex(mp3Infos.size() - 1);
                    Intent sendIntent = new Intent(UPDATE_ACTION);
                    sendIntent.putExtra("current", current);
                    // 发送广播，将被MediaPlayActivity组件中的BroadcastReceiver接收到
                    sendBroadcast(sendIntent);
                    path = mp3Infos.get(current).getUrl();
                    play(0);//播放音乐
                }
            }
        });

        //创建自定义广播对象
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(CTL_ACTION);
        registerReceiver(myReceiver, filter);
    }

    /**
     * 获取随机位置
     *
     * @param end
     * @return
     */
    protected int getRandomIndex(int end) {
        int index = (int) (Math.random() * end);
        return index;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            current = intent.getIntExtra("listPosition", 0); //当前播放歌曲的在mp3Infos的位置
//            current = 1;
            msg = intent.getIntExtra("MSG", 0);   //播放信息
            path = intent.getStringExtra("url"); //歌曲路径
            if (msg == PLAY_MSG) {
                play(0);//直接播放音乐
            } else if (msg == PAUSE_MSG) {
                pause();//暂停
            } else if (msg == STOP_MSG) {
                stop();//停止
            } else if (msg == CONTINUE_MSG) {
                resume();//继续播放
            } else if (msg == PREVIOUS_MSG) {
                previous();//上一首
            } else if (msg == NEXT_MSG) {
                next();//下一首
            } else if (msg == PROGRESS_CHANGE) {
                currentTime = intent.getIntExtra("progress", -1);//获取当前播放进度时间
                play(currentTime);//从当前播放时间开始播放
            }
        }

//        else if (msg == PLAYING_MSG) {
//            handler.sendEmptyMessage(1);//正在播放，发送消息通知播放界面更新歌曲的播放进度时间
//        }
//        System.out.println("onStartCommand------------->");
        return super.onStartCommand(intent, START_FLAG_REDELIVERY, startId);
    }


    /**
     * 播放音乐
     *
     * @param currentTime
     */
    private void play(int currentTime) {
        try {
            mediaPlayer.reset();// 把各项参数恢复到初始状态
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare(); // 进行缓冲
//            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new PreparedListener(currentTime));// 注册一个监听器,当音乐准备好的时候开始播放
            //发送消息通知MediaPlayActivity更改当前播放时间
            handler.sendEmptyMessage(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停音乐
     */
    private void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPause = true;
        }
    }

    /**
     * 继续播放
     */
    private void resume() {
        if (isPause) {
            mediaPlayer.start();
            isPause = false;
        }
    }

    /**
     * 停止音乐
     */
    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            try {
                mediaPlayer.prepare(); // 在调用stop后如果需要再次通过start进行播放,需要之前调用prepare函数
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 上一首
     */
    private void previous() {
        Intent sendIntent = new Intent(UPDATE_ACTION);
        //保存当前播放的歌曲标识
        sendIntent.putExtra("current", current);
        //发送广播，将被MediaPlayActivity组件中的BroadcastReceiver接收到
        sendBroadcast(sendIntent);
        //播放音乐
        play(0);
    }

    /**
     * 下一首
     */
    private void next() {
        Intent sendIntent = new Intent(UPDATE_ACTION);
        //保存当前播放的歌曲标识
        sendIntent.putExtra("current", current);
        //发送广播，将被MediaPlayActivity组件中的BroadcastReceiver接收到
        sendBroadcast(sendIntent);
        //播放音乐
        play(0);
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    /**
     * 实现一个OnPrepareLister接口,当音乐准备好的时候开始播放
     */
    private final class PreparedListener implements MediaPlayer.OnPreparedListener {
        private int currentTime;

        public PreparedListener(int currentTime) {
            this.currentTime = currentTime;
        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            mediaPlayer.start(); // 开始播放
            if (currentTime > 0) { // 如果音乐不是从头播放
                //将歌曲播放进度定位到当前播放时间
                mediaPlayer.seekTo(currentTime);
            }
            Intent intent = new Intent();
            intent.setAction(MUSIC_DURATION);
            duration = mediaPlayer.getDuration();
            intent.putExtra("duration", duration);  //通过Intent来传递歌曲的总长度
            //发送广播给MediaPlayActivity里的自定义广播
            sendBroadcast(intent);
        }
    }


    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //注销广播
            unregisterReceiver(this);
            int control = intent.getIntExtra("control", -1);
            switch (control) {
                case 1:
                    status = 1; // 将播放状态置为1表示：单曲循环
                    break;
                case 2:
                    status = 2; // 将播放状态置为2表示：列表循环
                    break;
                case 3:
                    status = 3; // 将播放状态置为3表示：随机播放
                    break;
            }
        }
    }
}
