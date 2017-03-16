package com.wyh.cloudmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.item.DrawerListItem;

import java.util.List;

/**
 * Created by haoge728 on 2016/9/15.
 * 侧滑栏上ListView的适配器
 */
public class DrawerListAdapter extends BaseAdapter {


    private LayoutInflater mInflater;
    private List<DrawerListItem> mItems;

    public DrawerListAdapter(Context context, List<DrawerListItem> data) {
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
            view = mInflater.inflate(R.layout.drawer_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.item_icon = (ImageView) view.findViewById(R.id.left_item_icon);
            viewHolder.item_title = (TextView) view.findViewById(R.id.left_item_title);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        DrawerListItem mDrawerListItem = mItems.get(position);
        if (mDrawerListItem != null) {
            viewHolder.item_icon.setImageResource(mDrawerListItem.getIcon());
            viewHolder.item_title.setText(mDrawerListItem.getTitle());
        }
        return view;
    }

    static class ViewHolder {
        ImageView item_icon;
        TextView item_title;
    }
}
