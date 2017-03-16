package com.wyh.cloudmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.item.PopupWindowListItem;

import java.util.List;

/**
 * Created by haoge728 on 2016/10/4.
 * PopupWindow上ListView的适配器
 */
public class PopupWindowListAdapter extends BaseAdapter {


    private LayoutInflater mInflater;
    private List<PopupWindowListItem> mItems;

    public PopupWindowListAdapter(Context context, List<PopupWindowListItem> data) {
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
            view = mInflater.inflate(R.layout.popupwindow_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) view.findViewById(R.id.popupwindow_item_icon);
            viewHolder.title = (TextView) view.findViewById(R.id.popupwindow_item_title);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if (mItems.get(position) != null) {
            viewHolder.icon.setImageResource(mItems.get(position).getIcon());
            viewHolder.title.setText(mItems.get(position).getTitle());
        }
        return view;
    }

    static class ViewHolder {
        ImageView icon;
        TextView title;
    }
}
