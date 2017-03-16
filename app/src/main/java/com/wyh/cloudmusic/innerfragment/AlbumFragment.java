package com.wyh.cloudmusic.innerfragment;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.base.BaseFragment;

/**
 * Created by haoge728 on 2016/9/17.
 * 本地音乐下的专辑界面
 */
public class AlbumFragment extends BaseFragment {

    private TextView textView;
    private Context context;

    public AlbumFragment(Context context) {
        this.context = context;
    }
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.album_fragment,null);
        textView = (TextView) view.findViewById(R.id.textview);
        return view;
    }

    @Override
    public void initData() {
        textView.setText("专辑");
        textView.setTextSize(30);
    }
}
