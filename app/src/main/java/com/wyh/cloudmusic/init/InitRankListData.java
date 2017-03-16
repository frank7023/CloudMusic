package com.wyh.cloudmusic.init;

import com.wyh.cloudmusic.R;
import com.wyh.cloudmusic.item.RankListItem;

import java.util.ArrayList;

/**
 * Created by haoge728 on 2016/9/26.
 */
public class InitRankListData {

    public static void initRankListItem(ArrayList<RankListItem> mItems) {
        mItems.add(new RankListItem(0,R.drawable.bar, "官方榜"));
        mItems.add(new RankListItem(1,R.drawable.ranklist_first, "1.你在终点等我-王菲", "2.南方的我，北方的你-王般诺", "3.告白气球-周杰伦"));
        mItems.add(new RankListItem(1,R.drawable.ranklist_second, "1.你在终点等我-王菲", "2.来日方长-薛之谦/黄龄", "3.不说-李荣浩"));
        mItems.add(new RankListItem(1,R.drawable.ranklist_third, "1.月-好妹妹乐队", "2.你喜欢还却不喜欢山-鹿先森乐队", "3.香草馥-戴荃"));
        mItems.add(new RankListItem(1,R.drawable.ranklist_fourth, "1.演员-薛之谦", "2.告白气球-周杰伦", "3.你还要我怎样-薛之谦"));
        mItems.add(new RankListItem(0,R.drawable.bar, "全球榜"));
        mItems.add(new RankListItem(2,R.drawable.global_list_1, "云音乐电音榜", "每周五更新"));
        mItems.add(new RankListItem(2,R.drawable.global_list_2, "云音乐ACG音乐榜", "每周四更新"));
        mItems.add(new RankListItem(2,R.drawable.global_list_3, "韩国Melon排行榜周榜", "每周一更新"));
        mItems.add(new RankListItem(2,R.drawable.global_list_3, "韩国Melon原声周榜", "每周一更新"));
        mItems.add(new RankListItem(2,R.drawable.global_list_5, "韩国Mnet排行榜周榜", "每周一更新"));
        mItems.add(new RankListItem(2,R.drawable.global_list_6, "Beatport全球电子舞曲榜", "每周四更新"));
        mItems.add(new RankListItem(2,R.drawable.global_list_7, "日本Oricon周榜", "每周三更新"));
        mItems.add(new RankListItem(2,R.drawable.global_list_8, "云音乐古典音乐榜", "每周四更新"));
        mItems.add(new RankListItem(2,R.drawable.global_list_9, "UK排行榜周榜", "刚刚更新"));
        mItems.add(new RankListItem(2,R.drawable.global_list_10, "美国Billboard周榜", "每周三更新"));
        mItems.add(new RankListItem(2,R.drawable.global_list_11, "法国 NRJ Vos Hits 周榜", "每周一更新"));
        mItems.add(new RankListItem(2,R.drawable.global_list_12, "iTunes榜", "刚刚更新"));
        mItems.add(new RankListItem(2,R.drawable.global_list_13, "Hit FM Top榜", "刚刚更新"));
        mItems.add(new RankListItem(2,R.drawable.global_list_14, "KTV唛榜", "每周五更新"));
        mItems.add(new RankListItem(2,R.drawable.global_list_15, "台湾Hito排行榜", "刚刚更新"));
        mItems.add(new RankListItem(2,R.drawable.global_list_16, "中国TOP排行榜 (港台榜)", "刚刚更新"));
        mItems.add(new RankListItem(2,R.drawable.global_list_17, "中国TOP排行榜 (内地榜)", "刚刚更新"));
        mItems.add(new RankListItem(2,R.drawable.global_list_18, "香港电台中文歌曲龙虎榜", "每周五更新"));
        mItems.add(new RankListItem(2,R.drawable.global_list_19, "华语金曲榜", "刚刚更新"));
        mItems.add(new RankListItem(2,R.drawable.global_list_20, "中国嘻哈榜", "每周五更新"));

    }
}