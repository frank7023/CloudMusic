package com.wyh.cloudmusic.item;

/**
 * Created by haoge728 on 2016/9/15.
 */
public class DrawerListItem {

    private int icon;//图标
    private String title;//文字说明

    public DrawerListItem() {

    }

    public DrawerListItem(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

}
