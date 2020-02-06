package com.imooc.model;

import java.util.Map;

//播放列表管理类
public class PlayListCollection {

    private Map playListMap;

    public PlayListCollection() {
    }

    public PlayListCollection(Map playListMap) {
        this.playListMap = playListMap;
    }

    public Map getPlayListMap() {
        return playListMap;
    }

    public void setPlayListMap(Map playListMap) {
        this.playListMap = playListMap;
    }

    //添加播放列表
    public void addPlayList(PlayList playList) {

    }

    //删除播放列表
    public void deletePlayList(PlayList playList) {

    }

    //通过名字查询播放列表
    public PlayList searchPlayListByName(String playListName) {
        PlayList playList = null;

        return playList;
    }

    //显示所有播放列表名称
    public void displayPlayListName() {

    }
}
