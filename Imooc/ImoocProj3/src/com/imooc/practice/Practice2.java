package com.imooc.practice;

import java.io.*;

class Product implements Serializable {

    private String id;
    private String name;
    private String categories;
    private double price;

    public Product() {
    }

    public Product(String id, String name, String categories, double price) {
        super();
        this.id = id;
        this.name = name;
        this.categories = categories;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "产品ID：" + id
                + "\n产品名称：" + name
                + "\n产品属性：" + categories
                + "\n产品价格：" + price
                +"元";
    }
}

public class Practice2 {

    public static void main(String[] args) {
        Product p1 = new Product("123", "iphone", "telephone", 4888);
        Product p2 = new Product("234", "ipad", "computer", 5088);
        Product p3 = new Product("345", "mac book", "computer", 10688);
        Product p4 = new Product("456", "iwatch", "watch", 4799);

        Product[] p = {p1, p2, p3, p4};

        try {
            FileOutputStream fos = new FileOutputStream("practice2.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            FileInputStream fis = new FileInputStream("practice2.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            for (int i = 0; i < 4; i++) {
                oos.writeObject(p[i]);
                oos.flush();
            }

            System.out.println("apple系列产品信息：");
            Product p5 = null;
            ois.available();

//            //正确方法
//            while (fis.available() > 0) {
//                System.out.println("11: " + ois.available());
//                p5 = (Product) ois.readObject();
//                System.out.println("22: " + ois.available());
//                System.out.println(p5);
//                System.out.println();
//            }

            while (ois.available() != -1) {
                p5 = (Product) ois.readObject();
                System.out.println(p5);
                System.out.println();
            }

//            //错误方法
//            while ((p5 = (Product) ois.readObject()) != null) {
//                System.out.println(p5);
//                System.out.println();
//            }

            fos.close();
            oos.close();
            fis.close();
            ois.close();
        } catch (EOFException e) {
            System.out.println("打印结束");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
