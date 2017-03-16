package com.wyh.cloudmusic.init;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.item.PopupWindowListItem;

import java.util.ArrayList;

/**
 * Created by haoge728 on 2016/10/4.
 */
public class InitPopupWindowListData {

    public static void initPopupWindowListItem(ArrayList<PopupWindowListItem> mItems, int type) {
        switch (type){
            case 0:
                PopupWindowListItem item1 = new PopupWindowListItem(R.drawable.create_playlist, "创建新歌单");
                mItems.add(item1);
                PopupWindowListItem item2 = new PopupWindowListItem(R.drawable.control_playlist, "管理歌单");
                mItems.add(item2);
                break;

            case 1:
                PopupWindowListItem plItem1 = new PopupWindowListItem(R.drawable.download_playlist, "下载");
                mItems.add(plItem1);
                PopupWindowListItem plItem2 = new PopupWindowListItem(R.drawable.delete_playlist, "删除");
                mItems.add(plItem2);
                PopupWindowListItem plItem3 = new PopupWindowListItem(R.drawable.edit_playlist, "编辑歌单信息");
                mItems.add(plItem3);
                break;
        }
    }
}
