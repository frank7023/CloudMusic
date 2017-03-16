package com.wyh.cloudmusic.innerfragment;

import android.view.View;
import android.widget.TextView;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.base.BaseFragment;

/**
 * Created by haoge728 on 2016/9/17.
 * 主页推荐页面下的歌单界面
 */
public class PlayListFragment extends BaseFragment {

    private TextView textView;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.playlist_fragment,null);

        return view;
    }

    @Override
    public void initData() {

    }
}
