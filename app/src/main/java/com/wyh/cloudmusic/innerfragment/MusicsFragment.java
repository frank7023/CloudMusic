package com.wyh.cloudmusic.innerfragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wyh.cloudmusic.MainActivity;
import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.activity.MediaPlayActivity;
import com.wyh.cloudmusic.adapter.MusicListAdapter;
import com.wyh.cloudmusic.base.BaseFragment;
import com.wyh.cloudmusic.item.MusicListItem;

/**
 * Created by haoge728 on 2016/9/17.
 * 本地音乐下的单曲界面
 */
public class MusicsFragment extends BaseFragment {

    private ListView listview;//展示歌曲列表的listview
    private MusicListAdapter adapter;//展示歌曲列表的适配器
    private Context context;
    //    private List<MusicListItem> mItems = new ArrayList<MusicListItem>();//存储歌曲信息的集合
    public static final String UPDATE_ACTION = "com.wwj.action.UPDATE_ACTION";  //更新动作

    public static MusicsFragment newInstance(Context context) {
        MusicsFragment musicsFragment = new MusicsFragment();
//        this.context = context;
        return musicsFragment;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.music_fragment, null);
        listview = (ListView) view.findViewById(R.id.music_listview);
        return view;
    }

    @Override
    public void onDestroy() {
        System.out.println("MusicsFragment is destroy");
        super.onDestroy();
    }

    @Override
    public void initData() {
        System.out.println("musicsFragment创建成功");
        adapter = new MusicListAdapter(getActivity(), MainActivity.mItems);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (MainActivity.mItems != null) {   //如果歌曲集合不为空
                    MusicListItem musicListItem = MainActivity.mItems.get(position);

                    Intent sendIntent = new Intent(UPDATE_ACTION);
                    //保存当前播放的歌曲标识
                    sendIntent.putExtra("current", position);
                    //发送广播，将被MediaPlayActivity组件中的BroadcastReceiver接收到
                    getActivity().sendBroadcast(sendIntent);

                    //将歌曲路径放入intent中
                    Intent intent = new Intent();
                    intent.putExtra("url", musicListItem.getUrl());
                    intent.putExtra("title", musicListItem.getTitle());
                    intent.putExtra("artist", musicListItem.getArtist());
                    intent.putExtra("listPosition", position);
                    intent.putExtra("duration", musicListItem.getTimes());
                    intent.setClass(getActivity(), MediaPlayActivity.class);
                    //跳转到播放界面
                    startActivity(intent);
                } else {
                    System.out.println("数据为空");
                }
            }
        });
    }
}
