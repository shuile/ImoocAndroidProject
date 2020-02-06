package cn.imooc.imoochomework_android05.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.imooc.imoochomework_android05.R;
import cn.imooc.imoochomework_android05.adapter.MainHeaderAdAdapter;
import cn.imooc.imoochomework_android05.adapter.MainMenuAdapter;
import cn.imooc.imoochomework_android05.adapter.SecondMenuAdapter;
import cn.imooc.imoochomework_android05.util.DataUtil;

/**
 * 主界面视图
 */
public class MainFragment extends Fragment {

    private int[] icons = {R.mipmap.header_pic_ad1, R.mipmap.header_pic_ad2, R.mipmap.header_pic_ad1};
    private ViewPager mVPagerHeaderAd;//广告头

    private int[] mainMenuIcons = {R.mipmap.menu_airport, R.mipmap.menu_car,
            R.mipmap.menu_course, R.mipmap.menu_hatol,
            R.mipmap.menu_nearby, R.mipmap.menu_train,
            R.mipmap.menu_ticket, R.mipmap.menu_train};
    private String[] menus;
    private RecyclerView mRecyclerViewMenu;//主菜单

    private int[] secondMenuIcons = {R.mipmap.menu_second_airport, R.mipmap.menu_second_play,
            R.mipmap.menu_second_quan};
    private RecyclerView mSecondRecyclerViewMenu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mVPagerHeaderAd = (ViewPager) getView().findViewById(R.id.vpager_main_header);
        mRecyclerViewMenu = (RecyclerView) getView().findViewById(R.id.recyclerview_main_menu);
        mSecondRecyclerViewMenu = (RecyclerView) getView().findViewById(R.id.recyclerview_second_menu);
        MainHeaderAdAdapter adAdapter = new MainHeaderAdAdapter(getActivity(), DataUtil.getHeaderAdInfo(getActivity(), icons));
        mVPagerHeaderAd.setAdapter(adAdapter);


        //菜单
        //布局样式
        menus = this.getActivity().getResources().getStringArray(R.array.main_menu);
        mRecyclerViewMenu.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        MainMenuAdapter mainMenuAdapter = new MainMenuAdapter(getActivity(), DataUtil.getMainMenus(mainMenuIcons, menus));
        mRecyclerViewMenu.setAdapter(mainMenuAdapter);

        menus = this.getActivity().getResources().getStringArray(R.array.second_menu);
        mSecondRecyclerViewMenu.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        SecondMenuAdapter secondMenuAdapter = new SecondMenuAdapter(getActivity(), DataUtil.getMainMenus(secondMenuIcons, menus));
        mSecondRecyclerViewMenu.setAdapter(secondMenuAdapter);
    }
}
