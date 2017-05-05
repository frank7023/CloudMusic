package com.wyh.cloudmusic.item;

/**
 * Created by haoge728 on 2016/10/2.
 */
public class MusicMenuListItem {

    private int title_icon;//音乐界面菜单的图标
    private String menu_title;//音乐界面菜单的标题
    private String music_sum;//音乐界面菜单标题后面的数字

    private int playlist_icon;//歌单图标
    private String playlist_title;//歌单标题
    private String playlist_music_count;//歌单的歌曲数
    private int menu_button;//操作歌单的按钮

    private int item_type;//item的类型

    public MusicMenuListItem(){

    }

    public MusicMenuListItem(int item_type, int title_icon, String menu_title, String music_sum) {
        this.item_type = item_type;
        this.title_icon = title_icon;
        this.menu_title = menu_title;
        this.music_sum = music_sum;
    }

    public MusicMenuListItem(int item_type, int playlist_icon, String playlist_title, String playlist_music_count, int menu_button) {
        this.item_type = item_type;
        this.playlist_icon = playlist_icon;
        this.playlist_title = playlist_title;
        this.playlist_music_count = playlist_music_count;
        this.menu_button = menu_button;
    }

    public int getTitle_icon() {
        return title_icon;
    }

    public void setTitle_icon(int title_icon) {
        this.title_icon = title_icon;
    }

    public String getMenu_title() {
        return menu_title;
    }

    public void setMenu_title(String menu_title) {
        this.menu_title = menu_title;
    }

    public String getMusic_sum() {
        return music_sum;
    }

    public void setMusic_sum(String title_tag) {
        this.music_sum = music_sum;
    }

    public int getPlaylist_icon() {
        return playlist_icon;
    }

    public void setPlaylist_icon(int playlist_icon) {
        this.playlist_icon = playlist_icon;
    }

    public String getPlaylist_music_count() {
        return playlist_music_count;
    }

    public void setPlaylist_music_count(String playlist_music_count) {
        this.playlist_music_count = playlist_music_count;
    }

    public String getPlaylist_title() {
        return playlist_title;
    }

    public void setPlaylist_title(String playlist_title) {
        this.playlist_title = playlist_title;
    }

    public int getMenu_button() {
        return menu_button;
    }

    public void setMenu_button(int menu_button) {
        this.menu_button = menu_button;
    }

    public int getItem_type() {
        return item_type;
    }

    public void setItem_type(int item_type) {
        this.item_type = item_type;
    }
}
