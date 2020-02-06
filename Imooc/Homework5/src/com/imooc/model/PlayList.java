package com.imooc.model;

import java.io.Serializable;
import java.util.List;

//播放列表类
public class PlayList implements Serializable {

    private String playListName;
    private List<Song> musicList;

    public PlayList() {
    }

    public PlayList(String playListName, List<Song> musicList) {
        this.playListName = playListName;
        this.musicList = musicList;
    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    public List<Song> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Song> musicList) {
        this.musicList = musicList;
    }

    //将歌曲添加到播放列表
    public void addToPlayList(Song song) {

    }

    //显示播放列表中所有歌曲
    public void displayAllSong() {

    }

    //通过id查询歌曲
    public Song searchSongById(String id) {
        Song song = null;

        return song;
    }

    //通过名称查询歌曲
    public Song searchSongByName(String name) {
        Song song = null;

        return song;
    }

    //修改歌曲
    public void updateSong(Song song) {

    }

    //从播放列表删除歌曲
    public void deleteSong(String id) {

    }

    //导出歌单
    public void exportplayList() {

    }
}
