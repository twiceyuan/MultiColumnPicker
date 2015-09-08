package com.twiceyuan.multicolumnpicker;

import android.app.Application;

import com.litesuits.orm.LiteOrm;

/**
 * Created by twiceYuan on 9/7/15.
 */
public class App extends Application {

    public static LiteOrm db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = LiteOrm.newSingleInstance(this, "cities");
    }
}
