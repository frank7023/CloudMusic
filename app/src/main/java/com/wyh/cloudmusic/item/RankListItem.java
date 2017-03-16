package com.wyh.cloudmusic.item;

/**
 * Created by haoge728 on 2016/9/26.
 */
public class RankListItem {

    private int type;//类型

    private int title_icon;//标题图片
    private String title;//标题

    private int official_rank_icon;//官方榜图片
    private String official_rank_text1;//官方榜内容
    private String official_rank_text2;//官方榜内容
    private String official_rank_text3;//官方榜内容

    private int global_rank_icon;//全球榜图片
    private String global_rank_text1;//全球榜内容
    private String global_rank_text2;//全球榜内容

    public RankListItem(int type, int title_icon, String title) {
        this.type = type;
        this.title_icon = title_icon;
        this.title = title;
    }

    public RankListItem( int type,int global_rank_icon, String global_rank_text1, String global_rank_text2) {
        this.type = type;
        this.global_rank_icon = global_rank_icon;
        this.global_rank_text1 = global_rank_text1;
        this.global_rank_text2 = global_rank_text2;
    }

    public RankListItem(int type, int official_rank_icon, String official_rank_text1, String official_rank_text2, String official_rank_text3) {
        this.type = type;
        this.official_rank_icon = official_rank_icon;
        this.official_rank_text1 = official_rank_text1;
        this.official_rank_text2 = official_rank_text2;
        this.official_rank_text3 = official_rank_text3;
    }

    public int getType() {
        return type;
    }

    public int getTitle_icon() {
        return title_icon;
    }

    public String getGlobal_rank_text2() {
        return global_rank_text2;
    }

    public String getGlobal_rank_text1() {
        return global_rank_text1;
    }

    public int getGlobal_rank_icon() {
        return global_rank_icon;
    }

    public String getOfficial_rank_text3() {
        return official_rank_text3;
    }

    public String getOfficial_rank_text2() {
        return official_rank_text2;
    }

    public String getOfficial_rank_text1() {
        return official_rank_text1;
    }

    public int getOfficial_rank_icon() {
        return official_rank_icon;
    }

    public String getTitle() {
        return title;
    }
}
