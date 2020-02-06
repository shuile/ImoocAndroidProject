package cn.imooc.imoochomework_android05.util;

import android.content.Context;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.imooc.imoochomework_android05.entity.Menu;
import cn.imooc.imoochomework_android05.entity.News;

public class DataUtil {

    /**
     *
     * @param context
     * @param icons
     * @return
     */
    public static List<ImageView> getHeaderAdInfo(Context context, int[] icons) {
        List<ImageView> datas = new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            ImageView icon = new ImageView(context);
            icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
            icon.setImageResource(icons[i]);
            datas.add(icon);
        }
        return datas;
    }

    public static List<Menu> getMainMenus(int[] icons, String[] names) {
        List<Menu> menus = new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            Menu menu = new Menu(icons[i], names[i]);
            menus.add(menu);
        }
        return menus;
    }

    public static List<News> getNews(String[] newsContent, String[] newsFrom, int[] seeNumber,
                                     int[] dianNumber, int[] newsPic) {
        List<News> newsList = new ArrayList<>();
        for (int i = 0; i < newsContent.length; i++) {
            News news = new News(newsContent[i], newsFrom[i], seeNumber[i], dianNumber[i], newsPic[i]);
            newsList.add(news);
        }
        return newsList;
    }
}
