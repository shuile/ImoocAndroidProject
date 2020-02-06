package cn.shui.imoochomework_android07.bean;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private String menuName;
    private List<MenuItem> children = new ArrayList<>();

    public Menu() {
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void addChild(MenuItem menuItem) {
        children.add(menuItem);
    }

    public List<MenuItem> getChildren() {
        return children;
    }

    public void setChildren(List<MenuItem> children) {
        this.children = children;
    }
}
