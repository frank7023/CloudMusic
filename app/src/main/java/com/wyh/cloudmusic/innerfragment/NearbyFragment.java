package com.wyh.cloudmusic.innerfragment;

import android.view.View;
import android.widget.TextView;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.base.BaseFragment;

/**
 * Created by haoge728 on 2016/9/17.
 * 朋友圈下的附近界面
 */
public class NearbyFragment extends BaseFragment {

    private TextView textView;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.nearby_fragment,null);
        textView = (TextView) view.findViewById(R.id.textview);
        return view;
    }

    @Override
    public void initData() {
        textView.setText("附近");
        textView.setTextSize(30);
    }
}
