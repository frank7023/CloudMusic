package com.wyh.cloudmusic.implement;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.wyh.cloudmusic.activity.CarActivity;
import com.wyh.cloudmusic.activity.ClockActivity;
import com.wyh.cloudmusic.activity.CloudActivity;
import com.wyh.cloudmusic.activity.FreeActivity;
import com.wyh.cloudmusic.activity.ListenmusicActivity;
import com.wyh.cloudmusic.activity.MessageActivity;
import com.wyh.cloudmusic.activity.NightActivity;
import com.wyh.cloudmusic.activity.ShopActivity;
import com.wyh.cloudmusic.activity.SkinActivity;
import com.wyh.cloudmusic.activity.TimeActivity;
import com.wyh.cloudmusic.activity.VipActivity;

/**
 * Created by haoge728 on 2016/9/16.
 * 左侧侧滑栏listview中每个item点击事件处理
 */
public class LeftItemClickListener implements AdapterView.OnItemClickListener {

    private Intent intent = new Intent();
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                intent.setClass(view.getContext(), MessageActivity.class);
                view.getContext().startActivity(intent);
                System.out.println("点击了");
                break;
            case 1:
                intent.setClass(view.getContext(), ShopActivity.class);
                view.getContext().startActivity(intent);
                break;
            case 2:
                intent.setClass(view.getContext(), VipActivity.class);
                view.getContext().startActivity(intent);
                break;
            case 3:
                intent.setClass(view.getContext(), FreeActivity.class);
                view.getContext().startActivity(intent);
                break;
            case 4:
                intent.setClass(view.getContext(), ListenmusicActivity.class);
                view.getContext().startActivity(intent);
                break;
            case 5:
                intent.setClass(view.getContext(), SkinActivity.class);
                view.getContext().startActivity(intent);
                break;
            case 6:
                intent.setClass(view.getContext(), NightActivity.class);
                view.getContext().startActivity(intent);
                break;
            case 7:
                intent.setClass(view.getContext(), TimeActivity.class);
                view.getContext().startActivity(intent);
                break;
            case 8:
                intent.setClass(view.getContext(), ClockActivity.class);
                view.getContext().startActivity(intent);
                break;
            case 9:
                intent.setClass(view.getContext(), CarActivity.class);
                view.getContext().startActivity(intent);
                break;
            case 10:
                intent.setClass(view.getContext(), CloudActivity.class);
                view.getContext().startActivity(intent);
                break;

        }
    }

}
