package cn.shui.myapp;

import com.squareup.otto.Bus;

/**
 * Created by shui on 2019-12-20
 */
public class BusProvider {
    public BusProvider() {

    }

    private static final Bus BUS = new Bus();

    public static Bus getBus() {
        return BUS;
    }
}
