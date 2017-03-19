package com.wyh.cloudmusic.innerfragment;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.base.BaseFragment;

/**
 * Created by haoge728 on 2016/9/17.
 * 本地音乐下的文件夹界面
 */
public class FileFragment extends BaseFragment {

    private TextView textView;
    private Context context;

    public FileFragment(Context context) {
        this.context = context;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.file_fragment, null);
        textView = (TextView) view.findViewById(R.id.textview);
        return view;
    }

    @Override
    public void initData() {
        System.out.println("FileFragment创建成功");
        textView.setText("文件夹");
        textView.setTextSize(30);
    }
}
