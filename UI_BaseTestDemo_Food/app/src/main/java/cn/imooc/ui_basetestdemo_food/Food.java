package cn.imooc.ui_basetestdemo_food;

public class Food {

    private String name;
    private boolean hot;
    private boolean seafood;
    private boolean sour;
    private int price;
    private int pic;

    public Food() {
    }

    public Food(String name, int price, int pic, boolean hot, boolean seafood, boolean sour) {
        super();
        this.name = name;
        this.hot = hot;
        this.seafood = seafood;
        this.sour = sour;
        this.price = price;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public boolean isSeafood() {
        return seafood;
    }

    public void setSeafood(boolean seafood) {
        this.seafood = seafood;
    }

    public boolean isSour() {
        return sour;
    }

    public void setSour(boolean sour) {
        this.sour = sour;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Food [foodName=" + name
                + ", foodPrice=" + price
                + ", isHot=" + hot
                + ", isSeafood=" + seafood
                + ", isSour=" + sour
                + "]";
    }
}
