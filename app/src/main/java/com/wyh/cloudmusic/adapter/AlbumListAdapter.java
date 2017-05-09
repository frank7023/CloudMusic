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

import java.util.List;

/**
 * 2017年3月18日 11:10:11
 * 本地音乐里专辑的ListView的适配器
 */

public class AlbumListAdapter extends BaseAdapter {


    private LayoutInflater mInflater;
    private List<MusicListItem> mItems;
    private Context context;

    public AlbumListAdapter(Context context, List<MusicListItem> data) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
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
            view = mInflater.inflate(R.layout.album_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.album_image = (ImageView) view.findViewById(R.id.album_image);
            viewHolder.album_title = (TextView) view.findViewById(R.id.album_title);
            viewHolder.album_artist = (TextView) view.findViewById(R.id.album_artist);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if (mItems.get(position) != null) {
            viewHolder.album_image.setImageBitmap(mItems.get(position).getAlbum_image());
              //获取专辑图片
//            viewHolder.album_image.setImageBitmap(SearchMusicUtil.getArtwork(context, mItems.get(position).getId(), mItems.get(position).getAlbumID(), true, true));
            viewHolder.album_title.setText(mItems.get(position).getAlbum());
            viewHolder.album_artist.setText(mItems.get(position).getArtist());
        }
        return view;
    }

    static class ViewHolder {
        ImageView album_image;
        TextView album_title;
        TextView album_artist;
    }
}
