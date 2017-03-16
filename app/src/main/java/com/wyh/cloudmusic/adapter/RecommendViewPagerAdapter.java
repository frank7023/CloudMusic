package com.wyh.cloudmusic.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by haoge728 on 2016/9/20.
 * recommendFragment中viewpager的适配器
 */
public class RecommendViewPagerAdapter extends PagerAdapter {
    private int[] imageIDs;
    private Context context;

    public RecommendViewPagerAdapter(Context contexts, int[] imageIDs) {
        this.imageIDs = imageIDs;
        this.context = contexts;
    }

    @Override
    public int getCount() {
        return imageIDs.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //对ViewPager页号求模取出View列表中要显示的项
        position = position % imageIDs.length;
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);//指定把图片拉伸为控件的宽和高
        imageView.setImageResource(imageIDs[position]);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
