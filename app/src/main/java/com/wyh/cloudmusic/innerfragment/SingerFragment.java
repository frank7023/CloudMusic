package com.wyh.cloudmusic.innerfragment;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.wyh.cloudmusic.MainActivity;
import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.adapter.SingerListAdapter;
import com.wyh.cloudmusic.base.BaseFragment;

/**
 * Created by haoge728 on 2016/9/17.
 * 本地音乐下的歌手界面
 */
public class SingerFragment extends BaseFragment {

    private ListView listView;
    private Context context;
    private SingerListAdapter adapter;

    public SingerFragment(Context context) {
        this.context = context;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.singer_fragment, null);
        listView = (ListView) view.findViewById(R.id.singer_listview);
        return view;
    }

    @Override
    public void initData() {
        System.out.println("SingerFragment创建成功");
        adapter = new SingerListAdapter(context, MainActivity.mItems);
        listView.setAdapter(adapter);
    }
}
