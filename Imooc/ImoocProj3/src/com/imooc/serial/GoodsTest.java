package com.imooc.serial;

import java.io.*;

public class GoodsTest {

    public static void main(String[] args) {
        //定义Goods类的对象
        Goods goods1 = new Goods("gd001", "电脑", 3000);
        try {
            FileOutputStream fos = new FileOutputStream("imooc.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            FileInputStream fis = new FileInputStream("imooc.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            //将Goods对象信息写入文件
            oos.writeObject(goods1);
            oos.writeBoolean(true);
            oos.flush();
            System.out.println(ois.readBoolean());
            //读对象信息
            Goods goods = (Goods) ois.readObject();
            System.out.println(goods);
            fos.close();
            oos.close();
            fis.close();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
