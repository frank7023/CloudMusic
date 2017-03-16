package com.wyh.cloudmusic.item;

/**
 * Created by haoge728 on 2016/10/4.
 */
public class PopupWindowListItem {

    private int icon;
    private String title;

    public PopupWindowListItem(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
