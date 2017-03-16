package com.wyh.cloudmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.item.RankListItem;

import java.util.ArrayList;

/**
 * Created by haoge728 on 2016/9/26.
 */
public class RankListAdapter extends BaseAdapter {

    public static final int TYPE_COUNT = 3;//listview里类型的总数
    public static final int TITLE_TYPE = 0;//标题类型
    public static final int OFFICIAL_TYPE = 1;//官方榜类型
    public static final int GLOBAL_TYPE = 2;//全球榜类型

    private LayoutInflater mInflater;
    private ArrayList<RankListItem> mItems = new ArrayList<RankListItem>();

    public RankListAdapter(Context context, ArrayList<RankListItem> mItems) {
        this.mInflater = LayoutInflater.from(context);
        this.mItems = mItems;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.get(position).getType() == 0) {
            return TITLE_TYPE;
        } else if (mItems.get(position).getType() == 1) {
            return OFFICIAL_TYPE;
        } else if (mItems.get(position).getType() == 2) {
            return GLOBAL_TYPE;
        } else {
            return super.getItemViewType(position);
        }
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
    public boolean isEnabled(int position) {
        if (position == 0 || position == 5) {
            return false;
        }
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        switch (type) {
            case TITLE_TYPE:
                TitleViewHolder titleViewHolder;
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.rank_list_title_type, null);
                    titleViewHolder = new TitleViewHolder();
                    titleViewHolder.title_type = (TextView) convertView.findViewById(R.id.title_type);
                    titleViewHolder.title = (TextView) convertView.findViewById(R.id.rank_list_title);
                    titleViewHolder.title_icon = (ImageView) convertView.findViewById(R.id.rank_list_title_icon);
                    convertView.setTag(titleViewHolder);
                } else {
                    titleViewHolder = (TitleViewHolder) convertView.getTag();
                }
                titleViewHolder.title_icon.setBackgroundResource(mItems.get(position).getTitle_icon());
                titleViewHolder.title.setText(mItems.get(position).getTitle());
                break;

            case OFFICIAL_TYPE:
                OfficialViewHolder officialViewHolder;
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.rank_list_official_type, null);
                    officialViewHolder = new OfficialViewHolder();
                    officialViewHolder.official_type = (TextView) convertView.findViewById(R.id.official_type);
                    officialViewHolder.official_icon = (ImageView) convertView.findViewById(R.id.rank_list_official_icon);
                    officialViewHolder.official_text_1 = (TextView) convertView.findViewById(R.id.rank_list_official_text_1);
                    officialViewHolder.official_text_2 = (TextView) convertView.findViewById(R.id.rank_list_official_text_2);
                    officialViewHolder.official_text_3 = (TextView) convertView.findViewById(R.id.rank_list_official_text_3);
                    convertView.setTag(officialViewHolder);
                } else {
                    officialViewHolder = (OfficialViewHolder) convertView.getTag();
                }
                officialViewHolder.official_icon.setBackgroundResource(mItems.get(position).getOfficial_rank_icon());
                officialViewHolder.official_text_1.setText(mItems.get(position).getOfficial_rank_text1());
                officialViewHolder.official_text_2.setText(mItems.get(position).getOfficial_rank_text2());
                officialViewHolder.official_text_3.setText(mItems.get(position).getOfficial_rank_text3());
                break;

            case GLOBAL_TYPE:
                GloBalViewHolder gloBalViewHolder;
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.rank_list_global_type, null);
                    gloBalViewHolder = new GloBalViewHolder();
                    gloBalViewHolder.globall_type = (TextView) convertView.findViewById(R.id.global_type);
                    gloBalViewHolder.global_icon = (ImageView) convertView.findViewById(R.id.rank_list_global_icon);
                    gloBalViewHolder.global_text_1 = (TextView) convertView.findViewById(R.id.rank_list_global_text_1);
                    gloBalViewHolder.global_text_2 = (TextView) convertView.findViewById(R.id.rank_list_global_text_2);
                    convertView.setTag(gloBalViewHolder);
                } else {
                    gloBalViewHolder = (GloBalViewHolder) convertView.getTag();
                }
                gloBalViewHolder.global_icon.setBackgroundResource(mItems.get(position).getGlobal_rank_icon());
                gloBalViewHolder.global_text_1.setText(mItems.get(position).getGlobal_rank_text1());
                gloBalViewHolder.global_text_2.setText(mItems.get(position).getGlobal_rank_text2());
                break;
        }
        return convertView;
    }

    static class TitleViewHolder {
        TextView title_type;
        ImageView title_icon;
        TextView title;
    }

    static class OfficialViewHolder {
        TextView official_type;
        ImageView official_icon;
        TextView official_text_1;
        TextView official_text_2;
        TextView official_text_3;
    }

    static class GloBalViewHolder {
        TextView globall_type;
        ImageView global_icon;
        TextView global_text_1;
        TextView global_text_2;
    }
}
