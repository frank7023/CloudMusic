package com.wyh.cloudmusic.innerfragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.adapter.RecommendViewPagerAdapter;
import com.wyh.cloudmusic.base.BaseFragment;
import com.wyh.cloudmusic.fragment.HomepageFragment;

import java.util.ArrayList;

/**
 * Created by haoge728 on 2016/9/17.
 * 主页推荐页面下的个性推荐界面
 */
public class RecommendFragment extends BaseFragment implements View.OnClickListener {

    private TextView more_music_list, more_radio_station;
    private ViewPager viewPager;
    private ImageView imageView;
    private LinearLayout points_group;
    private LinearLayout.LayoutParams params;
    private int currentposition;
    private int previousPointEnale = 0;//记录上一次点的位置
    private HomepageFragment homepageFragment;
    private ArrayList<ImageView> points_list;
    private int[] imageIDs = new int[]{
            R.drawable.first,
            R.drawable.second,
            R.drawable.third,
            R.drawable.fourth,
            R.drawable.five
    };

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            currentposition = viewPager.getCurrentItem();
            if (currentposition < imageIDs.length - 1) {
                currentposition++;
            } else {
                currentposition = 0;
            }
            viewPager.setCurrentItem(currentposition);

            handler.sendEmptyMessageDelayed(0, 4000);//继续延迟3秒发送消息让Handler继续执行 形成循环
            return false;
        }
    });

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.recommend_fragment, null);
        more_music_list = (TextView) view.findViewById(R.id.more_music_list);
        more_radio_station = (TextView) view.findViewById(R.id.more_radio_station);
        viewPager = (ViewPager) view.findViewById(R.id.recommend_viewpager);
        points_group = (LinearLayout) view.findViewById(R.id.ll_points);
        return view;
    }

    @Override
    public void initData() {
        //给viewpager加点，有几张图就有几个点
        points_list = new ArrayList<ImageView>();
        for (int i = 0; i < imageIDs.length; i++) {
            imageView = new ImageView(getActivity());
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.dot_focus);
            } else {
                imageView.setBackgroundResource(R.drawable.dot_normal);
            }
            params = new LinearLayout.LayoutParams(20, 20);
            if (i != 0) {
                params.leftMargin = 10;
            }
            imageView.setLayoutParams(params);
            points_list.add(imageView);//将点加到集合里
            points_group.addView(imageView);
        }

        more_music_list.setOnClickListener(this);
        more_radio_station.setOnClickListener(this);
        viewPager.setAdapter(new RecommendViewPagerAdapter(mActivity, imageIDs));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //遍历小圆点集合，设置小圆点的不同状态
                for (int i = 0; i < points_list.size(); i++) {
                    if (i == position % points_list.size()) {
                        points_list.get(i).setBackgroundResource(R.drawable.dot_focus);
                    } else {
                        points_list.get(i).setBackgroundResource(R.drawable.dot_normal);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        handler.sendEmptyMessageDelayed(0, 4000);// 延时3秒后发送消息让handler来实现轮播
    }

    @Override
    public void onClick(View v) {
        if (homepageFragment==null){
            homepageFragment = new HomepageFragment();
        }
        switch (v.getId()) {
            case R.id.more_music_list:
                homepageFragment.setCurrentPager(1);
                break;
            case R.id.more_radio_station:
                homepageFragment.setCurrentPager(2);
                break;
        }
    }
}
