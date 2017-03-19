package com.wyh.cloudmusic.innerfragment;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.base.BaseFragment;

/**
 * Created by haoge728 on 2016/9/17.
 * 本地音乐下的歌手界面
 */
public class SingerFragment extends BaseFragment {

    private TextView textView;
    private Context context;

    public SingerFragment(Context context) {
        this.context = context;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.singer_fragment, null);
        textView = (TextView) view.findViewById(R.id.textview);
        return view;
    }

    @Override
    public void initData() {
        System.out.println("SingerFragment创建成功");
        textView.setText("歌手");
        textView.setTextSize(30);
    }
}
