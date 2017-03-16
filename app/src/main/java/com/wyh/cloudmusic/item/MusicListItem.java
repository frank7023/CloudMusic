package com.wyh.cloudmusic.item;

public class MusicListItem {

    private int id;    //音乐ID

    private String title; //音乐名

    private String artist; //歌手名

    private String path; //歌曲路径

    private String album; //专辑名

    private int albumID;//专辑ID

    private String times; //歌曲的总播放时长

    private long size; //歌曲文件大小

    private String sortletters;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return path;
    }

    public void setUrl(String path) {
        this.path = path;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getSortletters() {
        return sortletters;
    }

    public void setSortletters(String sortletters) {
        this.sortletters = sortletters;
    }
}
