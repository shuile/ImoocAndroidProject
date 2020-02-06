package cn.imooc.imoochomework_android06.entity;

public class Menu {

    private int pic;
    private String name;

    public Menu(int pic, String name) {
        pic = pic;
        this.name = name;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
