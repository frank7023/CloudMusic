package com.wyh.cloudmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.item.MusicListItem;

import java.util.List;

/**
 * Created by haoge728 on 2016/10/23.
 * 本地音乐上ListView的适配器
 */
public class MusicListAdapter extends BaseAdapter {


    private LayoutInflater mInflater;
    private List<MusicListItem> mItems;

    public MusicListAdapter(Context context, List<MusicListItem> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mItems = data;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
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
            view = mInflater.inflate(R.layout.music_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.music_title = (TextView) view.findViewById(R.id.music_title);
            viewHolder.music_artist = (TextView) view.findViewById(R.id.music_artist);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if (mItems.get(position) != null) {
            viewHolder.music_title.setText(mItems.get(position).getTitle());
            viewHolder.music_artist.setText(mItems.get(position).getArtist());
        }
        return view;
    }

    static class ViewHolder {
        TextView music_title;
        TextView music_artist;
    }
}
