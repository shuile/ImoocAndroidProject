package cn.imooc.imoochomework_android06.entity;

public class Food {

    private int foodPic;
    private String foodName;
    private String foodDes;
    private double foodPrice;
    private boolean isNewCustomer;
    private int saleNumber;

    public Food(int foodPic, String foodName, String foodDes, double foodPrice, boolean isNewCustomer, int saleNumber) {
        this.foodPic = foodPic;
        this.foodName = foodName;
        this.foodDes = foodDes;
        this.foodPrice = foodPrice;
        this.isNewCustomer = isNewCustomer;
        this.saleNumber = saleNumber;
    }

    public int getFoodPic() {
        return foodPic;
    }

    public void setFoodPic(int foodPic) {
        this.foodPic = foodPic;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDes() {
        return foodDes;
    }

    public void setFoodDes(String foodDes) {
        this.foodDes = foodDes;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public boolean isNewCustomer() {
        return isNewCustomer;
    }

    public void setNewCustomer(boolean newCustomer) {
        isNewCustomer = newCustomer;
    }

    public int getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(int saleNumber) {
        this.saleNumber = saleNumber;
    }
}
