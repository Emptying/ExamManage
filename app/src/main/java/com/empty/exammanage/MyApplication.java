package com.empty.exammanage;
import android.app.Application;

/**
 * Created by wallds on 2016/5/28.
 */
public class MyApplication extends Application{
    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    public static MyApplication getInstance() {
        return instance;
    }
}
