package com.imooc.tel;

public class PhoneTest {
    public static void main(String[] args) {
        ForthPhone phone = new ForthPhone();
        phone.call();
        phone.message();
        phone.video();
        phone.photo();
        phone.network();
        System.out.println("===============");
        IPhoto ip = new ForthPhone();
        ip.photo();
        ip = new Camera();
        ip.photo();
        System.out.println("================");
        System.out.println(INet.TEMP);
        INet net = new SmartWatch();
        net.connection();
        INet.stop();
//        System.out.println(net.TEMP);
//        SmartWatch sw = new SmartWatch();
//        System.out.println(sw.TEMP);
        System.out.println("=================");
        INet net2 = new SmartWatch();
        net2.connection();
        IPhoto ip2 = new SmartWatch();
        ip2.connection();
        System.out.println("=================");
        INet net3 = new ForthPhone();
        net3.connection();
        IPhoto ip3 = new ForthPhone();
        ip3.connection();
    }
}
