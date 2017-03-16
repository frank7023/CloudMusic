package com.wyh.cloudmusic.innerfragment;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.adapter.RankListAdapter;
import com.wyh.cloudmusic.base.BaseFragment;
import com.wyh.cloudmusic.init.InitRankListData;
import com.wyh.cloudmusic.item.RankListItem;

import java.util.ArrayList;

/**
 * Created by haoge728 on 2016/9/17.
 * 主页推荐页面下的排行榜界面
 */
public class RankListFragment extends BaseFragment {

    private TextView textView;
    private ListView listView;
    private RankListAdapter adapter;
    private ArrayList<RankListItem> mItems = new ArrayList<RankListItem>();

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.ranklist_fragment, null);
        textView = (TextView) view.findViewById(R.id.textview);
        listView = (ListView) view.findViewById(R.id.rank_list);
        return view;
    }

    @Override
    public void initData() {
        InitRankListData.initRankListItem(mItems);//初始化排行榜数据
        adapter = new RankListAdapter(mActivity, mItems);
        listView.setAdapter(adapter);
    }
}
