package cn.imooc.imoochomework_android06.util;

import java.util.ArrayList;
import java.util.List;

import cn.imooc.imoochomework_android06.entity.Food;
import cn.imooc.imoochomework_android06.entity.Menu;

public class DataUtil {

    public static List<Menu> getMainFirstMenu(int[] mainFirstIcons, String[] mainFirstName) {
        List<Menu> menuList = new ArrayList<>();
        for (int i = 0; i < mainFirstIcons.length; i++) {
            Menu menu = new Menu(mainFirstIcons[i], mainFirstName[i]);
            menuList.add(menu);
        }
        return menuList;
    }

    public static List<Food> getFood(int[] foodPics, String[] foodName, String[] foodDes,
                                     double[] foodPrice, boolean[] isNewCustomer, int[] saleNumber) {
        List<Food> foodList = new ArrayList<>();
        for (int i = 0; i < foodPics.length; i++) {
            Food food = new Food(foodPics[i], foodName[i], foodDes[i], foodPrice[i], isNewCustomer[i], saleNumber[i]);
            foodList.add(food);
        }
        return foodList;
    }
}
