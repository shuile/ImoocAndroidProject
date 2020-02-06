package cn.imooc.imoochomework_android05.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.imooc.imoochomework_android05.R;
import cn.imooc.imoochomework_android05.adapter.MainMenuAdapter;
import cn.imooc.imoochomework_android05.adapter.NewsAdapter;
import cn.imooc.imoochomework_android05.adapter.SecondMenuAdapter;
import cn.imooc.imoochomework_android05.util.DataUtil;

public class FindFragment extends Fragment {

    private int[] mainMenuIcons = {R.mipmap.find_main_travel, R.mipmap.find_main_square,
                    R.mipmap.find_main_hotwind, R.mipmap.find_main_way};
    private String[] menus;
    private RecyclerView mMainRecyclerViewMenu;

    private int[] secondMenuIcons = {R.mipmap.find_channel_parter, R.mipmap.find_chnnel_me,
                    R.mipmap.find_channel_online};
    private RecyclerView mSecondRecyclerViewMenu;

    private int[] newsPic = {R.mipmap.find_hot_city, R.mipmap.find_hot_home,
                    R.mipmap.find_hot_jiangnan};
    private String[] newsContent;
    private String[] newsFrom;
    private int[] seeNumber = {10526, 10002, 895};
    private int[] dianNumber = {23, 15, 0};
    private RecyclerView mNewsRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mMainRecyclerViewMenu = (RecyclerView) getView().findViewById(R.id.find_recyclerview_main_menu);
        mSecondRecyclerViewMenu = (RecyclerView) getView().findViewById(R.id.find_recyclerview_second_menu);
        mNewsRecyclerView = (RecyclerView) getView().findViewById(R.id.news_recyclerview);

        menus = this.getActivity().getResources().getStringArray(R.array.find_main_menu);
        mMainRecyclerViewMenu.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        MainMenuAdapter adapter = new MainMenuAdapter(getActivity(), DataUtil.getMainMenus(mainMenuIcons, menus));
        mMainRecyclerViewMenu.setAdapter(adapter);

        menus = this.getActivity().getResources().getStringArray(R.array.find_second_menu1);
        mSecondRecyclerViewMenu.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        SecondMenuAdapter secondMenuAdapter = new SecondMenuAdapter(getActivity(), DataUtil.getMainMenus(secondMenuIcons, menus));
        mSecondRecyclerViewMenu.setAdapter(secondMenuAdapter);

        newsContent = this.getActivity().getResources().getStringArray(R.array.news_content);
        newsFrom = this.getActivity().getResources().getStringArray(R.array.news_from);
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        NewsAdapter newsAdapter = new NewsAdapter(getActivity(), DataUtil.getNews(newsContent, newsFrom, seeNumber, dianNumber, newsPic));
        mNewsRecyclerView.setAdapter(newsAdapter);
    }
}