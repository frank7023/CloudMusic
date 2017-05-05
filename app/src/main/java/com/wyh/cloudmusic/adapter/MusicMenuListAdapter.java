package com.wyh.cloudmusic.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.init.InitPopupWindowListData;
import com.wyh.cloudmusic.item.MusicMenuListItem;
import com.wyh.cloudmusic.item.PopupWindowListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 2016年10月2日 15:33:49
 * 音乐界面listview的适配器
 */
public class MusicMenuListAdapter extends BaseAdapter {

    public static final int TYPE_COUNT = 3;//listview里类型的总数
    public static final int MENU_TYPE = 0;//菜单类型
    public static final int TITLE_TYPE = 1;//标题类型
    public static final int PLAYLIST_TYPE = 2;//歌单类型

    private LayoutInflater mInflater;
    private List<MusicMenuListItem> mItems;//音乐页面所有item的集合（3种类型）
    private Context context;
    private PopupWindow mPopWindow;
    private ListView popListView;//popupwindow中的ListView
    private TextView popWindowTitle;
    private ArrayList<PopupWindowListItem> popupWindowList = new ArrayList<PopupWindowListItem>();//创建歌单中的popupwindow的集合
    private ArrayList<PopupWindowListItem> plPopupWindowList = new ArrayList<PopupWindowListItem>();//歌单中的popupwindow的集合
    private MusicMenuListItem musicMenuListItem;
    private int tag;

    public MusicMenuListAdapter(Context context, List<MusicMenuListItem> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mItems = data;
        this.context = context;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.get(position).getItem_type() == 0) {
            return MENU_TYPE;
        } else if (mItems.get(position).getItem_type() == 1) {
            return TITLE_TYPE;
        } else if (mItems.get(position).getItem_type() == 2) {
            return PLAYLIST_TYPE;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        View view = convertView;
        switch (type) {
            case 0:
                ViewHolder1 viewHolder1;
                if (view == null) {
                    view = mInflater.inflate(R.layout.music_menu_list_item1, null);
                    viewHolder1 = new ViewHolder1();
                    viewHolder1.item_icon = (ImageView) view.findViewById(R.id.music_menu_item_icon1);
                    viewHolder1.item_title = (TextView) view.findViewById(R.id.music_menu_item_title1);
                    viewHolder1.item_title_tag = (TextView) view.findViewById(R.id.music_menu_item_tag1);
                    view.setTag(viewHolder1);
                } else {
                    viewHolder1 = (ViewHolder1) view.getTag();
                }
                if (mItems.get(position) != null) {
                    viewHolder1.item_icon.setImageResource(mItems.get(position).getTitle_icon());
                    viewHolder1.item_title.setText(mItems.get(position).getMenu_title());
                    viewHolder1.item_title_tag.setText(mItems.get(position).getTitle_tag());
                }
                break;

            case 1:
                ViewHolder2 viewHolder2;
                if (view == null) {
                    view = mInflater.inflate(R.layout.music_menu_list_item2, null);
                    viewHolder2 = new ViewHolder2();
                    viewHolder2.arrow = (ImageView) view.findViewById(R.id.music_menu_arrow2);
                    viewHolder2.title = (TextView) view.findViewById(R.id.music_menu_item_title2);
                    viewHolder2.count = (TextView) view.findViewById(R.id.music_menu_item_tag2);
                    viewHolder2.menu = (ImageView) view.findViewById(R.id.music_menu_button2);
                    viewHolder2.menu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showPopupWindow(0);//显示创建歌单的PopupWindow界面
                        }
                    });
                    view.setTag(viewHolder2);
                } else {
                    viewHolder2 = (ViewHolder2) view.getTag();
                }
                if (mItems.get(position) != null) {
                    viewHolder2.arrow.setImageResource(mItems.get(position).getPlaylist_icon());
                    viewHolder2.title.setText(mItems.get(position).getPlaylist_title());
                    viewHolder2.count.setText(mItems.get(position).getPlaylist_music_count());
                    viewHolder2.menu.setImageResource(mItems.get(position).getMenu_button());
                }
                break;

            case 2:
                ViewHolder3 viewHolder3;
                if (view == null) {
                    view = mInflater.inflate(R.layout.music_menu_list_item3, null);
                    viewHolder3 = new ViewHolder3();
                    viewHolder3.playlist_icon = (ImageView) view.findViewById(R.id.playlist_icon);
                    viewHolder3.playlist_title = (TextView) view.findViewById(R.id.playlist_title);
                    viewHolder3.playlist_count = (TextView) view.findViewById(R.id.playlist_music_count);
                    viewHolder3.playlist_menu = (ImageView) view.findViewById(R.id.music_menu_button);
                    viewHolder3.playlist_menu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showPopupWindow(1);//显示创建歌单的PopupWindow界面
                            tag = position + 1;//标记position值，用于删除item
                        }
                    });
                    view.setTag(viewHolder3);
                } else {
                    viewHolder3 = (ViewHolder3) view.getTag();
                }
                if (mItems.get(position) != null) {
                    viewHolder3.playlist_icon.setImageResource(mItems.get(position).getPlaylist_icon());
                    viewHolder3.playlist_title.setText(mItems.get(position).getPlaylist_title());
                    viewHolder3.playlist_count.setText(mItems.get(position).getPlaylist_music_count());
                    viewHolder3.playlist_menu.setImageResource(mItems.get(position).getMenu_button());
                }
                break;
        }
        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        if (position == 5) {
            return false;
        }
        return true;
    }


    /**
     * 显示popupwindow界面
     * type=0 显示创建歌单上的PopupWindow
     * type=1 显示歌单上的PopupWindow
     */
    private void showPopupWindow(int type) {
        //设置contentView
        View contentView = LayoutInflater.from(context).inflate(R.layout.popupwindow, null);
        //初始化PopupWindow中的ListView
        switch (type) {
            case 0:
                initPopListView(contentView, popupWindowList, type);
                break;
            case 1:
                initPopListView(contentView, plPopupWindowList, type);
                break;
        }
        //设置PopupWindow属性
        mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setFocusable(true);
        mPopWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        //设置进场动画
        mPopWindow.setAnimationStyle(R.style.popup_window_bottombar);//设置动画样式
        setBackgroundAlpha(0.5f);//设置屏幕透明度
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(context).inflate(R.layout.music_fragment, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

    }

    /**
     * 初始化创建歌单的popupwindow里的listview
     * 根据不同的type来选择不同的数据集合到ListView的适配器中
     * type=0 代表创建歌单上的PopupWindow
     * type=1 代表歌单上的PopupWindow
     */
    public void initPopListView(View view, ArrayList<PopupWindowListItem> popItemList, int type) {
        popListView = (ListView) view.findViewById(R.id.popupwindow_listview);
        popWindowTitle = (TextView) view.findViewById(R.id.popwindow_title);
        if (popItemList.size() == 0) {
            InitPopupWindowListData.initPopupWindowListItem(popItemList, type);
        }
        PopupWindowListAdapter popupWindowListAdapter = new PopupWindowListAdapter(context, popItemList);
        popListView.setAdapter(popupWindowListAdapter);
        switch (type) {
            case 0:
                popWindowTitle.setText("创建的歌单");
                popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            //创建歌单的Dialog
                            case 0:
                                LayoutInflater layoutInflater = LayoutInflater.from(context);
                                View dialog = layoutInflater.inflate(R.layout.create_playlist_dialog, null);
                                final EditText editText = (EditText) dialog.findViewById(R.id.playlist_name);
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setTitle("新建歌单");

                                builder.setPositiveButton("提交", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //创建新歌单并刷新listview
                                        musicMenuListItem = new MusicMenuListItem(2, R.drawable.radio_station_12, editText.getText().toString(), "0首，已下载0首", R.drawable.playlist_menu_button);
                                        mItems.add(musicMenuListItem);
                                        notifyDataSetChanged();
                                        mPopWindow.dismiss();
                                    }
                                });
                                builder.setNegativeButton("取消", null);
                                builder.setView(dialog);
                                builder.show();
                                mPopWindow.dismiss();
                                break;

                            case 1:
                                Toast.makeText(context, "正在打开管理歌单...", Toast.LENGTH_SHORT).show();
                                mPopWindow.dismiss();
                                break;

                        }
                    }
                });
                break;

            case 1:
                popWindowTitle.setText("歌单");
                popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                Toast.makeText(context, "正在下载...", Toast.LENGTH_SHORT).show();
                                mPopWindow.dismiss();
                                break;

                            case 1:
                                //删除歌单
                                mItems.remove(tag);
                                notifyDataSetChanged();
                                mPopWindow.dismiss();
                                break;

                            case 2:
                                Toast.makeText(context, "正在打开歌单信息...", Toast.LENGTH_SHORT).show();
                                mPopWindow.dismiss();
                                break;
                        }
                    }
                });
                break;

        }
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) context).getWindow().setAttributes(lp);
    }

    static class ViewHolder1 {
        ImageView item_icon;
        TextView item_title;
        TextView item_title_tag;
    }

    static class ViewHolder2 {
        ImageView arrow;
        TextView title;
        TextView count;
        ImageView menu;
    }

    static class ViewHolder3 {
        ImageView playlist_icon;
        TextView playlist_title;
        TextView playlist_count;
        ImageView playlist_menu;
    }

}
