package com.wyh.cloudmusic.innerfragment;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.wyh.cloudmusic.MainActivity;
import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.adapter.AlbumListAdapter;
import com.wyh.cloudmusic.base.BaseFragment;

/**
 * Created by haoge728 on 2016/9/17.
 * 本地音乐下的专辑界面
 */
public class AlbumFragment extends BaseFragment {

    private ListView listview;//展示专辑列表的listview
    private AlbumListAdapter adapter;//展示专辑列表的适配器
    private Context context;
//    private List<MusicListItem> mItems = new ArrayList<MusicListItem>();//存储歌曲信息的集合

    public AlbumFragment(Context context) {
        this.context = context;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.album_fragment, null);
        listview = (ListView) view.findViewById(R.id.album_listview);
        return view;
    }

    @Override
    public void initData() {
        System.out.println("AlbumFragment创建成功");
        adapter = new AlbumListAdapter(context, MainActivity.mItems, listview);
        listview.setAdapter(adapter);
    }
}
