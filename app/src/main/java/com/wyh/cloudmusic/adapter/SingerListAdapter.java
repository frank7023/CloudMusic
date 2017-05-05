package com.wyh.cloudmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.item.MusicListItem;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 2017年5月5日 10:54:07
 * 本地音乐里歌手的ListView的适配器
 */

public class SingerListAdapter extends BaseAdapter {


    private LayoutInflater mInflater;
    private List<MusicListItem> mItems;
    private List<String> singer_list = new ArrayList<>();
    private Context context;

    public SingerListAdapter(Context context, List<MusicListItem> data) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mItems = data;
        getArtistList(mItems);//去掉重复的歌手名
    }

    /**
     * 去掉重复的歌手名
     *
     * @param list
     * @return
     */
    public List<String> getArtistList(List<MusicListItem> list) {
        for (int i = 0; i < list.size(); i++) {
            singer_list.add(list.get(i).getArtist());
        }
        //此时已经去掉重复的数据保存在hashset中
        Set set = new LinkedHashSet<String>();
        set.addAll(singer_list);
        singer_list.clear();
        singer_list.addAll(set);
        return singer_list;
    }

    @Override
    public int getCount() {
//        return mItems.size();
        return singer_list.size();
    }

    @Override
    public Object getItem(int position) {
//        return mItems.get(position);
        return singer_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if (view == null) {
            view = mInflater.inflate(R.layout.singer_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.singer_image = (ImageView) view.findViewById(R.id.singer_image);
            viewHolder.singer_name = (TextView) view.findViewById(R.id.singer_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

//        if (mItems.get(position) != null) {
//            viewHolder.singer_image.setImageBitmap(mItems.get(position).getAlbum_image());
            viewHolder.singer_name.setText(singer_list.get(position));
//            viewHolder.singer_name.setText(mItems.get(position).getArtist());
//        }
        return view;
    }

    static class ViewHolder {
        ImageView singer_image;
        TextView singer_name;
    }
}
